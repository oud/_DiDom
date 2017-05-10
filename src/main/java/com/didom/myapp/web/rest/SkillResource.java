package com.didom.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.didom.myapp.domain.Skill;

import com.didom.myapp.repository.SkillRepository;
import com.didom.myapp.web.rest.util.HeaderUtil;
import com.didom.myapp.service.dto.SkillDTO;
import com.didom.myapp.service.mapper.SkillMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Skill.
 */
@RestController
@RequestMapping("/api")
public class SkillResource {

    private final Logger log = LoggerFactory.getLogger(SkillResource.class);

    private static final String ENTITY_NAME = "skill";
        
    private final SkillRepository skillRepository;

    private final SkillMapper skillMapper;

    public SkillResource(SkillRepository skillRepository, SkillMapper skillMapper) {
        this.skillRepository = skillRepository;
        this.skillMapper = skillMapper;
    }

    /**
     * POST  /skills : Create a new skill.
     *
     * @param skillDTO the skillDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new skillDTO, or with status 400 (Bad Request) if the skill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/skills")
    @Timed
    public ResponseEntity<SkillDTO> createSkill(@Valid @RequestBody SkillDTO skillDTO) throws URISyntaxException {
        log.debug("REST request to save Skill : {}", skillDTO);
        if (skillDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new skill cannot already have an ID")).body(null);
        }
        Skill skill = skillMapper.skillDTOToSkill(skillDTO);
        skill = skillRepository.save(skill);
        SkillDTO result = skillMapper.skillToSkillDTO(skill);
        return ResponseEntity.created(new URI("/api/skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /skills : Updates an existing skill.
     *
     * @param skillDTO the skillDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated skillDTO,
     * or with status 400 (Bad Request) if the skillDTO is not valid,
     * or with status 500 (Internal Server Error) if the skillDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/skills")
    @Timed
    public ResponseEntity<SkillDTO> updateSkill(@Valid @RequestBody SkillDTO skillDTO) throws URISyntaxException {
        log.debug("REST request to update Skill : {}", skillDTO);
        if (skillDTO.getId() == null) {
            return createSkill(skillDTO);
        }
        Skill skill = skillMapper.skillDTOToSkill(skillDTO);
        skill = skillRepository.save(skill);
        SkillDTO result = skillMapper.skillToSkillDTO(skill);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, skillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /skills : get all the skills.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of skills in body
     */
    @GetMapping("/skills")
    @Timed
    public List<SkillDTO> getAllSkills() {
        log.debug("REST request to get all Skills");
        List<Skill> skills = skillRepository.findAll();
        return skillMapper.skillsToSkillDTOs(skills);
    }

    /**
     * GET  /skills/:id : get the "id" skill.
     *
     * @param id the id of the skillDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the skillDTO, or with status 404 (Not Found)
     */
    @GetMapping("/skills/{id}")
    @Timed
    public ResponseEntity<SkillDTO> getSkill(@PathVariable Long id) {
        log.debug("REST request to get Skill : {}", id);
        Skill skill = skillRepository.findOne(id);
        SkillDTO skillDTO = skillMapper.skillToSkillDTO(skill);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(skillDTO));
    }

    /**
     * DELETE  /skills/:id : delete the "id" skill.
     *
     * @param id the id of the skillDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/skills/{id}")
    @Timed
    public ResponseEntity<Void> deleteSkill(@PathVariable Long id) {
        log.debug("REST request to delete Skill : {}", id);
        skillRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
