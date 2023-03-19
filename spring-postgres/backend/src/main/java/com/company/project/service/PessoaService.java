package com.company.project.service;

import com.company.project.dto.PessoaDTO;
import com.company.project.entity.Person;
import com.company.project.repository.PersonRepository;
import com.company.project.service.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    PersonRepository repository;

    @Autowired
    ModelMapper modelMapper;

    public Page<PessoaDTO> findAll(Integer page, Integer size, String orderBy, String direction) {
        Pageable pageable =  PageRequest.of(page, size, Sort.Direction.valueOf(direction),orderBy);
        Page<Person> pessoaPage = repository.findAll(pageable);
        return pessoaPage.map(x -> new PessoaDTO(x));
    }

    public Person findById(Long id) {
        Optional<Person> pessoaOptional = repository.findById(id);
        return pessoaOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public Person create(Person obj) {
        Integer menorPosicao = repository.menorPosicao();
        if(menorPosicao == null){
            obj.setPosicao(999);
        }else{
            obj.setPosicao(menorPosicao - 1);
        }
        return repository.save(modelMapper.map(obj, Person.class));
    }

    public Object udpate(PessoaDTO objDto) {
        findById(objDto.getId());
        return repository.save(modelMapper.map(objDto, Person.class));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
