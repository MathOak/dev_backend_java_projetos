package com.gestaoclasse.class_manager.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.AlunosDTOs.AlunoResponseDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasAssociateRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasResponseDTO;
import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.AlunosRepository;
import com.gestaoclasse.class_manager.Repositories.CursosRepository;
import com.gestaoclasse.class_manager.Repositories.ProfessoresRepository;
import com.gestaoclasse.class_manager.Repositories.TurmasRepository;

@Service
public class TurmasService {
	@Autowired
	private TurmasRepository repository;

    @Autowired
    private CursosRepository cursosRepository;

    @Autowired
    private ProfessoresRepository professoresRepository;
    
    @Autowired
    private AlunosRepository alunosRepository;
	
	public ResponseEntity<List<TurmasResponseDTO>> getAll(){
		 List<TurmasResponseDTO> newObject = repository.findAll().stream().map(
				object ->{
					return TurmasResponseDTO.fromEntity(object);
				}).collect(Collectors.toList());
		 
		return ResponseEntity.status(HttpStatus.OK).body(newObject);
	}
	
	public ResponseEntity<TurmasResponseDTO> saveOne(TurmasRequestDTO dto) {
        Cursos curso = cursosRepository.findById(dto.curso_id())
                .orElseThrow(() -> new ResourceNotFoundException("Curso nao existe!"));

        Professores professor = professoresRepository.findById(dto.professor_id())
            .orElseThrow(() -> new ResourceNotFoundException("Professor nao existe!"));

        Turmas object = TurmasRequestDTO.toEntity(dto);
        object.setCurso(curso);
        object.setProfessor(professor);

		TurmasResponseDTO newObject = TurmasResponseDTO.fromEntityWithRequestTime(repository.save(object));
		return ResponseEntity.status(HttpStatus.CREATED).body(newObject);
	}
	
	public ResponseEntity<?> deleteAlunoById(UUID id){
		return repository.findById(id).map(object -> {
			repository.delete(object);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Turma não existe!"));
		
	}
	
	public ResponseEntity<TurmasResponseDTO> updateById(UUID id, Turmas body){
		return repository.findById(id).
				map(object -> {
					if(!object.getCurso().equals(body.getCurso()))
						object.setCurso((body.getCurso()));

					if(!object.getProfessor().equals(body.getProfessor()))
						object.setProfessor(body.getProfessor());

					if(!object.getCodigo().equals(body.getCodigo()))
						object.setCodigo(body.getCodigo());

					Turmas newObject = repository.save(object);
					
					return ResponseEntity.ok().body(TurmasResponseDTO.fromEntityWithRequestTime(newObject));
				}).orElse(ResponseEntity.notFound().build());
	}
	
	public ResponseEntity<TurmasResponseDTO> associateStudantById(UUID turma_id, UUID aluno_id){
		Turmas turma = repository.findById(turma_id)
				.orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada!"));
		
		Alunos aluno = alunosRepository.findById(aluno_id)
				.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado!"));
		
		boolean jaExiste = turma.getAlunos().stream().anyMatch(a -> a.getId().equals(aluno_id));
		
		if(jaExiste) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
		}
		
		turma.getAlunos().add(aluno);
		Turmas newObject = repository.save(turma);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(TurmasResponseDTO.fromEntityWithRequestTime(newObject));
	}
	
	public ResponseEntity<TurmasResponseDTO> associateStudantsBatch(UUID turma_id, TurmasAssociateRequestDTO aluno_ids){
		Turmas turma = repository.findById(turma_id)
				.orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada!"));
		System.out.println(aluno_ids);
		
		List<Alunos> alunoError =
		aluno_ids.aluno_ids().stream().map(aluno_id -> {
			Alunos aluno = alunosRepository.findById(aluno_id)
					.orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado!"));
			
			boolean jaExiste = turma.getAlunos().stream().anyMatch(a -> a.getId().equals(aluno_id));
			
			if(jaExiste) {
				return aluno;
			}
			
			turma.getAlunos().add(aluno);
			return aluno;
		}).collect(Collectors.toList());
		
		if(alunoError.size() > 0) {}

		Turmas newObject = repository.save(turma);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(TurmasResponseDTO.fromEntityWithRequestTime(newObject));
	}
	
	public  ResponseEntity<List<AlunoResponseDTO>> listAllStudantsByClass(UUID turma_id){
		Turmas turma = repository.findById(turma_id)
				.orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada!"));
		
		List<AlunoResponseDTO> alunos= turma.getAlunos()
				.stream()
				.map(AlunoResponseDTO::fromEntity)
				.collect(Collectors.toList());
		return ResponseEntity.ok(alunos);
	}
	
	public ResponseEntity<?> deleteAlunoById(UUID turma_id,UUID aluno_id){
		Turmas turma = repository.findById(turma_id)
				.orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada!"));
		
		boolean removido = turma.getAlunos().removeIf(a -> a.getId().equals(aluno_id));
		
		 if (!removido) {
		        throw new ResourceNotFoundException("Aluno não está nesta turma!");
		    }
		

	    repository.save(turma);
	    return ResponseEntity.noContent().build();
	}

}
