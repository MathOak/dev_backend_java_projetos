package com.gestaoclasse.class_manager.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.Professores.ProfessoresResponseDTO;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.ProfessoresRepository;

@Service
public class ProfessoresService {
	@Autowired
	private ProfessoresRepository repository;
	
	public ResponseEntity<List<ProfessoresResponseDTO>> getAll(){
		 List<ProfessoresResponseDTO> newObject = repository.findAll().stream().map(
				object ->{
					return ProfessoresResponseDTO.fromEntity(object);
				}).collect(Collectors.toList());
		 
		return ResponseEntity.status(HttpStatus.OK).body(newObject);
	}
	
	public ResponseEntity<ProfessoresResponseDTO> saveOne(Professores object) {
		ProfessoresResponseDTO newObject = ProfessoresResponseDTO.fromEntityWithRequestTime(repository.save(object));
		return ResponseEntity.status(HttpStatus.CREATED).body(newObject);
	}
	
	public ResponseEntity<?> deleteAlunoById(UUID id){
		return repository.findById(id).map(object -> {
			repository.delete(object);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Curso não existe!"));
		
	}
	
	public ResponseEntity<ProfessoresResponseDTO> updateById(UUID id, Professores body){
		return repository.findById(id).
				map(object -> {
					if(!object.getNome().equals(body.getNome()))
						object.setNome(body.getNome());

					if(!object.getEmail().equals(body.getEmail()))
						object.setEmail(body.getEmail());

					if(!object.getEspecialidade().equals(body.getEspecialidade()))
						object.setEspecialidade(body.getEspecialidade());

					Professores newObject = repository.save(object);
					
					return ResponseEntity.ok().body(ProfessoresResponseDTO.fromEntityWithRequestTime(newObject));
				}).orElse(ResponseEntity.notFound().build());
	}

}
