package com.gestaoclasse.class_manager.Repositories;

import org.springframework.stereotype.Repository;

import com.gestaoclasse.class_manager.Entities.Cursos;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CursosRepository extends JpaRepository<Cursos, UUID> {
}
