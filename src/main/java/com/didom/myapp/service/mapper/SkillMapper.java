package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.SkillDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Skill and its DTO SkillDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SkillMapper {

    SkillDTO skillToSkillDTO(Skill skill);

    List<SkillDTO> skillsToSkillDTOs(List<Skill> skills);

    @Mapping(target = "freelancers", ignore = true)
    Skill skillDTOToSkill(SkillDTO skillDTO);

    List<Skill> skillDTOsToSkills(List<SkillDTO> skillDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Skill skillFromId(Long id) {
        if (id == null) {
            return null;
        }
        Skill skill = new Skill();
        skill.setId(id);
        return skill;
    }
    

}
