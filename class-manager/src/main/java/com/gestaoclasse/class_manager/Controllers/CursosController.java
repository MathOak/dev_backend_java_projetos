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

import com.gestaoclasse.class_manager.DTOs.Cursos.CursosRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Cursos.CursosResponseDTO;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Services.CursosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("cursos")
public class CursosController {

	@Autowired
	private CursosService service;
	
	@GetMapping
	public ResponseEntity<List<CursosResponseDTO>> listarTodos() {
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<CursosResponseDTO> criarAluno(@Valid @RequestBody CursosRequestDTO body) {
		Cursos object = CursosRequestDTO.toEntity(body);
		return service.saveOne(object);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CursosResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody CursosRequestDTO body){
		Cursos dados = CursosRequestDTO.toEntity(body);
		return service.updateById(id, dados);
	}
}
