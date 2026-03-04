package com.gestaoclasse.class_manager.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity @Table(name="cursos")
@Getter @Setter
public class Cursos extends BaseEntity {
	private String nome;
	private String description;
	private int total_horas;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTotal_horas() {
		return total_horas;
	}
	public void setTotal_horas(int total_horas) {
		this.total_horas = total_horas;
	}
	
}
