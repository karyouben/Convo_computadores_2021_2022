package fp.cortes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InformeCortes {
	
	private String nombre;
	private LocalDate fecha;
	private List<CorteElectrico> cortes;
	private Integer numCortes;
	
	public InformeCortes(String nombre,LocalDate fecha,Integer numCortes) {
		this.nombre=nombre;
		this.fecha=fecha;
		this.numCortes=numCortes;
		cortes=new ArrayList<>();
	}
	
	public InformeCortes(String nombre,LocalDate fecha,Stream<CorteElectrico> cortes) {
		this.nombre=nombre;
		this.fecha=fecha;
		this.cortes=new ArrayList<>();
		
	}

	@Override
	public String toString() {
		return "InformeCortes [nombre=" + nombre + ", fecha=" + fecha + ", numCortes=" + numCortes + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InformeCortes other = (InformeCortes) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(nombre, other.nombre);
	}
	
	public  InformeCortes() {
		cortes=new ArrayList<CorteElectrico>();
	}
	
	public InformeCortes(List<CorteElectrico> corte) {
		cortes=new ArrayList<CorteElectrico>(corte);
	}
	
	public InformeCortes(Collection<CorteElectrico> cortes) {
		this.cortes=new ArrayList<CorteElectrico>(cortes);
	}
	
	public InformeCortes(Stream<CorteElectrico> cortes) {
		this.cortes=cortes.collect(Collectors.toList());
	}
	
	
	
	public void incorporaCorte(CorteElectrico c) {
		cortes.add(c);
	}
	
	public void incorpotaCortes(List<CorteElectrico> cortes) {
		this.cortes.addAll(cortes);
	}
	
	public void eliminarCorte(CorteElectrico c) {
		cortes.remove(c);
	}
	
	//ejercicio 1
	
	public Double mediaAfectadosEnRegiones(Severidad s,Set<String> regiones) {
		return cortes.stream()
				.filter(c->c.severidad().equals(s) && regiones.contains(c.region()))
				.mapToDouble(CorteElectrico::consumAfectados)
				.average()
				.getAsDouble();
		
	}
	
	//ejercicio 2
	
	public List<String> compa?iasCortesMasRecientes(String etiqueta,Integer n){
		return cortes.stream()
				.filter(c->c.etiquetas().contains(etiqueta))
				.sorted(Comparator.comparing(CorteElectrico::fechaCorte).reversed())
				.map(CorteElectrico::compa?ia)
				.limit(n)
				.distinct()
				.collect(Collectors.toList());
				
	}
	
	//ejercicio 3
	
	public SortedMap<String,SortedSet<String>> compa?iasConCortesCriticosPorRegion(){
		SortedMap<String,SortedSet<String>> res=new TreeMap<>();
		for(CorteElectrico c:cortes) {
			String clave=c.region();
			String valor=c.compa?ia();
			if(!res.containsKey(clave)) {
				SortedSet<String> lista= new TreeSet<>();
				lista.add(valor);
				res.put(clave, lista);
			}else {
				res.get(clave).add(valor);
			}
		}return res;
	}
	
	public SortedMap<String,SortedSet<String>> compa?iasConCortesCriticosPorRegion2(){
		return cortes.stream()
				.collect(Collectors.groupingBy(CorteElectrico::region,TreeMap::new,
						Collectors.collectingAndThen(Collectors.toCollection(TreeSet::new), lista->obtieneConjunto(lista))));
		//se puede tambien con un Collectors.toSet() al estar ya ordenado en el auxiliar
	}
	
	

	private SortedSet<String> obtieneConjunto(Set<CorteElectrico> lista){
		return lista.stream()
				.map(CorteElectrico::compa?ia)
				.sorted()
				.collect(Collectors.toCollection(TreeSet::new));
		//se puede tambien con un ()-> new TreeSet<>();
				
	}
	
	//ejercicio 4
	
	public Map<Severidad,Double> porcentajeCortesPorSeveridadEnRegion(String region){
		Map<Severidad,Double> res=null;
		Map<Severidad,Long> m=numeroCortesPorSeveridad(region);
		Long p=cortesTotales(region);
		if(p>0)
			res= m.entrySet().stream()
			.collect(Collectors.toMap(c->c.getKey(), c->c.getValue()*100.0/p));
		return res;
		
	}
	
	public Long cortesTotales(String region) {
		return cortes.stream()
				.filter(c->c.region().equals(region))
				.count();
	}
	
	public Map<Severidad,Long> numeroCortesPorSeveridad(String region){
		return cortes.stream()
				.collect(Collectors.groupingBy(CorteElectrico::severidad,Collectors.counting()));
	}
	
	
	//ejercicio 5
	
	public String compa?iaConMasAfectadosEnFecha(LocalDate f) {
		Map<String,Integer> m= consumidoresAfectadosPorCompa?ia(f);
		Comparator<Map.Entry<String, Integer>> c= Comparator.comparing(Map.Entry::getValue);
		return m.entrySet().stream()
				.max(c)
				.get()
				.getKey();
		
	}
	
	private Map<String,Integer> consumidoresAfectadosPorCompa?ia(LocalDate f){
		return cortes.stream()
				.filter(c->c.fechaRestablecimiento().getDayOfYear()>=f.getDayOfYear())
				.collect(Collectors.toMap(CorteElectrico::compa?ia, c->c.consumAfectados(),(x,y)->Integer.max(x, y)));
		
	}
	
	
	
	

}
