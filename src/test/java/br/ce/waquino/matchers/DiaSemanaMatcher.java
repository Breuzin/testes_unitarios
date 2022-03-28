package br.ce.waquino.matchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.ce.wcaquino.utils.DataUtils;

public class DiaSemanaMatcher extends TypeSafeMatcher<Date> {

	protected Integer diaDaSemana;
	
	public DiaSemanaMatcher(Integer diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
	
	@Override
	public void describeTo(Description description) {
		Calendar data = Calendar.getInstance();
		data.set(Calendar.DAY_OF_WEEK, diaDaSemana);
		String dataExtenso = data.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
		description.appendText(dataExtenso);
	}

	@Override
	protected boolean matchesSafely(Date item) {
		return DataUtils.verificarDiaSemana(item, diaDaSemana);
	}
}
