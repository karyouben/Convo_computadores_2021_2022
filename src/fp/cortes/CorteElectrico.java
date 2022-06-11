package fp.cortes;


import java.time.LocalDateTime;
import java.util.List;

import fp.utiles.Checkers;

public record CorteElectrico(String descripcion,LocalDateTime fechaCorte,LocalDateTime fechaRestablecimiento, String compañia,
		String region, Double perdidaDemanda, Integer consumAfectados,List<String> etiquetas) implements Comparable<CorteElectrico> {
	
	public CorteElectrico{
		Checkers.check("La fecha de restablecimiento debe ser igual o posterior a la fecha de inicio del corte", fechaCorte.isBefore(fechaRestablecimiento) || fechaCorte==fechaRestablecimiento);
		Checkers.check("El número de consumidores afectados debe ser mayor o igual que 0 o null", consumAfectados>=0|| consumAfectados==null);
		Checkers.check("La lista de etiquetas debe contener al menos una etiqueta", etiquetas.size()>=1);
		
	}
	
	
	public Severidad severidad() {
		Severidad res=null;
		if(consumAfectados()<10000 || consumAfectados()==null) {
			res=Severidad.BAJO;
		}else if(consumAfectados()>=10000 && consumAfectados()<=100000){
			res=Severidad.MEDIO;
		}else {
			res=Severidad.ALTO;
		}return res;
	}
	
	public  Boolean esCritico() {
		Boolean res=false;
		if(perdidaDemanda()>200 ||  (fechaCorte().getHour()-fechaRestablecimiento().getHour())>10 ) {
			res=true;
		}return res;
	}
	
	
	
	public int compareTo (CorteElectrico o) {
		int res=this.fechaCorte().compareTo(o.fechaCorte());
	if(res==0) {
		res=this.region().compareTo(o.region());
	 }return res;
  }
	

}
