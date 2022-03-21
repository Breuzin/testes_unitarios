package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;
import java.util.List;

import br.ce.acquino.exceptions.FilmeSemEstoqueException;
import br.ce.acquino.exceptions.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException  {

		double valor = 0;
		
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		if ((filmes.stream().filter(x -> x.getEstoque() == 0).count()) > 0) {
			throw new FilmeSemEstoqueException("Filme sem estoque");
		}
		
		valor = filmes.stream().mapToDouble(x -> x.getPrecoLocacao()).sum();
		Date dataLocacao = new Date();
		Locacao locacao = new Locacao(usuario, valor, filmes, dataLocacao);
		
		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}
}