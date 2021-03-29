package br.ufg.inf.aula4.ctrl.negocio;

import java.util.List;

import br.ufg.inf.aula4.ctrl.exception.AlunoException;
import br.ufg.inf.aula4.ctrl.exception.CursoException;
import br.ufg.inf.aula4.ctrl.exception.MatriculaException;
import br.ufg.inf.aula4.ctrl.exception.OfertaExection;
import br.ufg.inf.aula4.ctrl.exception.PessoaExection;
import br.ufg.inf.aula4.model.dao.MatriculaDAO;
import br.ufg.inf.aula4.model.entities.Matricula;

public class MatriculaNegocio {
	MatriculaDAO dao = new MatriculaDAO();

	public Matricula inserir(Matricula matricula) throws MatriculaException  {
		this.validarMatricula(matricula);
		dao.inserir(matricula);
		return matricula;
	}
	
	// READ
	public List<Matricula> buscaTodos() throws MatriculaException, AlunoException, CursoException, PessoaExection, OfertaExection {
		return dao.buscaTodos();	
	}
	
	public Matricula buscaPorId(Integer id) throws MatriculaException  {
		return dao.buscaPorId(id);
	}
	
	
	// UPDATE
	
	public Matricula alterar(Matricula aluno) throws AlunoException, CursoException, PessoaExection, MatriculaException  {		
		this.validarMatricula(aluno);
		return dao.alterar(aluno);
	}
	
	// DELETE
	
	public void excluir(Integer id) throws MatriculaException  {
		dao.excluir(id);
	}
	
	private void validarMatricula(Matricula matricula) throws MatriculaException  {
		if (matricula.getAluno().getIdAluno() <= 0) {
			throw new MatriculaException("Aluno inv�lido.");
		}
		else if (matricula.getOferta().getIdOferta() <= 0) {
			throw new MatriculaException("Oferta inv�lida.");
		}
	}
}
