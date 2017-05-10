package com.didom.myapp.service.mapper;

import com.didom.myapp.domain.*;
import com.didom.myapp.service.dto.PaymentTypeDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity PaymentType and its DTO PaymentTypeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentTypeMapper {

    PaymentTypeDTO paymentTypeToPaymentTypeDTO(PaymentType paymentType);

    List<PaymentTypeDTO> paymentTypesToPaymentTypeDTOs(List<PaymentType> paymentTypes);

    @Mapping(target = "jobs", ignore = true)
    @Mapping(target = "contracts", ignore = true)
    @Mapping(target = "proposals", ignore = true)
    PaymentType paymentTypeDTOToPaymentType(PaymentTypeDTO paymentTypeDTO);

    List<PaymentType> paymentTypeDTOsToPaymentTypes(List<PaymentTypeDTO> paymentTypeDTOs);
    /**
     * generating the fromId for all mappers if the databaseType is sql, as the class has relationship to it might need it, instead of
     * creating a new attribute to know if the entity has any relationship from some other entity
     *
     * @param id id of the entity
     * @return the entity instance
     */
     
    default PaymentType paymentTypeFromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentType paymentType = new PaymentType();
        paymentType.setId(id);
        return paymentType;
    }
    

}
