package clases;
public class Recurso {
	
	private boolean disponibilidad;
	
	private float costo;
	
	private String tipoDeRecurso;
	private String descripcion;
	
	public void reservar() {
		this.disponibilidad = false;
	}
	
	public void liberar() {
		this.disponibilidad = true;
	}
	
	public void costoActualizado(float costo) {
		this.costo = costo;
	}
	
	public void setTipoDeRecurso(String tipoDeRecurso) {
		this.tipoDeRecurso = tipoDeRecurso;
	}
	
	public void describir(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean estaDisponible() {
		return disponibilidad;
	}
	
	public float cualEsTuCosto() {
		return costo;
	}
	
	public String queTipoDeRecursoEs() {
		return tipoDeRecurso;
	}
	public String cualEsTuDescripcion() {
		return descripcion;
	}
}

