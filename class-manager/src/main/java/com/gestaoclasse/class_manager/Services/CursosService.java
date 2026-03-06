package com.gestaoclasse.class_manager.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.Cursos.CursosResponseDTO;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.CursosRepository;


@Service
public class CursosService {
	@Autowired
	private CursosRepository repository;
	
	public ResponseEntity<List<CursosResponseDTO>> getAll(){
		 List<CursosResponseDTO> newObject = repository.findAll().stream().map(
				object ->{
					return CursosResponseDTO.fromEntity(object);
				}).collect(Collectors.toList());
		 
		return ResponseEntity.status(HttpStatus.OK).body(newObject);
	}
	
	public ResponseEntity<CursosResponseDTO> saveOne(Cursos object) {
		CursosResponseDTO newObject = CursosResponseDTO.fromEntityWithRequestTime(repository.save(object));
		return ResponseEntity.status(HttpStatus.CREATED).body(newObject);
	}
	
	public ResponseEntity<?> deleteAlunoById(UUID id){
		return repository.findById(id).map(object -> {
			repository.delete(object);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Curso não existe!"));
		
	}
	
	public ResponseEntity<CursosResponseDTO> updateById(UUID id, Cursos body){
		return repository.findById(id).
				map(object -> {
					if(!object.getNome().equals(body.getNome()))
						object.setNome(body.getNome());

					if(!object.getDescription().equals(body.getDescription()))
						object.setDescription(body.getDescription());

					if(object.getTotal_horas() !=(body.getTotal_horas()))
						object.setTotal_horas(body.getTotal_horas());

					Cursos newObject = repository.save(object);
					
					return ResponseEntity.ok().body(CursosResponseDTO.fromEntityWithRequestTime(newObject));
				}).orElse(ResponseEntity.notFound().build());
	}

}
