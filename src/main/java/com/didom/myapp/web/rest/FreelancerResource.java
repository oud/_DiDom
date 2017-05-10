package com.didom.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.didom.myapp.service.FreelancerService;
import com.didom.myapp.web.rest.util.HeaderUtil;
import com.didom.myapp.web.rest.util.PaginationUtil;
import com.didom.myapp.service.dto.FreelancerDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Freelancer.
 */
@RestController
@RequestMapping("/api")
public class FreelancerResource {

    private final Logger log = LoggerFactory.getLogger(FreelancerResource.class);

    private static final String ENTITY_NAME = "freelancer";
        
    private final FreelancerService freelancerService;

    public FreelancerResource(FreelancerService freelancerService) {
        this.freelancerService = freelancerService;
    }

    /**
     * POST  /freelancers : Create a new freelancer.
     *
     * @param freelancerDTO the freelancerDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new freelancerDTO, or with status 400 (Bad Request) if the freelancer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/freelancers")
    @Timed
    public ResponseEntity<FreelancerDTO> createFreelancer(@RequestBody FreelancerDTO freelancerDTO) throws URISyntaxException {
        log.debug("REST request to save Freelancer : {}", freelancerDTO);
        if (freelancerDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new freelancer cannot already have an ID")).body(null);
        }
        FreelancerDTO result = freelancerService.save(freelancerDTO);
        return ResponseEntity.created(new URI("/api/freelancers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /freelancers : Updates an existing freelancer.
     *
     * @param freelancerDTO the freelancerDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated freelancerDTO,
     * or with status 400 (Bad Request) if the freelancerDTO is not valid,
     * or with status 500 (Internal Server Error) if the freelancerDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/freelancers")
    @Timed
    public ResponseEntity<FreelancerDTO> updateFreelancer(@RequestBody FreelancerDTO freelancerDTO) throws URISyntaxException {
        log.debug("REST request to update Freelancer : {}", freelancerDTO);
        if (freelancerDTO.getId() == null) {
            return createFreelancer(freelancerDTO);
        }
        FreelancerDTO result = freelancerService.save(freelancerDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, freelancerDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /freelancers : get all the freelancers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of freelancers in body
     */
    @GetMapping("/freelancers")
    @Timed
    public ResponseEntity<List<FreelancerDTO>> getAllFreelancers(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Freelancers");
        Page<FreelancerDTO> page = freelancerService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/freelancers");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /freelancers/:id : get the "id" freelancer.
     *
     * @param id the id of the freelancerDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the freelancerDTO, or with status 404 (Not Found)
     */
    @GetMapping("/freelancers/{id}")
    @Timed
    public ResponseEntity<FreelancerDTO> getFreelancer(@PathVariable Long id) {
        log.debug("REST request to get Freelancer : {}", id);
        FreelancerDTO freelancerDTO = freelancerService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(freelancerDTO));
    }

    /**
     * DELETE  /freelancers/:id : delete the "id" freelancer.
     *
     * @param id the id of the freelancerDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/freelancers/{id}")
    @Timed
    public ResponseEntity<Void> deleteFreelancer(@PathVariable Long id) {
        log.debug("REST request to delete Freelancer : {}", id);
        freelancerService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
