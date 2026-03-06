package com.gestaoclasse.class_manager.unit.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Exceptions.ResourceNotFoundException;
import com.gestaoclasse.class_manager.Repositories.AlunosRepository;
import com.gestaoclasse.class_manager.Services.AlunosService;
import com.gestaoclasse.class_manager.fixtures.TestDataFactory;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class AlunosServiceTest {

	@Mock
	private AlunosRepository repository;
	
	@InjectMocks
	private AlunosService service;
	
	@Test
	void getAll_andShouldReturn200WithList() {
		when(repository.findAll()).thenReturn(List.of(
                TestDataFactory.aluno("Ana"),
                TestDataFactory.aluno("Bruno")
			));
		
		ResponseEntity<?> response = service.getAll();
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	@Test
	void deleteById_whenNotExist_shouldThrowNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());
        
        assertThrows(ResourceNotFoundException.class, () -> service.deleteById(id));
        verify(repository, never()).delete(any());

	}
	
	
	@Test
	void saveOne_shouldReturn201() {
        Alunos aluno = TestDataFactory.aluno("Carlos");
        when(repository.save(any(Alunos.class))).thenReturn(aluno);

        ResponseEntity<?> response = service.saveOne(aluno);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(repository, times(1)).save(any(Alunos.class));
	}
	
}
