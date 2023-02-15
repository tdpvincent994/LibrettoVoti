package it.polito.tdp.librettovoti.model;

public class TestLibretto {

	//qui inserisco il metodo main
	public static void main(String[] args) {
		
		Libretto lib = new Libretto() ; //istanzio un oggetto Libretto e ci metto qualcosa dentro
		lib.add(new Voto("Analisi 1", 30));
		lib.add(new Voto("Informatica", 25));
		lib.add(new Voto("Fisica 1", 18));
		lib.add(new Voto("Algebra lineare", 25));
		
		System.out.println(lib) ; //richiama la toString modificata di Libretto, altrimenti avrei l'id dell'oggetto
		
		
		//per i test
		System.out.println("Voti pari a 25");
		Libretto lib25 = lib.filtraPunti(25); //contiene gli elementi filtrati
		System.out.println(lib25) ;

	}

}
