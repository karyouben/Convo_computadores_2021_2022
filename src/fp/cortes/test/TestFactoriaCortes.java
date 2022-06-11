package fp.cortes.test;

import java.util.List;

import fp.cortes.CorteElectrico;
import fp.cortes.FactoriaCortes;

public class TestFactoriaCortes {

	public static void main(String[] args) {
		testLeeCortes("data/cortes.csv");

	}

	private static void testLeeCortes(String fichero) {
		System.out.println("\nTestLeeCortes =========");
		List<CorteElectrico> cortes=FactoriaCortes.leeCortes(fichero);
		System.out.println(" CorteElectrico: ");
		for(CorteElectrico c:cortes) {
			System.out.println(c);
		}
		
	}

}
