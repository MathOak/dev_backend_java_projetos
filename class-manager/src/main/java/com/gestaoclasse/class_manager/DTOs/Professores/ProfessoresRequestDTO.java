package com.gestaoclasse.class_manager.DTOs.Professores;


import com.gestaoclasse.class_manager.DTOs.Professores.ProfessoresRequestDTO;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Interfaces.OnCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProfessoresRequestDTO(
		@NotBlank(groups = {OnCreate.class},
		message = "Nome é do curso não pode estar em branco")
		@NotNull(message= "Nome do curso é obrigatório")
		String nome,
		
		@NotBlank(groups = {OnCreate.class},
		message = "E-mail não poder estar em branco")
		@NotNull(message= "E-mail de horas do curso é obrigatória")
		String email,

		@NotBlank(groups = {OnCreate.class},
		message = "Especialidade não poder estar em branco")
		String especialidade
		) {
		public static Professores toEntity(ProfessoresRequestDTO dto) {
			Professores object = new Professores();
			object.setNome(dto.nome());
			object.setEmail(dto.email());
			object.setEspecialidade(dto.especialidade());
			return object;
		}
}
