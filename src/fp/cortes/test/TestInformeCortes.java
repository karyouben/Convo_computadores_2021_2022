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
		testMediaAfectadosEnRegiones(c,Severidad.ALTO, Set.of("Pokemon_Españita","portugal"));
		testCompañiasCortesMasRecientes(c,"UNICORNIO",40);
		testCompañiasConCortesCriticosPorRegion(c);
		testCompañiasConCortesCriticosPorRegion2(c);
		testCompañiaConMasAfectadosEnFecha(c,LocalDate.of(1994, 9, 16));
		

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
	
	private static void testCompañiasCortesMasRecientes(InformeCortes c, String etiqueta, Integer n) {
		try {
			String msg=String.format("Las " +n+ " compañias con cortes mas recientes de la etiqueta %d son : ",
					etiqueta,n,c.compañiasCortesMasRecientes(etiqueta, n));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompañiasConCortesCriticosPorRegion(InformeCortes c) {
		try {
			String msg=String.format("El conjunto de cortes criticos por compañia son los siguientes: ",
					c.compañiasConCortesCriticosPorRegion());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompañiasConCortesCriticosPorRegion2(InformeCortes c) {
		try {
			String msg=String.format("El conjunto de cortes criticos por compañia son los siguientes: ",
					c.compañiasConCortesCriticosPorRegion2());
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	
	private static void testCompañiaConMasAfectadosEnFecha(InformeCortes c,LocalDate f) {
		try {
			String msg=String.format("La compañia con mas cortes afectados en la fecha %d es: ",
					f,c.compañiaConMasAfectadosEnFecha(f));
			System.out.println(msg);
		}catch(Exception e) {
			System.err.println("Excepcion inesperada capturada" + e.getMessage());
		}
		
	}
	

	

}
