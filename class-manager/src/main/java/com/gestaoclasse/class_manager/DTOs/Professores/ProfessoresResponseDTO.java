package com.gestaoclasse.class_manager.DTOs.Professores;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.gestaoclasse.class_manager.Entities.Professores;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProfessoresResponseDTO(
		UUID id,
		String nome,
		String email,
		String especialidade,
		LocalDateTime request_time,
		LocalDateTime created_at,
		LocalDateTime updated_at
		)
	{

	public static ProfessoresResponseDTO fromEntity(Professores object) {
		return new ProfessoresResponseDTO(
				object.getId(),
				object.getNome(),
				object.getEmail(),
				object.getEspecialidade(),
				null,
				object.getCreated_at(),
				object.getUpdated_at()
				);
	}

	public static ProfessoresResponseDTO fromEntityWithRequestTime(Professores object) {
		return new ProfessoresResponseDTO(
				object.getId(),
				object.getNome(),
				object.getEmail(),
				object.getEspecialidade(),
				LocalDateTime.now(),
				object.getCreated_at(),
				object.getUpdated_at()
				);
	}
}
