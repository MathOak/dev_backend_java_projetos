package com.gestaoclasse.class_manager.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoRequestDTO(
		@NotBlank(message = "Nome é obrigatório")
		String nome,
		
		@NotNull(message = "Data de nascimento é obrigatória")
		LocalDate data_nascimento,
		

		String matricula
		) {}
