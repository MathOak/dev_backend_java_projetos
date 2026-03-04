package com.gestaoclasse.class_manager.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.AlunoResponsePostPutDTO;
import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.AlunosRepository;

@Service
public class AlunosService {
	@Autowired
	private AlunosRepository repository;
	
	public List<Alunos> getAllAlunos(){
		return repository.findAll();
	}
	
	public Alunos saveOne(Alunos aluno) {
		return repository.save(aluno);
	}
	
	public ResponseEntity<?> deleteAlunoById(UUID id){
		return repository.findById(id).map(aluno -> {
			repository.delete(aluno);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Aluno não existe!"));
		
	}
	
	public ResponseEntity<AlunoResponsePostPutDTO> updateAlunoById(UUID id, Alunos body){
		return repository.findById(id).
				map(aluno -> {
					aluno.setNome(body.getNome());
					aluno.setData_nascimento(body.getData_nascimento());
					aluno.setMatricula(body.getMatricula());
					Alunos alunoNovo = repository.save(aluno);
					
					return ResponseEntity.ok().body(toResponsePostPut(alunoNovo));
				}).orElse(ResponseEntity.notFound().build());
	}
	private AlunoResponsePostPutDTO toResponsePostPut(Alunos aluno) {
		LocalDate requestTime = LocalDate.now();
		return new AlunoResponsePostPutDTO(
				aluno.getId(),
				aluno.getNome(),
				aluno.getData_nascimento(),
				aluno.getMatricula(),
				requestTime
				);
	}
}
