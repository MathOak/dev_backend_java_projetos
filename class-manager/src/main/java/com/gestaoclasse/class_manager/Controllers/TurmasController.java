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
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Services.AlunosService;
import com.gestaoclasse.class_manager.Services.TurmasService;
import com.gestaoclasse.class_manager.Services.TurmasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("turmas")
public class TurmasController {

	@Autowired
	private TurmasService service;
	
	@GetMapping
	public ResponseEntity<List<Turmas>> listarTodos() {
		ArrayList<Turmas> alunos =new ArrayList<>(service.getAllAlunos());
		ArrayList<Turmas> newAlunosList = new ArrayList();
		alunos.forEach(aluno->{
			newAlunosList.add(aluno);
		});
	
		return ResponseEntity.status(HttpStatus.OK).body(newAlunosList);
	}
	
	@PostMapping
	public ResponseEntity<Turmas> criarAluno(@Valid @RequestBody Turmas body) {
		Turmas aluno = body;
		Turmas newAluno = service.saveOne(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(newAluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Turmas> atualizar(@PathVariable UUID id, @Valid @RequestBody Turmas body){
		Turmas dados = body;
		return service.updateProfessorById(id, dados);
	}
}
