package com.didom.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.didom.myapp.domain.ProposalStatusCatalog;

import com.didom.myapp.repository.ProposalStatusCatalogRepository;
import com.didom.myapp.web.rest.util.HeaderUtil;
import com.didom.myapp.service.dto.ProposalStatusCatalogDTO;
import com.didom.myapp.service.mapper.ProposalStatusCatalogMapper;
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

/**
 * REST controller for managing ProposalStatusCatalog.
 */
@RestController
@RequestMapping("/api")
public class ProposalStatusCatalogResource {

    private final Logger log = LoggerFactory.getLogger(ProposalStatusCatalogResource.class);

    private static final String ENTITY_NAME = "proposalStatusCatalog";
        
    private final ProposalStatusCatalogRepository proposalStatusCatalogRepository;

    private final ProposalStatusCatalogMapper proposalStatusCatalogMapper;

    public ProposalStatusCatalogResource(ProposalStatusCatalogRepository proposalStatusCatalogRepository, ProposalStatusCatalogMapper proposalStatusCatalogMapper) {
        this.proposalStatusCatalogRepository = proposalStatusCatalogRepository;
        this.proposalStatusCatalogMapper = proposalStatusCatalogMapper;
    }

    /**
     * POST  /proposal-status-catalogs : Create a new proposalStatusCatalog.
     *
     * @param proposalStatusCatalogDTO the proposalStatusCatalogDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new proposalStatusCatalogDTO, or with status 400 (Bad Request) if the proposalStatusCatalog has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/proposal-status-catalogs")
    @Timed
    public ResponseEntity<ProposalStatusCatalogDTO> createProposalStatusCatalog(@RequestBody ProposalStatusCatalogDTO proposalStatusCatalogDTO) throws URISyntaxException {
        log.debug("REST request to save ProposalStatusCatalog : {}", proposalStatusCatalogDTO);
        if (proposalStatusCatalogDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new proposalStatusCatalog cannot already have an ID")).body(null);
        }
        ProposalStatusCatalog proposalStatusCatalog = proposalStatusCatalogMapper.proposalStatusCatalogDTOToProposalStatusCatalog(proposalStatusCatalogDTO);
        proposalStatusCatalog = proposalStatusCatalogRepository.save(proposalStatusCatalog);
        ProposalStatusCatalogDTO result = proposalStatusCatalogMapper.proposalStatusCatalogToProposalStatusCatalogDTO(proposalStatusCatalog);
        return ResponseEntity.created(new URI("/api/proposal-status-catalogs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /proposal-status-catalogs : Updates an existing proposalStatusCatalog.
     *
     * @param proposalStatusCatalogDTO the proposalStatusCatalogDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated proposalStatusCatalogDTO,
     * or with status 400 (Bad Request) if the proposalStatusCatalogDTO is not valid,
     * or with status 500 (Internal Server Error) if the proposalStatusCatalogDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/proposal-status-catalogs")
    @Timed
    public ResponseEntity<ProposalStatusCatalogDTO> updateProposalStatusCatalog(@RequestBody ProposalStatusCatalogDTO proposalStatusCatalogDTO) throws URISyntaxException {
        log.debug("REST request to update ProposalStatusCatalog : {}", proposalStatusCatalogDTO);
        if (proposalStatusCatalogDTO.getId() == null) {
            return createProposalStatusCatalog(proposalStatusCatalogDTO);
        }
        ProposalStatusCatalog proposalStatusCatalog = proposalStatusCatalogMapper.proposalStatusCatalogDTOToProposalStatusCatalog(proposalStatusCatalogDTO);
        proposalStatusCatalog = proposalStatusCatalogRepository.save(proposalStatusCatalog);
        ProposalStatusCatalogDTO result = proposalStatusCatalogMapper.proposalStatusCatalogToProposalStatusCatalogDTO(proposalStatusCatalog);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, proposalStatusCatalogDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /proposal-status-catalogs : get all the proposalStatusCatalogs.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of proposalStatusCatalogs in body
     */
    @GetMapping("/proposal-status-catalogs")
    @Timed
    public List<ProposalStatusCatalogDTO> getAllProposalStatusCatalogs() {
        log.debug("REST request to get all ProposalStatusCatalogs");
        List<ProposalStatusCatalog> proposalStatusCatalogs = proposalStatusCatalogRepository.findAll();
        return proposalStatusCatalogMapper.proposalStatusCatalogsToProposalStatusCatalogDTOs(proposalStatusCatalogs);
    }

    /**
     * GET  /proposal-status-catalogs/:id : get the "id" proposalStatusCatalog.
     *
     * @param id the id of the proposalStatusCatalogDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the proposalStatusCatalogDTO, or with status 404 (Not Found)
     */
    @GetMapping("/proposal-status-catalogs/{id}")
    @Timed
    public ResponseEntity<ProposalStatusCatalogDTO> getProposalStatusCatalog(@PathVariable Long id) {
        log.debug("REST request to get ProposalStatusCatalog : {}", id);
        ProposalStatusCatalog proposalStatusCatalog = proposalStatusCatalogRepository.findOne(id);
        ProposalStatusCatalogDTO proposalStatusCatalogDTO = proposalStatusCatalogMapper.proposalStatusCatalogToProposalStatusCatalogDTO(proposalStatusCatalog);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(proposalStatusCatalogDTO));
    }

    /**
     * DELETE  /proposal-status-catalogs/:id : delete the "id" proposalStatusCatalog.
     *
     * @param id the id of the proposalStatusCatalogDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/proposal-status-catalogs/{id}")
    @Timed
    public ResponseEntity<Void> deleteProposalStatusCatalog(@PathVariable Long id) {
        log.debug("REST request to delete ProposalStatusCatalog : {}", id);
        proposalStatusCatalogRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
