package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoService {
	
	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		//Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);
		
		//Salvando a locacao...	
		//TODO adicionar m√©todo para salvar
		
		return locacao;
	}

	@Test
	public void test() {
		
		//Dado
		Usuario usuario = new Usuario("Kleber");
		Filme filme = new Filme("Interestelar", 6, 60.0);
		LocacaoService locacaoService = new LocacaoService();
		
		//Quando
		Locacao locacao = locacaoService.alugarFilme(usuario, filme);
		
		//Ent„o
		Assert.assertTrue(locacao.getValor() == 60.0);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		//assert(locacao.getValor() == filme.getPrecoLocacao()) : "False";
		//assert(locacao.getDataRetorno().getDay() == DataUtils.adicionarDias(locacao.getDataLocacao(), 1).getDay()) : "Datas diferentes";
	}
}