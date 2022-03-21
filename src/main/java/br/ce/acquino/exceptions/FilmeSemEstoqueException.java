package br.ce.acquino.exceptions;

public class FilmeSemEstoqueException extends Exception{
	private static final long serialVersionUID = -763328745670801483L;

	public FilmeSemEstoqueException(String msg) {
		super(msg);
	}
}
