package com.didom.myapp.service.impl;

import com.didom.myapp.service.ProposalService;
import com.didom.myapp.domain.Proposal;
import com.didom.myapp.repository.ProposalRepository;
import com.didom.myapp.service.dto.ProposalDTO;
import com.didom.myapp.service.mapper.ProposalMapper;
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
 * Service Implementation for managing Proposal.
 */
@Service
@Transactional
public class ProposalServiceImpl implements ProposalService{

    private final Logger log = LoggerFactory.getLogger(ProposalServiceImpl.class);
    
    private final ProposalRepository proposalRepository;

    private final ProposalMapper proposalMapper;

    public ProposalServiceImpl(ProposalRepository proposalRepository, ProposalMapper proposalMapper) {
        this.proposalRepository = proposalRepository;
        this.proposalMapper = proposalMapper;
    }

    /**
     * Save a proposal.
     *
     * @param proposalDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ProposalDTO save(ProposalDTO proposalDTO) {
        log.debug("Request to save Proposal : {}", proposalDTO);
        Proposal proposal = proposalMapper.proposalDTOToProposal(proposalDTO);
        proposal = proposalRepository.save(proposal);
        ProposalDTO result = proposalMapper.proposalToProposalDTO(proposal);
        return result;
    }

    /**
     *  Get all the proposals.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ProposalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Proposals");
        Page<Proposal> result = proposalRepository.findAll(pageable);
        return result.map(proposal -> proposalMapper.proposalToProposalDTO(proposal));
    }

    /**
     *  Get one proposal by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public ProposalDTO findOne(Long id) {
        log.debug("Request to get Proposal : {}", id);
        Proposal proposal = proposalRepository.findOne(id);
        ProposalDTO proposalDTO = proposalMapper.proposalToProposalDTO(proposal);
        return proposalDTO;
    }

    /**
     *  Delete the  proposal by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Proposal : {}", id);
        proposalRepository.delete(id);
    }
}
