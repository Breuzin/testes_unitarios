package br.ce.waquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.acquino.exceptions.NaoPodeDividirPorZeroException;


public class CalculadoraTest {

	private Calculadora calc;
	
	@Before
	public void setup() {
		calc = new Calculadora();
	}
	
	@Test
	public void somaDoisValores() {
		// Cenário
		int a = 5;
		int b = 3;
		
		// Ação
		int result = calc.somar(a, b);
		
		// Verificação
		Assert.assertEquals(8, result);
	}
	
	@Test
	public void subtrairDoisValores() {
		int a = 8;
		int b = 5;
		
		int result = calc.subtrair(a, b);
		
		Assert.assertEquals(3, result);
	}
	
	@Test
	public void dividirDoisValores() throws NaoPodeDividirPorZeroException {
		double a = 6;
		double b = 2;
		
		double result = calc.dividir(a, b);
		
		Assert.assertEquals(3, result, 0.01);
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void divisaoPorZeroExcecao() throws NaoPodeDividirPorZeroException {
		double a = 6;
		double b = 0;
				
		calc.dividir(a, b);
	}
}
