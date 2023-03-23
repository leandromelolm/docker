package com.company.project.core;

import com.company.project.dto.PersonDTO;
import com.company.project.entities.Person;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Person.class, PersonDTO.class)
                .addMapping(Person::getRoles, PersonDTO::setRoles);
        return modelMapper;
    }
}
