package it.polito.tdp.librettovoti.model;

import java.util.ArrayList;
import java.util.List;

//per gestire una lista di voti
public class Libretto {

	private List<Voto> voti ;
	
	//senza parametri perchè posso non avere nulla 
	public Libretto() {
		this.voti = new ArrayList<Voto>() ; //nel costruttore definisco la struttura dati necessaria
		//l'operazione new si fa in luoghi controllati, ne parleremo nei pattern
	}
	
	
	//metodi per aggiungere un elemento, restituisce se è stato aggiunto o meno per avere coscienza
	//parametro oggetto Voto, perchè svincola da modifiche future su Voto, non devo modificare nulla
	public boolean add(Voto v) {
		if(!isDuplicato(v) && !isConflitto(v)) { //verifico che non esista però
			this.voti.add(v); //uso il metodo della List, delega. Mi restituisce un boolean, meglio essere in linea con il metodo allora
			return true ;
		} else {
			return false ;
		}
	}
	
	//metodo che filta, mi restituisce un oggetto di tipo Libretto (quindi elenco di esami ) in cui il voto è uguale ad una soglia
	// lista vuota che riempio. scelta migliore, e non string o void come parametro restituito
	public Libretto filtraPunti(int punti) {
		Libretto result = new Libretto() ;
		for(Voto v: this.voti) {
			if(v.getPunti()==punti) {
				result.add(v); //in questo modo vengono effettuati anche i controlli del metodo add dell'oggetto
			}
		}
		return result ;
	}
	
	/**
	 * Restituisce il punteggio ottenuto all'esame di cui 
	 * specifico il nome
	 * @param nome Nome dell'esame 
	 * @return punteggio numeroco, oppure {@code null} se l'esame non esiste nel Libretto
	 */
	//Integer wrappa (avvolge) il tipo primiivo 
	public Integer puntiEsame(String nome) {
		for(Voto v: this.voti) {
			if( v.getNome().equals(nome) ) { //le string si confrontano sempre con il metodo equals
				return v.getPunti() ;
			}
		}
//		return -1; //non ha molto senso però potrei avere errori in seguito, per questo cambio da int a Integer e scelgo null
		return null ; //se non trovo il corso, è più sicuro (se avessi -1 e usassi un name sbagliato, in una media darebbe un risultato sbagliato)
//		throw new IllegalArgumentException("Corso non trovato") ;  //altra possibilità
	}
	
	
	//esiste duplicato nel libretto o meno
	public boolean isDuplicato(Voto v) {
		for(Voto v1: this.voti) {
			if(v1.equals(v)) //uso confronto sull'oggetto stesso nome e stesso punteggio, delego alla classe, in cui devo ridefinire equals
				return true ;
		}
		return false ;
	}
	
	
	//esiste un conflitto, stesso voto ma punteggio diverso
	//uso i metodi già costruiti, modo diverso da isDuplicato che itera sul Libretto
	public boolean isConflitto(Voto v) {
		Integer punti = this.puntiEsame(v.getNome()) ; //verifico il punteggio del voto passato, nel libretto attuale, tramite il metodo puntiEsame(string)
		if (punti != null && punti != v.getPunti()) //se c'è il valore è diverso da null, a quel punto verifico
			return true; // hanno lo stesso punteggio
		else
			return false;
	}
	
	public List<Voto> getVoti() {
		return this.voti ;
	}
	
	

	public Libretto votiMigliorati() {
		Libretto nuovo = new Libretto() ;
		for(Voto v: this.voti) {
			int punti = v.getPunti() ;
			if(punti>=24)
				punti +=2 ;
			else 
				punti++ ;
			if (punti>30)
				punti=30 ;

			// NOOOO va a modificare l'oggetto nel libretto originale
			//			v.setPunti(punti);
			//			nuovo.add(v) ;
			
			nuovo.add(new Voto(v.getNome(), punti)) ;
		}
		return nuovo ;
	}
	
	
	// FUNZIONERÀ COSÌ??? Proviamo... e capiamo perché
	public void cancellaVotiMinori(int punti) {
		for(Voto v: this.voti) {
			if(v.getPunti()<punti)
				this.voti.remove(v) ;
		}
	}
	
	//richiamo quella della lista che mi inserisce le [,,], devo modificare anche la toString dell'oggetto Voto 
	public String toString() {
		return this.voti.toString() ;
	}
}
