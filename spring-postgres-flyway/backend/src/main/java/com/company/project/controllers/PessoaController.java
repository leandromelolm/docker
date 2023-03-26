package com.company.project.controllers;

import com.company.project.dto.PersonDTO;
import com.company.project.entities.Person;
import com.company.project.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value="/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PessoaController {

    @Autowired
    PessoaService service;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping()
    @Operation(summary = "Finds all People", description = "Finds all People",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<Page<PersonDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value="orderBy", defaultValue="birthdate") String orderBy,
            @RequestParam(value="direction", defaultValue="DESC") String direction){
        Page<PersonDTO> pessoaDtoPage = service.findAll(page, size, orderBy, direction);
        return ResponseEntity.ok().body(pessoaDtoPage);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Finds a Person", description = "Finds a Person",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<PersonDTO> getOnePessoa(@PathVariable("id") Long id){
        Person pessoa = service.findById(id);
        PersonDTO pessoaDTO = modelMapper.map(pessoa, PersonDTO.class);
        return ResponseEntity.ok().body(pessoaDTO);
    }

    @PostMapping()
    @Operation(summary = "Adds a new Person",
            description = "Adds a new Person by passing in a JSON representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<PersonDTO> create(@RequestBody PersonDTO objDto){
        Person newObj = service.create(objDto);
        PersonDTO newDto = modelMapper.map(newObj, PersonDTO.class);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getUserId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Updates a Person",
            description = "Updates a Person by passing in a JSON, XML or YML representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonDTO.class))
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<PersonDTO> update(@PathVariable Long id, @RequestBody PersonDTO objDto){
        objDto.setUserId(id);
        return ResponseEntity.ok().body(modelMapper.map(service.udpate(objDto), PersonDTO.class));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletes a Person",
            description = "Deletes a Person by passing in a JSON representation of the person!",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<String> delete(@PathVariable Long id){
        Person pessoa = service.findById(id);
        service.delete(pessoa.getUserId());
        return ResponseEntity.status(HttpStatus.OK).body("person "+pessoa.getUsername()+" deleted successfully.");
    }

}
