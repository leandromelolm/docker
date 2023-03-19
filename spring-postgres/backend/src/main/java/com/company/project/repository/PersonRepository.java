package com.company.project.repository;

import com.company.project.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional(readOnly = true)
    @Query("SELECT p FROM Person p")
    Page<Person> findAll(Pageable pageable);

    @Query("SELECT min(p.posicao) FROM Person p")
    Integer menorPosicao();

}


