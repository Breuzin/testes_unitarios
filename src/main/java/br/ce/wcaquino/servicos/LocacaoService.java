package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.ce.acquino.exceptions.FilmeSemEstoqueException;
import br.ce.acquino.exceptions.LocadoraException;
import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;
import buildermaster.BuilderMaster;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, List<Filme> filmes) throws FilmeSemEstoqueException, LocadoraException  {

		
		
		if (usuario == null) {
			throw new LocadoraException("Usuario vazio");
		}
		if (filmes == null || filmes.isEmpty()) {
			throw new LocadoraException("Filme vazio");
		}
		if ((filmes.stream().filter(x -> x.getEstoque() == 0).count()) > 0) {
			throw new FilmeSemEstoqueException("Filme sem estoque");
		}
		
		Double valorTotal = 0d;
		for (int i = 0; i < filmes.size(); i++) {
			double valorFilme = filmes.get(i).getPrecoLocacao();
			switch (i) {
				case 2: valorFilme *= 0.75; 
				break;
				case 3: valorFilme *= 0.50; 
				break;
				case 4: valorFilme *= 0.25; 
				break;
				case 5: valorFilme = 0; 
				break;
			}
			valorTotal += valorFilme;
		}
		Date dataLocacao = new Date();
		Locacao locacao = new Locacao(usuario, valorTotal, filmes, dataLocacao);
		
		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		if (DataUtils.verificarDiaSemana(dataEntrega, Calendar.SUNDAY)) {
			dataEntrega = adicionarDias(dataEntrega, 1);
		}
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar método para salvar

		return locacao;
	}
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}
}