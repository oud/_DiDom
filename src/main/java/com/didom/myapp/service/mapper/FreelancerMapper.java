package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.FreelancerDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Freelancer and its DTO FreelancerDTO.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, LocationMapper.class, SkillMapper.class, })
public interface FreelancerMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.login", target = "userLogin")
    @Mapping(source = "location.id", target = "locationId")
    FreelancerDTO freelancerToFreelancerDTO(Freelancer freelancer);

    List<FreelancerDTO> freelancersToFreelancerDTOs(List<Freelancer> freelancers);

    @Mapping(source = "userId", target = "user")
    @Mapping(source = "locationId", target = "location")
    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "proposals", ignore = true)
    @Mapping(target = "messages", ignore = true)
    Freelancer freelancerDTOToFreelancer(FreelancerDTO freelancerDTO);

    List<Freelancer> freelancerDTOsToFreelancers(List<FreelancerDTO> freelancerDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Freelancer freelancerFromId(Long id) {
        if (id == null) {
            return null;
        }
        Freelancer freelancer = new Freelancer();
        freelancer.setId(id);
        return freelancer;
    }
    

}
