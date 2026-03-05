package com.gestaoclasse.class_manager.DTOs.Cursos;


import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Interfaces.OnCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CursosRequestDTO(
		@NotBlank(groups = {OnCreate.class},
		message = "Nome é do curso não pode estar em branco")
		@NotNull(message= "Nome do curso é obrigatório")
		String nome,
		
		@NotBlank(groups = {OnCreate.class},
		message = "Total de horas não poder estar em branco")
		@NotNull(message= "Total de horas do curso é obrigatória")
		int total_horas,
		
		String description
		) {
		public static Cursos toEntity(CursosRequestDTO dto) {
			Cursos object = new Cursos();
			object.setNome(dto.nome());
			object.setDescription(dto.description());
			object.setTotal_horas(dto.total_horas());
			return object;
		}
}
