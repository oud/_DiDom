package com.didom.myapp.service.impl;

import com.didom.myapp.service.FreelancerService;
import com.didom.myapp.domain.Freelancer;
import com.didom.myapp.repository.FreelancerRepository;
import com.didom.myapp.service.dto.FreelancerDTO;
import com.didom.myapp.service.mapper.FreelancerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Freelancer.
 */
@Service
@Transactional
public class FreelancerServiceImpl implements FreelancerService{

    private final Logger log = LoggerFactory.getLogger(FreelancerServiceImpl.class);
    
    private final FreelancerRepository freelancerRepository;

    private final FreelancerMapper freelancerMapper;

    public FreelancerServiceImpl(FreelancerRepository freelancerRepository, FreelancerMapper freelancerMapper) {
        this.freelancerRepository = freelancerRepository;
        this.freelancerMapper = freelancerMapper;
    }

    /**
     * Save a freelancer.
     *
     * @param freelancerDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FreelancerDTO save(FreelancerDTO freelancerDTO) {
        log.debug("Request to save Freelancer : {}", freelancerDTO);
        Freelancer freelancer = freelancerMapper.freelancerDTOToFreelancer(freelancerDTO);
        freelancer = freelancerRepository.save(freelancer);
        FreelancerDTO result = freelancerMapper.freelancerToFreelancerDTO(freelancer);
        return result;
    }

    /**
     *  Get all the freelancers.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FreelancerDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Freelancers");
        Page<Freelancer> result = freelancerRepository.findAll(pageable);
        return result.map(freelancer -> freelancerMapper.freelancerToFreelancerDTO(freelancer));
    }

    /**
     *  Get one freelancer by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FreelancerDTO findOne(Long id) {
        log.debug("Request to get Freelancer : {}", id);
        Freelancer freelancer = freelancerRepository.findOneWithEagerRelationships(id);
        FreelancerDTO freelancerDTO = freelancerMapper.freelancerToFreelancerDTO(freelancer);
        return freelancerDTO;
    }

    /**
     *  Delete the  freelancer by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Freelancer : {}", id);
        freelancerRepository.delete(id);
    }
}
