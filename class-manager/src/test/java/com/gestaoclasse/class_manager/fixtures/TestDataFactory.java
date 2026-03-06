package com.gestaoclasse.class_manager.fixtures;

import java.time.LocalDate;
import java.util.UUID;

import com.gestaoclasse.class_manager.Entities.Alunos;
import com.gestaoclasse.class_manager.Entities.Cursos;
import com.gestaoclasse.class_manager.Entities.Professores;
import com.gestaoclasse.class_manager.Entities.Turmas;

public final class TestDataFactory {

	private TestDataFactory() {}
	
    public static Alunos aluno(String nome) {
        Alunos a = new Alunos();
        a.setId(UUID.randomUUID());
        a.setNome(nome);
        a.setMatricula("MAT-" + nome.toUpperCase().replace(" ", "_"));
        a.setData_nascimento(LocalDate.of(2010, 1, 1));
        a.setEscolaridade("Fundamental");
        return a;
    }

    public static Cursos curso(String nome) {
        Cursos c = new Cursos();
        c.setId(UUID.randomUUID());
        c.setNome(nome);
        c.setDescription("Descricao " + nome);
        c.setTotal_horas(60);
        return c;
    }

    public static Professores professor(String nome) {
        Professores p = new Professores();
        p.setId(UUID.randomUUID());
        p.setNome(nome);
        p.setEmail(nome.toLowerCase().replace(" ", ".") + "@escola.com");
        p.setEspecialidade("Matematica");
        return p;
    }

    public static Turmas turma(String codigo, Cursos curso, Professores professor) {
        Turmas t = new Turmas();
        t.setId(UUID.randomUUID());
        t.setCodigo(codigo);
        t.setCurso(curso);
        t.setProfessor(professor);
        return t;
    }
    
}
