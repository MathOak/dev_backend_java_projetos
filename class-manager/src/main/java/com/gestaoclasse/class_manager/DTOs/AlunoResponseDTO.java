package com.gestaoclasse.class_manager.DTOs;

import java.time.LocalDate;
import java.util.UUID;

public record AlunoResponseDTO(
		UUID id,
		String nome,
		LocalDate data_nascimento,
		String matricula)
{}
