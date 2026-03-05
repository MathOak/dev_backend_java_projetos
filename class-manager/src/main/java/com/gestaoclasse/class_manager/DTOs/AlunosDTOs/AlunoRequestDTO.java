package com.gestaoclasse.class_manager.DTOs.AlunosDTOs;

import java.time.LocalDate;

import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Interfaces.OnCreate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AlunoRequestDTO(
		@NotBlank(groups = {OnCreate.class}, message = "Nome é obrigatório")
		String nome,
		
		@NotNull(groups = {OnCreate.class}, message = "Data de nascimento é obrigatória")
		LocalDate data_nascimento,
		
		@NotBlank(groups = {OnCreate.class})
		String matricula,
		
		String escolaridade
		) {
	
	public static Alunos toEntity(AlunoRequestDTO dto) {
		Alunos object = new Alunos();
		object.setNome(dto.nome());
		object.setData_nascimento(dto.data_nascimento());
		object.setMatricula(dto.matricula());
		object.setEscolaridade(dto.escolaridade());
		return object;
	}
}
