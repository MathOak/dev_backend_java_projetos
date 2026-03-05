package com.gestaoclasse.class_manager.DTOs.Turmas;


import java.util.UUID;


import com.gestaoclasse.class_manager.DTOs.Turmas.TurmasRequestDTO;
import com.gestaoclasse.class_manager.Entities.Turmas;
import com.gestaoclasse.class_manager.Interfaces.OnCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record TurmasRequestDTO(
		@NotBlank(groups = {OnCreate.class},
		message = "Codigo é da turma não pode estar em branco")
		@NotNull(message= "Codigo da turma é obrigatório")
		String codigo,
		
		@NotBlank(groups = {OnCreate.class},
		message = "Curso não poder estar em branco")
		@NotNull(message= "Curso é obrigatória")
		UUID curso_id,

		@NotBlank(groups = {OnCreate.class},
		message = "Professor não poder estar em branco")
		@NotNull(message= "Professor é obrigatório")
		UUID professor_id
		) {
		public static Turmas toEntity(TurmasRequestDTO dto) {
			Turmas object = new Turmas();
			object.setCodigo(dto.codigo());
			return object;
		}
}
