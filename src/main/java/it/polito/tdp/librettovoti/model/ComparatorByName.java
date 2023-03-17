package it.polito.tdp.librettovoti.model;

import java.util.Comparator;

public class ComparatorByName implements Comparator<Voto> {

	@Override
	public int compare(Voto o1, Voto o2) {
		// ordinamento alfabetico sul nome del corso, una stringa quindi uso l'ordinamento delle string
		return o1.getNome().compareTo(o2.getNome());
	}

}
