package br.ce.waquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
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

	private LocacaoService service;

	private static List<Filme> filmes;

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setup() {
		service = new LocacaoService();
	}

	@Test
	public void testLocacao() throws Exception {

		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 1, 60.0);
		Filme filme2 = new Filme("Interestelar2", 1, 70.0);
		filmes = Arrays.asList(filme, filme2);

		// When
		Locacao locacao;
		locacao = service.alugarFilme(usuario, filmes);

		// Verification
		error.checkThat(locacao.getTotalValue(), is(equalTo(130.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}

	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception {
		// Data
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 0, 60.0);
		Filme filme2 = new Filme("Interestelar2", 2, 70.0);
		filmes = Arrays.asList(filme, filme2);

		// When
		service.alugarFilme(usuario, filmes);
	}

	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException {
		Filme filme = new Filme("Interestelar", 2, 60.0);
		Filme filme2 = new Filme("Interestelar2", 2, 70.0);
		filmes = Arrays.asList(filme, filme2);

		System.out.println();
		try {
			service.alugarFilme(null, filmes);
			Assert.fail();
		} catch (LocadoraException e) {
			Assert.assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}

	@Test
	public void testLocacao_FilveVazio() throws FilmeSemEstoqueException, LocadoraException {
		Usuario usuario = new Usuario("Kleber");

		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");

		service.alugarFilme(usuario, null);
	}
}
