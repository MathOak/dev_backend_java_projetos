package com.gestaoclasse.class_manager.Controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestaoclasse.class_manager.DTOs.AlunosDTOs.AlunoRequestDTO;
import com.gestaoclasse.class_manager.DTOs.AlunosDTOs.AlunoResponseDTO;
import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Services.AlunosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("alunos")
public class AlunosController {

	@Autowired
	private AlunosService service;
	
	@GetMapping
	public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<AlunoResponseDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO body) {
		Alunos aluno = AlunoRequestDTO.toEntity(body);
		return service.saveOne(aluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody AlunoRequestDTO body){
		Alunos dados = AlunoRequestDTO.toEntity(body);
		return service.updateById(id, dados);
	}
	
}
