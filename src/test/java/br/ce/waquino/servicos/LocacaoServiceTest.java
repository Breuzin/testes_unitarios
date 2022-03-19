package br.ce.waquino.servicos;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.servicos.LocacaoService;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTest {

	@Test
	public void test() {
		
		//Dado
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 6, 60.0);
		LocacaoService locacaoService = new LocacaoService();
		
		//Quando
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		
		//Então
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.equalTo(5.0)));
		Assert.assertThat(locacao.getValor(), CoreMatchers.is(CoreMatchers.not(70.0)));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), CoreMatchers.is(true));
		Assert.assertThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), CoreMatchers.is(true));
		//assert(locacao.getValor() == filme.getPrecoLocacao()) : "False";
		//assert(locacao.getDataRetorno().getDay() == DataUtils.adicionarDias(locacao.getDataLocacao(), 1).getDay()) : "Datas diferentes";
	}
}
