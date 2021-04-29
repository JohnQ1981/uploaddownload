package com.udemy.docweba.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.udemy.docweba.entities.Documenta;

public interface DocumentaRepo extends JpaRepository<Documenta, Long> {

}
