package com.gestaoclasse.class_manager.Entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Turmas extends BaseEntity {
	private String codigo;//QUA03062026U057
	
	@ManyToOne
	@JoinColumn(name = "curso_id")
	private Cursos curso;

	@ManyToOne
	@JoinColumn(name = "professor_id")
	private Professores professor;
	
	@ManyToMany
	@JoinTable(
			name = "turma_estudantes",
			joinColumns = @JoinColumn(name = "turma_id"),
			inverseJoinColumns = @JoinColumn(name = "estudante_id")
	)
	private Set<Alunos> alunos = new HashSet<>();

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Cursos getCurso() {
		return curso;
	}

	public void setCurso(Cursos curso) {
		this.curso = curso;
	}

	public Professores getProfessor() {
		return professor;
	}

	public void setProfessor(Professores professor) {
		this.professor = professor;
	}

	public Set<Alunos> getAlunos() {
		return alunos;
	}

	public void setAlunos(Set<Alunos> alunos) {
		this.alunos = alunos;
	}
	
	
}
