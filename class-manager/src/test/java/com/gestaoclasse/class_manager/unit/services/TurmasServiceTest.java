package com.gestaoclasse.class_manager.unit.services;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Repositories.AlunosRepository;
import com.gestaoclasse.class_manager.Repositories.CursosRepository;
import com.gestaoclasse.class_manager.Repositories.ProfessoresRepository;
import com.gestaoclasse.class_manager.Repositories.TurmasRepository;
import com.gestaoclasse.class_manager.Services.TurmasService;
import com.gestaoclasse.class_manager.fixtures.TestDataFactory;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class TurmasServiceTest {

    @Mock private TurmasRepository repository;
    @Mock private CursosRepository cursosRepository;
    @Mock private ProfessoresRepository  professoresRepository;
    @Mock private AlunosRepository alunosRepository;

    @InjectMocks
    private TurmasService service;
    
    @Test
    void associateStudantById_shouldAssociateAndReturn202() {
        Cursos curso = TestDataFactory.curso("Java");
        Professores prof = TestDataFactory.professor("Maria");
        Turmas turma = TestDataFactory.turma("TURMA-JAVA", curso, prof);
        Alunos aluno = TestDataFactory.aluno("Ana");
        

        when(repository.findById(turma.getId())).thenReturn(Optional.of(turma));
        when(alunosRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));
        when(repository.save(any(Turmas.class))).thenAnswer(inv -> inv.getArgument(0));

        ResponseEntity<?> response = service.associateStudantById(turma.getId(), aluno.getId());
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(turma.getAlunos().contains(aluno));
        verify(repository).save(turma);

    }
    @Test
    void associateStudantById_whenDuplicatedStudantOnClass_shouldReturn401() {
        Cursos curso = TestDataFactory.curso("Java");
        Professores prof = TestDataFactory.professor("Maria");
        Turmas turma = TestDataFactory.turma("TURMA-JAVA", curso, prof);
        Alunos aluno = TestDataFactory.aluno("Ana");
        turma.setAlunos(Set.of(aluno));

        when(repository.findById(turma.getId())).thenReturn(Optional.of(turma));
        when(alunosRepository.findById(aluno.getId())).thenReturn(Optional.of(aluno));

        ResponseEntity<?> response = service.associateStudantById(turma.getId(), aluno.getId());

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        verify(repository, never()).save(any());
        
    }
    
}
