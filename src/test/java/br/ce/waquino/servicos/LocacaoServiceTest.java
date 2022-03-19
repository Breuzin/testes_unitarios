package br.ce.waquino.servicos;

import java.util.Date;

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
		Assert.assertEquals(60.0, locacao.getValor(), 0.01);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		//assert(locacao.getValor() == filme.getPrecoLocacao()) : "False";
		//assert(locacao.getDataRetorno().getDay() == DataUtils.adicionarDias(locacao.getDataLocacao(), 1).getDay()) : "Datas diferentes";
	}
}
