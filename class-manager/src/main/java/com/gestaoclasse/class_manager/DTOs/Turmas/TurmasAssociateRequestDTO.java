package com.gestaoclasse.class_manager.DTOs.Turmas;

import java.util.List;
import java.util.UUID;

public record TurmasAssociateRequestDTO(
		List<UUID> aluno_ids
		) {
}
