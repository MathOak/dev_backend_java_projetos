package com.gestaoclasse.class_manager.DTOs.Turmas;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Entities.Turmas;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TurmasResponseDTO(
		UUID id,
		String codigo,
		Cursos curso,
		Professores professor,
		LocalDateTime request_time,
		LocalDateTime created_at,
		LocalDateTime updated_at
		)
	{

	public static TurmasResponseDTO fromEntity(Turmas object) {
		return new TurmasResponseDTO(
				object.getId(),
				object.getCodigo(),
				object.getCurso(),
				object.getProfessor(),
				null,
				object.getCreated_at(),
				object.getUpdated_at()
				);
	}

	public static TurmasResponseDTO fromEntityWithRequestTime(Turmas object) {
		return new TurmasResponseDTO(
				object.getId(),
				object.getCodigo(),
				object.getCurso(),
				object.getProfessor(),
				LocalDateTime.now(),
				object.getCreated_at(),
				object.getUpdated_at()
				);
	}
}
