package fp.cortes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import fp.utiles.Checkers;
import fp.utiles.Ficheros;

public class FactoriaCortes {
	
	public static List<CorteElectrico> leeCortes(String fichero){
		Checkers.checkNoNull(fichero);
		List<String> lineas=Ficheros.leeFichero("Error al leer fichero", fichero);
		lineas.remove(0);
		
		List<CorteElectrico> res=new ArrayList<CorteElectrico>();
		for(String linea:lineas) {
			CorteElectrico c=parseaCorte(linea);
			res.add(c);
		}return res;
	}

	private static CorteElectrico parseaCorte(String linea) {
		Checkers.checkNoNull(linea);
		String[] trozos=linea.split(";");
		Checkers.check("Formato no valido", trozos.length==8);
		String descripcion=trozos[0].trim();
		LocalDateTime fechaCorte=LocalDateTime.parse(trozos[1].trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy H:m"));
		LocalDateTime fechaRestablecimiento=LocalDateTime.parse(trozos[2].trim(),DateTimeFormatter.ofPattern("dd/MM/yyyy H:m"));
		String compañia=trozos[3].trim();
		String region=trozos[4].trim();
		Double perdidaDemanda=Double.parseDouble(trozos[5].trim());
		Integer consumAfectados=Integer.parseInt(trozos[6].trim());
		List<String> etiquetas=obtieneLista(trozos[7].trim());
		return new CorteElectrico(descripcion, fechaCorte, fechaRestablecimiento, compañia, region, perdidaDemanda, consumAfectados, etiquetas);
	}

	private static List<String> obtieneLista(String cadena) {
		String[] arreglo=cadena.split(",");
		List<String> salida=new ArrayList<String>();
		for(String c:arreglo) {
			salida.add(c);
		}return salida;
	}
	
	
	
	
	
	private static Boolean parseaBooleano(String cadena) {
		Boolean res=null;
		if(cadena.equals("VERDADERO")) {
			res=true;
		}else if(cadena.equals("FALSO")) {
			res=false;
		}return res;
	}
	
	

}
