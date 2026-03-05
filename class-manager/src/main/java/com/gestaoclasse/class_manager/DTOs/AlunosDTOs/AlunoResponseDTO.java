package com.gestaoclasse.class_manager.DTOs.AlunosDTOs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gestaoclasse.class_manager.Entities.Alunos;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AlunoResponseDTO(
		UUID id,
		String nome,
		LocalDate data_nascimento,
		String matricula,
		LocalDateTime request_time,
		LocalDateTime created_at,
		LocalDateTime updated_at
		)
	{

	public static AlunoResponseDTO fromEntity(Alunos aluno) {
		return new AlunoResponseDTO(
				aluno.getId(),
				aluno.getNome(),
				aluno.getData_nascimento(),
				aluno.getMatricula(),
				null,
				aluno.getCreated_at(),
				aluno.getUpdated_at()
				);
	}

	public static AlunoResponseDTO fromEntityWithRequestTime(Alunos aluno) {
		return new AlunoResponseDTO(
				aluno.getId(),
				aluno.getNome(),
				aluno.getData_nascimento(),
				aluno.getMatricula(),
				LocalDateTime.now(),
				aluno.getCreated_at(),
				aluno.getUpdated_at()
				);
	}
}
