package com.gestaoclasse.class_manager.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.AlunoResponsePostPutDTO;
import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.AlunosRepository;
import com.gestaoclasse.class_manager.Repositories.ProfessoresRepository;
import com.gestaoclasse.class_manager.Repositories.TurmasRepository;

@Service
public class TurmasService {
	@Autowired
	private TurmasRepository repository;
	
	public List<Turmas> getAllAlunos(){
		return repository.findAll();
	}
	
	public Turmas saveOne(Turmas professor) {
		return repository.save(professor);
	}
	
	public ResponseEntity<?> deleteAlunoById(UUID id){
		return repository.findById(id).map(aluno -> {
			repository.delete(aluno);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Professor não existe!"));
		
	}
	
	public ResponseEntity<Turmas> updateProfessorById(UUID id, Turmas body){
		return repository.findById(id).
				map(professor -> {
					Turmas professorNovo = repository.save(professor);
					
					return ResponseEntity.ok().body(professorNovo);
				}).orElse(ResponseEntity.notFound().build());
	}

}
