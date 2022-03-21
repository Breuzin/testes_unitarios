package br.ce.waquino.servicos;

import br.ce.acquino.exceptions.NaoPodeDividirPorZeroException;

public class Calculadora {

	public int somar(int num1, int num2) {
		return num1 + num2;
	}

	public int subtrair(int a, int b) {
		return a - b;
	}

	public double dividir(double a, double b) throws NaoPodeDividirPorZeroException {
		if (b == 0) {
			throw new NaoPodeDividirPorZeroException("Não pode dividir por zero");
		}
		return a / b;
	}
}
