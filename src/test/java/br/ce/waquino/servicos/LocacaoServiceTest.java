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

import br.ce.acquino.exceptions.FilmeSemEstoqueException;
import br.ce.acquino.exceptions.LocadoraException;
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

	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 0, 60.0);
		LocacaoService locacaoService = new LocacaoService();

		// When
		locacaoService.alugarFilme(usuario, filme);
		
		System.out.println("Forma Elegante");
	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		
		Filme filme = new Filme("Interestelar", 2, 60.0);
		LocacaoService locacaoService = new LocacaoService();

		try {
			locacaoService.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
		System.out.println("Forma Robusta");
	}
	
	@Test
	public void testLocacao_FilveVazio() throws FilmeSemEstoqueException, LocadoraException {
		LocacaoService locacaoService = new LocacaoService();
		Usuario usuario = new Usuario("Kleber");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		locacaoService.alugarFilme(usuario, null);
		
		System.out.println("Forma Nova");
	}
}
