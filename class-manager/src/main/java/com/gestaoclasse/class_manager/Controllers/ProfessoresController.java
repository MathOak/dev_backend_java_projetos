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

import com.gestaoclasse.class_manager.DTOs.Professores.ProfessoresRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Professores.ProfessoresResponseDTO;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Services.ProfessoresService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("professores")
public class ProfessoresController {

	@Autowired
	private ProfessoresService service;
	
	@GetMapping
	public ResponseEntity<List<ProfessoresResponseDTO>> listarTodos() {
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<ProfessoresResponseDTO> criarAluno(@Valid @RequestBody ProfessoresRequestDTO body) {
		Professores object = ProfessoresRequestDTO.toEntity(body);
		return service.saveOne(object);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProfessoresResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody ProfessoresRequestDTO body){
		Professores dados = ProfessoresRequestDTO.toEntity(body);
		return service.updateById(id, dados);
	}
}
