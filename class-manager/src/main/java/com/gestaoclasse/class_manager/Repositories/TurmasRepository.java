package com.gestaoclasse.class_manager.Repositories;

import org.springframework.stereotype.Repository;

import com.gestaoclasse.class_manager.Entities.Turmas;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TurmasRepository extends JpaRepository<Turmas, UUID> {
}
