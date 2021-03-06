package fp.cortes.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import fp.cortes.CorteElectrico;
import fp.cortes.FactoriaCortes;
import fp.cortes.InformeCortes;
import fp.cortes.Severidad;

public class TestInformeCortes {

	public static void main(String[] args) {
		List<CorteElectrico> cortes=FactoriaCortes.leeCortes("data/cortes.csv");
		InformeCortes c=new InformeCortes(cortes);
		System.out.println("\nTestMediaAfectadosEnRegiones");
		System.out.println("===================================================");
		testMediaAfectadosEnRegiones(c,Severidad.ALTO, Set.of("Pokemon_Espa˝ita","portugal"));
		System.out.println("\nTestCompa˝iasCortesMasRecientes");
		System.out.println("===================================================");
		testCompa˝iasCortesMasRecientes(c,"UNICORNIO",40);
		System.out.println("\nTestCompa˝iasConCortesCriticosPorRegion");
		System.out.println("===================================================");
		testCompa˝iasConCortesCriticosPorRegion(c);
		System.out.println("\nTestCompa˝iasConCortesCriticosPorRegion2");
		System.out.println("===================================================");
		testCompa˝iasConCortesCriticosPorRegion2(c);
		System.out.println("\nTestCompa˝iaConMasAfectadosEnFecha");
		System.out.println("===================================================");
		testCompa˝iaConMasAfectadosEnFecha(c,LocalDate.of(1994, 9, 16));
		

	}

	private static void testMediaAfectadosEnRegiones(InformeCortes c, Severidad s, Set<String> regiones) {
		try {
			String msg=String.format("La media de afectados en regiones de corted de nivel %d es: ",
					s,regiones,c.mediaAfectadosEnRegiones(s, regiones));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompa˝iasCortesMasRecientes(InformeCortes c, String etiqueta, Integer n) {
		try {
			String msg=String.format("Las " +n+ " compa˝ias con cortes mas recientes de la etiqueta %d son : ",
					etiqueta,n,c.compa˝iasCortesMasRecientes(etiqueta, n));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompa˝iasConCortesCriticosPorRegion(InformeCortes c) {
		try {
			String msg=String.format("El conjunto de cortes criticos por compa˝ia son los siguientes: ",
					c.compa˝iasConCortesCriticosPorRegion());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompa˝iasConCortesCriticosPorRegion2(InformeCortes c) {
		try {
			String msg=String.format("El conjunto de cortes criticos por compa˝ia son los siguientes: ",
					c.compa˝iasConCortesCriticosPorRegion2());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompa˝iaConMasAfectadosEnFecha(InformeCortes c,LocalDate f) {
		try {
			String msg=String.format("La compa˝ia con mas cortes afectados en la fecha %d es: ",
					f,c.compa˝iaConMasAfectadosEnFecha(f));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	

	

}
