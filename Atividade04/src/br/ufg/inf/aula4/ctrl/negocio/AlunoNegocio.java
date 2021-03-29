package br.ufg.inf.aula4.ctrl.negocio;

import java.util.Calendar;
import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.model.dao.AlunoDAO;
import br.ufg.inf.aula4.model.entities.Aluno;

public class AlunoNegocio {
	AlunoDAO dao = new AlunoDAO();

	public Aluno inserir(Aluno aluno) throws AlunoException, CursoException, PessoaExection {
		this.validarAluno(aluno);
		dao.inserir(aluno);
		return aluno;
	}
	
	// READ
	public List<Aluno> buscaTodos() throws AlunoException, CursoException, PessoaExection{
		return dao.buscaTodos();	
	}
	
	public Aluno buscaPorId(Integer id) throws AlunoException, CursoException, PessoaExection {
		return dao.buscaPorId(id);
	}
	
	
	// UPDATE
	
	public Aluno alterar(Aluno aluno) throws AlunoException, CursoException, PessoaExection {		
		this.validarAluno(aluno);
		return dao.alterar(aluno);
	}
	
	// DELETE
	
	public void excluir(Integer id) throws AlunoException {
		dao.excluir(id);
	}
	
	private void validarAluno(Aluno aluno) throws AlunoException, CursoException, PessoaExection {
		Calendar calHoje = Calendar.getInstance();
		Calendar calNascimento = Calendar.getInstance();
		calNascimento.setTime(aluno.getPessoa().getDtNascimento());
		if (!aluno.getAtivo()) {
			throw new AlunoException("Aluno não ativo.");
		}
		else if (aluno.getCurso().getNmCurso() == null || aluno.getCurso().getNmCurso().length() == 0) {
			throw new CursoException("Nome da disciplina é obrigatório.");
		}
		else if (aluno.getPessoa().getNmPessoa() == null || aluno.getPessoa().getNmPessoa().length() == 0) {
			throw new PessoaExection("Nome da pessoa é obrigatório.");
		}
		else if (aluno.getPessoa().getCpf().toString().length() != 11) {
			throw new PessoaExection("CPF deve ter pelo menos 11 algarismos.");
		}
		else if (calHoje.after(calNascimento)) {
			throw new PessoaExection("Data de Nascimento deve ser maior que hoje.");
		}
	}
}
