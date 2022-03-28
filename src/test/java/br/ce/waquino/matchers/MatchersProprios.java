package br.ce.waquino.matchers;

import java.util.Calendar;

public class MatchersProprios {

	public static DiaSemanaMatcher caiEm(Integer diaDaSemana) {
		return new DiaSemanaMatcher(diaDaSemana);
	}
	
	public static DiaSemanaMatcher caiEmSegunda() {
		return new DiaSemanaMatcher(Calendar.MONDAY);
	}
}
