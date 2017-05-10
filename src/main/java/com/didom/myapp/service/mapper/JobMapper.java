package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.JobDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Job and its DTO JobDTO.
 */
@Mapper(componentModel = "spring", uses = {PaymentTypeMapper.class, ClientMapper.class, SkillMapper.class, })
public interface JobMapper {

    @Mapping(source = "paymentType.id", target = "paymentTypeId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "mainSkill.id", target = "mainSkillId")
    @Mapping(source = "mainSkill.skillName", target = "mainSkillSkillName")
    JobDTO jobToJobDTO(Job job);

    List<JobDTO> jobsToJobDTOs(List<Job> jobs);

    @Mapping(source = "paymentTypeId", target = "paymentType")
    @Mapping(source = "clientId", target = "client")
    @Mapping(target = "proposals", ignore = true)
    @Mapping(source = "mainSkillId", target = "mainSkill")
    Job jobDTOToJob(JobDTO jobDTO);

    List<Job> jobDTOsToJobs(List<JobDTO> jobDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default Job jobFromId(Long id) {
        if (id == null) {
            return null;
        }
        Job job = new Job();
        job.setId(id);
        return job;
    }
    

}
