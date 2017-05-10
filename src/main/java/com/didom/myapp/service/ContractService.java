package com.didom.myapp.service;

import com.didom.myapp.service.dto.ContractDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Contract.
 */
public interface ContractService {

    /**
     * Save a contract.
     *
     * @param contractDTO the entity to save
     * @return the persisted entity
     */
    ContractDTO save(ContractDTO contractDTO);

    /**
     *  Get all the contracts.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<ContractDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" contract.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    ContractDTO findOne(Long id);

    /**
     *  Delete the "id" contract.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
