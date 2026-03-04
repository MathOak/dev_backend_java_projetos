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
import com.gestaoclasse.class_manager.Services.AlunosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("alunos")
public class AlunosController {

	@Autowired
	private AlunosService service;
	
	@GetMapping
	public ResponseEntity<List<AlunoResponseDTO>> listarTodos() {
		ArrayList<Alunos> alunos =new ArrayList<>(service.getAllAlunos());
		ArrayList<AlunoResponseDTO> newAlunosList = new ArrayList();
		alunos.forEach(aluno->{
			newAlunosList.add(toResponse(aluno));
		});
	
		return ResponseEntity.status(HttpStatus.OK).body(newAlunosList);
	}
	
	@PostMapping
	public ResponseEntity<AlunoResponsePostPutDTO> criarAluno(@Valid @RequestBody AlunoRequestDTO body) {
		Alunos aluno = toEntity(body);
		Alunos newAluno = service.saveOne(aluno);
		return ResponseEntity.status(HttpStatus.CREATED).body(toResponsePostPut(newAluno));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id){
		return service.deleteAlunoById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AlunoResponsePostPutDTO> atualizar(@PathVariable UUID id, @Valid @RequestBody AlunoRequestDTO body){
		Alunos dados = toEntity(body);
		return service.updateAlunoById(id, dados);
	}
	
	private Alunos toEntity(AlunoRequestDTO dto) {
		Alunos aluno = new Alunos();
		aluno.setNome(dto.nome());
		aluno.setData_nascimento(dto.data_nascimento());
		aluno.setMatricula(dto.matricula());
		return aluno;
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
	private AlunoResponseDTO toResponse(Alunos aluno) {
		return new AlunoResponseDTO(
				aluno.getId(),
				aluno.getNome(),
				aluno.getData_nascimento(),
				aluno.getMatricula());
	}

}
