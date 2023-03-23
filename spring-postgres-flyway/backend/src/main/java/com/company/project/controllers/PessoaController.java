package com.company.project.controllers;

import com.company.project.dto.PersonDTO;
import com.company.project.entities.Person;
import com.company.project.service.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/pessoas")
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Page<PersonDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value="orderBy", defaultValue="birthdate") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction){
        Page<PersonDTO> pessoaDtoPage = service.findAll(page, size, orderBy, direction);
        return ResponseEntity.ok().body(pessoaDtoPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getOnePessoa(@PathVariable("id") Long id){
        Person pessoa = service.findById(id);
        PersonDTO pessoaDTO = modelMapper.map(pessoa, PersonDTO.class);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @PostMapping()
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO objDto){
        Person newObj = service.create(objDto);
        PersonDTO newDto = modelMapper.map(newObj, PersonDTO.class);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getUserId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO objDto){
        objDto.setUserId(id);
        return ResponseEntity.ok().body(modelMapper.map(service.udpate(objDto), PersonDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Person pessoa = service.findById(id);
        service.delete(pessoa.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body("person "+pessoa.getUsername()+" deleted successfully.");
    }

}
