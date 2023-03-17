package it.polito.tdp.librettovoti.model;

public class Voto {
	private String nome ;
	private int punti ; //la chiamo punti per non fare confusione con il nome della Classe
	 
	//costruttore che inizializza 
	public Voto(String nome, int punti) {
		super();
		this.nome = nome;
		this.punti = punti;
	}
	//getter degli attributi, leggibili e scrivibili
	public String getNome() {
		return nome;
	}
	
	//setter
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPunti() {
		return punti;
	}
	public void setPunti(int punti) {
		this.punti = punti;
	}
	
	//chiedo a Eclipse, che lo crea sulla base delle proprietà, modifico sulla base dei gusti
	@Override
	public String toString() {
		return nome+" : "+ punti;
	}
	
	//generati da Eclipse sul criterio da noi scelto, devono andare insieme anche se hashCode ora non ci serve
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + punti;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		//controllo completo
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (punti != other.punti)
			return false;
		return true;
	}
	
	
	//Per quanto riguarda la comparazione tra oggetti Voto, esiste una comparazione naturale obiettiva? no
	//Non ha senso quindi definire il metodo compareTo (Voto other) e implementare Comparable qui. Comparable
	//Ho due soluzioni che implemento entrambe:
	//1. Dichiaro una classe esterna che implementa l'interfaccia COMPARATOR e il metodo compare
	//2. Utilizzo una classe anonima che implementa l'interfaccia COMPARATOR e il metodo compare	
	
	
		
}
