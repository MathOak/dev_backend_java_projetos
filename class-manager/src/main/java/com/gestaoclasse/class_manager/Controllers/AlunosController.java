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

import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Services.AlunosService;

@RestController
@RequestMapping("alunos")
public class AlunosController {

	@Autowired
	private AlunosService service;
	
	@GetMapping
	public List<Alunos> listarTodos() {
		return service.getAllAlunos();
	}
	
	@PostMapping
	public Alunos criarAluno(@RequestBody Alunos aluno) {
		return service.saveOne(aluno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Alunos> atualizar(@PathVariable UUID id,@RequestBody Alunos dados){
		return service.updateAlunoById(id, dados);
	}
	

}
