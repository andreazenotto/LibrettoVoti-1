package it.polito.tdp.librettovoti.model;

public class TestLibretto {

	public static void main(String[] args) {

		Libretto lib = new Libretto();
		lib.add(new Voto("Analisi 1", 25));
		lib.add(new Voto("Fisica 1", 18));
		lib.add(new Voto("Informatica", 30));
		
		System.out.println(lib);
		System.out.println("Voti pari a 25:");		
		System.out.println(lib.filtraPunti(25));
		
	}

}
