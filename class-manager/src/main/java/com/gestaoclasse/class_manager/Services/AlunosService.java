package com.gestaoclasse.class_manager.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.AlunosDTOs.AlunoResponseDTO;
import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.AlunosRepository;

@Service
public class AlunosService {

    @Autowired
    private AlunosRepository repository;

    public ResponseEntity<List<AlunoResponseDTO>> getAll() {
        List<AlunoResponseDTO> newObject = repository.findAll().stream().map(
                object -> {
                    return AlunoResponseDTO.fromEntity(object);
                }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(newObject);
    }

    public ResponseEntity<AlunoResponseDTO> saveOne(Alunos object) {
        AlunoResponseDTO newObject = AlunoResponseDTO.fromEntityWithRequestTime(repository.save(object));
        return ResponseEntity.status(HttpStatus.CREATED).body(newObject);
    }

    public ResponseEntity<?> deleteById(UUID id) {
        return repository.findById(id).map(object -> {
            repository.delete(object);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Aluno não existe!"));

    }

    public ResponseEntity<AlunoResponseDTO> updateById(UUID id, Alunos body) {
        return repository.findById(id).
                map(object -> {
                    if (!object.getNome().equals(body.getNome())) {
                        object.setNome(body.getNome());
                    }

                    if (!object.getData_nascimento().equals(body.getData_nascimento())) {
                        object.setData_nascimento(body.getData_nascimento());
                    }

                    if (!object.getMatricula().equals(body.getMatricula())) {
                        object.setMatricula(body.getMatricula());
                    }

                    Alunos newObject = repository.save(object);

                    return ResponseEntity.ok().body(AlunoResponseDTO.fromEntityWithRequestTime(newObject));
                }).orElse(ResponseEntity.notFound().build());
    }

}
