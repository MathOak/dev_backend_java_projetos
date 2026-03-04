package com.gestaoclasse.class_manager.Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestaoclasse.class_manager.DTOs.AlunoRequestDTO;
import com.gestaoclasse.class_manager.DTOs.AlunoResponseDTO;
import com.gestaoclasse.class_manager.DTOs.AlunoResponsePostPutDTO;
import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Services.AlunosService;
import com.gestaoclasse.class_manager.Services.ProfessoresService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("professores")
public class ProfessoresController {

	@Autowired
	private ProfessoresService service;
	
	@GetMapping
	public ResponseEntity<List<Professores>> listarTodos() {
		ArrayList<Professores> alunos =new ArrayList<>(service.getAllAlunos());
		ArrayList<Professores> newAlunosList = new ArrayList();
		alunos.forEach(aluno->{
			newAlunosList.add(aluno);
		});
	
		return ResponseEntity.status(HttpStatus.OK).body(newAlunosList);
	}
	
	@PostMapping
	public ResponseEntity<Professores> criarAluno(@Valid @RequestBody Professores body) {
		Professores aluno = body;
		Professores newAluno = service.saveOne(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(newAluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Professores> atualizar(@PathVariable UUID id, @Valid @RequestBody Professores body){
		Professores dados = body;
		return service.updateProfessorById(id, dados);
	}
}
