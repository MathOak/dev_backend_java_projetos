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

import com.gestaoclasse.class_manager.DTOs.AlunosDTOs.AlunoResponseDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasAssociateRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasResponseDTO;
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Services.TurmasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("turmas")
public class TurmasController {

	@Autowired
	private TurmasService service;
	
	@GetMapping
	public ResponseEntity<List<TurmasResponseDTO>> listarTodos() {
		return service.getAll();
	}
	
	@PostMapping
	public ResponseEntity<TurmasResponseDTO> criarAluno(@Valid @RequestBody TurmasRequestDTO body) {
		   return service.saveOne(body);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TurmasResponseDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody TurmasRequestDTO body){
		Turmas dados = TurmasRequestDTO.toEntity(body);
		return service.updateById(id, dados);
	}
	
	@PostMapping("/associar/{turma_id}/{aluno_id}")
	public ResponseEntity<TurmasResponseDTO> associarUm(@PathVariable UUID turma_id, @PathVariable UUID aluno_id){
		return service.associateStudantById(turma_id, aluno_id);
	}
	
	@PostMapping("/associar-varios/{turma_id}")
	public ResponseEntity<TurmasResponseDTO> associarVarios(@PathVariable UUID turma_id, @RequestBody TurmasAssociateRequestDTO aluno_ids){
		System.out.println(aluno_ids);
		return service.associateStudantsBatch(turma_id, aluno_ids);
	}
	
	@GetMapping("/{turma_id}/alunos")
	public ResponseEntity<List<AlunoResponseDTO>> listarAlunosDaTurma(@PathVariable UUID turma_id){
		return service.listAllStudantsByClass(turma_id);
	}
	
	@DeleteMapping("/{turma_id}/alunos/{aluno_id}")
	public ResponseEntity<?> removarAlunoDaTurma(@PathVariable UUID turma_id, @PathVariable UUID aluno_id){
		return service.deleteAlunoById(turma_id, aluno_id);
	}
}
