package com.didom.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.didom.myapp.domain.Country;

import com.didom.myapp.repository.CountryRepository;
import com.didom.myapp.web.rest.util.HeaderUtil;
import com.didom.myapp.service.dto.CountryDTO;
import com.didom.myapp.service.mapper.CountryMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing Country.
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    private static final String ENTITY_NAME = "country";
        
    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    public CountryResource(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    /**
     * POST  /countries : Create a new country.
     *
     * @param countryDTO the countryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new countryDTO, or with status 400 (Bad Request) if the country has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/countries")
    @Timed
    public ResponseEntity<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to save Country : {}", countryDTO);
        if (countryDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new country cannot already have an ID")).body(null);
        }
        Country country = countryMapper.countryDTOToCountry(countryDTO);
        country = countryRepository.save(country);
        CountryDTO result = countryMapper.countryToCountryDTO(country);
        return ResponseEntity.created(new URI("/api/countries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /countries : Updates an existing country.
     *
     * @param countryDTO the countryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated countryDTO,
     * or with status 400 (Bad Request) if the countryDTO is not valid,
     * or with status 500 (Internal Server Error) if the countryDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/countries")
    @Timed
    public ResponseEntity<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) throws URISyntaxException {
        log.debug("REST request to update Country : {}", countryDTO);
        if (countryDTO.getId() == null) {
            return createCountry(countryDTO);
        }
        Country country = countryMapper.countryDTOToCountry(countryDTO);
        country = countryRepository.save(country);
        CountryDTO result = countryMapper.countryToCountryDTO(country);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, countryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /countries : get all the countries.
     *
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of countries in body
     */
    @GetMapping("/countries")
    @Timed
    public List<CountryDTO> getAllCountries(@RequestParam(required = false) String filter) {
        if ("location-is-null".equals(filter)) {
            log.debug("REST request to get all Countrys where location is null");
            return StreamSupport
                .stream(countryRepository.findAll().spliterator(), false)
                .filter(country -> country.getLocation() == null)
                .map(countryMapper::countryToCountryDTO)
                .collect(Collectors.toCollection(LinkedList::new));
        }
        log.debug("REST request to get all Countries");
        List<Country> countries = countryRepository.findAll();
        return countryMapper.countriesToCountryDTOs(countries);
    }

    /**
     * GET  /countries/:id : get the "id" country.
     *
     * @param id the id of the countryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the countryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/countries/{id}")
    @Timed
    public ResponseEntity<CountryDTO> getCountry(@PathVariable Long id) {
        log.debug("REST request to get Country : {}", id);
        Country country = countryRepository.findOne(id);
        CountryDTO countryDTO = countryMapper.countryToCountryDTO(country);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(countryDTO));
    }

    /**
     * DELETE  /countries/:id : delete the "id" country.
     *
     * @param id the id of the countryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/countries/{id}")
    @Timed
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        log.debug("REST request to delete Country : {}", id);
        countryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
