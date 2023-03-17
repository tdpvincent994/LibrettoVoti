package it.polito.tdp.librettovoti.model;

public class TestLibretto {

	//qui inserisco il metodo main
	public static void main(String[] args) {
		
		Libretto lib = new Libretto() ; //istanzio un oggetto Libretto e ci metto qualcosa dentro
		lib.add(new Voto("Analisi 1", 30));
		lib.add(new Voto("Informatica", 25));
		lib.add(new Voto("Fisica 1", 18));
		lib.add(new Voto("Algebra lineare", 25));
		System.out.println("Libretto originale");
		System.out.println(lib) ; //richiama la toString modificata di Libretto, altrimenti avrei l'id dell'oggetto
		
		
		//per i test
		System.out.println("\n\nVoti pari e superiori a 25");
		Libretto lib25 = lib.filtraPunti(25); //contiene gli elementi filtrati
		System.out.println(lib25) ;

		
		System.out.println("\n\nVoti in ordine alfabetico in base al corso");
		Libretto libOrdinata = lib.librettoOrdinatoAlfabeticamente(); //contiene gli elementi filtrati
		System.out.println(libOrdinata) ;

		
		System.out.println("\n\nVoti in ordine decrescente in base al punteggio");
		Libretto libOrdinataDecr = lib.librettoOrdinatoPuntiDecrescente(); //contiene gli elementi filtrati
		System.out.println(libOrdinataDecr) ;

		
		System.out.println("\n\nVoti migliorati");
		Libretto libMigliorato = lib.votiMigliorati(); //contiene gli elementi filtrati
		System.out.println(libMigliorato) ;
	}

}
