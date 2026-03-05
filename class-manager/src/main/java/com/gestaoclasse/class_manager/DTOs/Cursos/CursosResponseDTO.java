package com.gestaoclasse.class_manager.DTOs.Cursos;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gestaoclasse.class_manager.Entities.Cursos;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record CursosResponseDTO(
		UUID id,
		String nome,
		int total_horas,
		String description,
		LocalDateTime request_time,
		LocalDateTime created_at,
		LocalDateTime updated_at
		)
	{

	public static CursosResponseDTO fromEntity(Cursos object) {
		return new CursosResponseDTO(
				object.getId(),
				object.getNome(),
				object.getTotal_horas(),
				object.getDescription(),
				null,
				object.getCreated_at(),
				object.getUpdated_at()
				);
	}

	public static CursosResponseDTO fromEntityWithRequestTime(Cursos object) {
		return new CursosResponseDTO(
				object.getId(),
				object.getNome(),
				object.getTotal_horas(),
				object.getDescription(),
				LocalDateTime.now(),
				object.getCreated_at(),
				object.getUpdated_at()
				);
	}
}
