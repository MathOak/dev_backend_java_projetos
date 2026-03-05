package com.gestaoclasse.class_manager.Services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasRequestDTO;
import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasResponseDTO;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
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

    public ResponseEntity<List<TurmasResponseDTO>> getAll() {
        List<TurmasResponseDTO> newObject = repository.findAll().stream().map(
                object -> {
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

    public ResponseEntity<?> deleteAlunoById(UUID id) {
        return repository.findById(id).map(object -> {
            repository.delete(object);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Turma não existe!"));

    }

    public ResponseEntity<TurmasResponseDTO> updateById(UUID id, Turmas body) {
        return repository.findById(id).
                map(object -> {
                    if (!object.getCurso().equals(body.getCurso())) {
                        object.setCurso((body.getCurso()));
                    }

                    if (!object.getProfessor().equals(body.getProfessor())) {
                        object.setProfessor(body.getProfessor());
                    }

                    if (!object.getCodigo().equals(body.getCodigo())) {
                        object.setCodigo(body.getCodigo());
                    }

                    Turmas newObject = repository.save(object);

                    return ResponseEntity.ok().body(TurmasResponseDTO.fromEntityWithRequestTime(newObject));
                }).orElse(ResponseEntity.notFound().build());
    }

}
