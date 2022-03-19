package br.ce.waquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.isMesmaData;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Test
	public void testLocacao() {
		
		//Dado
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 6, 60.0);
		LocacaoService locacaoService = new LocacaoService();
		
		//Quando
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		
		//Então
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(false));
		//assert(locacao.getValor() == filme.getPrecoLocacao()) : "False";
		//assert(locacao.getDataRetorno().getDay() == DataUtils.adicionarDias(locacao.getDataLocacao(), 1).getDay()) : "Datas diferentes";
	}
}
