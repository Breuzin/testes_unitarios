package br.ce.acquino.exceptions;

public class NaoPodeDividirPorZeroException extends Exception {
	private static final long serialVersionUID = 7782663779339434847L;
	
	public NaoPodeDividirPorZeroException(String msg) {
		super(msg);
	}
}
