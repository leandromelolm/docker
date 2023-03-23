package com.company.project.service;

import com.company.project.dto.PersonDTO;
import com.company.project.entities.Person;
import com.company.project.repository.PersonRepository;
import com.company.project.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    PersonRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public Page<PersonDTO> findAll(Integer page, Integer size, String orderBy, String direction) {
        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.valueOf(direction),orderBy);
        Page<Person> pessoaPage = repository.findAll(pageable);
        return pessoaPage.map(x -> new PersonDTO(x));
    }

    public Person findById(Long id) {
        Optional<Person> pessoaOptional = repository.findById(id);
        return pessoaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Person create(PersonDTO o) {
        o.setCreatedOn(Instant.now());
        o.setLastLogin(Instant.now().atZone(ZoneId.of("UTC")));
        return repository.save(modelMapper.map(o, Person.class));
    }

    public Object udpate(PersonDTO objDto) {
        findById(objDto.getUserId());
        return repository.save(modelMapper.map(objDto, Person.class));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
