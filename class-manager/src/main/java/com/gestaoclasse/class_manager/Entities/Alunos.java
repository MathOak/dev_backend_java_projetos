package com.gestaoclasse.class_manager.Entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

@Entity
public class Alunos {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", updatable = false,
	nullable = false, unique = true, columnDefinition = "BINARY(16)")
	private UUID id;
	
	private String matricula;
	private String nome;
	private String data_nascimento;
	
	
	
	public UUID getId() {
		return id;
	}



	public String getMatricula() {
		return matricula;
	}



	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getData_nascimento() {
		return data_nascimento;
	}



	public void setData_nascimento(String data_nascimento) {
		this.data_nascimento = data_nascimento;
	}



	@PrePersist
	public void prePersist() {
		if (id == null) {
			id = UUID.randomUUID();
		}
	}
}
