package com.didom.myapp.service;

import com.didom.myapp.service.dto.FreelancerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Freelancer.
 */
public interface FreelancerService {

    /**
     * Save a freelancer.
     *
     * @param freelancerDTO the entity to save
     * @return the persisted entity
     */
    FreelancerDTO save(FreelancerDTO freelancerDTO);

    /**
     *  Get all the freelancers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<FreelancerDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" freelancer.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FreelancerDTO findOne(Long id);

    /**
     *  Delete the "id" freelancer.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
