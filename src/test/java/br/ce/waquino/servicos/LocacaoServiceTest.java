package br.ce.waquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	@Test
	public void testLocacao() throws Exception {

		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 2, 60.0);
		LocacaoService locacaoService = new LocacaoService();

		// When
		Locacao locacao;
		locacao = locacaoService.alugarFilme(usuario, filme);

		// Verification
		error.checkThat(locacao.getValor(), is(equalTo(60.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}

	@Test(expected=Exception.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 0, 60.0);
		LocacaoService locacaoService = new LocacaoService();

		// When
		locacaoService.alugarFilme(usuario, filme);
	}
	
	@Test
	public void testLocacao_filmeSemEstoque2(){
		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 0, 60.0);
		LocacaoService locacaoService = new LocacaoService();

		// When
		try {
			locacaoService.alugarFilme(usuario, filme);
			Assert.fail("Deveria ter lançado uma exceção");
		} catch (Exception e) {
			Assert.assertThat(e.getMessage(), is("Filme sem estoque"));
		}
	}
	
	@Test
	public void testLocacao_filmeSemEstoque_3() throws Exception {
		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 0, 60.0);
		LocacaoService locacaoService = new LocacaoService();

		exception.expect(Exception.class);
		exception.expectMessage("Filme sem estoque");
		
		// When
		locacaoService.alugarFilme(usuario, filme);	
	}
}
