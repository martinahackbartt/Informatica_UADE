package clases;
public class Ubicacion extends Recurso {
	
	private int capacidad;
	
   public Ubicacion(int capacidad) {
       this.setTipoDeRecurso("Ubicacion");
       this.capacidad = capacidad;
   }
	
   public int queCapacidadTiene() {
   	return capacidad;
   }
}
