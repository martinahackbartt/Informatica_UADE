package clases;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Evento {
  private Date fecha;
  private Ubicacion ubicacion;
  private String descripcion;
  private List<InvitadoPorEvento> invitados;
  private List<Recurso> recursos;
 
  public Evento(Date fecha, Ubicacion ubicacion, String descripcion) {
      this.fecha = fecha;
      this.ubicacion = ubicacion;
      this.descripcion = descripcion;
      this.invitados = new ArrayList<>();
      this.recursos = new ArrayList<>();
     
  }
  // Métodos para modificar detalles del evento
  public void setFecha(Date fecha) {
      this.fecha = fecha;
  }
  public void setUbicacion(Ubicacion ubicacion) {
      this.ubicacion = ubicacion;
  }
  public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
  }
  // Métodos para obtener los detalles del evento
  public Date getFecha() {
      return fecha;
  }
  public Ubicacion getUbicacion() {
      return ubicacion;
  }
  public String getDescripcion() {
      return descripcion;
  }
  // Métodos para gestionar invitados
  public void agregarInvitado(InvitadoPorEvento invitado) {
      invitados.add(invitado);
  }
  public void eliminarInvitado(InvitadoPorEvento invitado) {
      invitados.remove(invitado);
  }
  public List<InvitadoPorEvento> obtenerListaInvitados() {
      return invitados;
  }
	public boolean esFuturo() {
		return !(fecha.before(new Date()));
	}
	public boolean esPasado() {
		return fecha.before(new Date());
	}
	
	//Métodos para gestionar los recursos
   public void agregarRecurso(Recurso recurso) {
       if (recurso.estaDisponible()) {
           recurso.reservar();
           recursos.add(recurso);
           } else {
           System.out.println("Error, el recurso no esta disponible.");
       }
   }
   public void eliminarRecurso(Recurso recurso) {
       if (recursos.contains(recurso)) {
           recurso.liberar();
           recursos.remove(recurso);
           System.out.println("Recurso eliminado: " + recurso.cualEsTuDescripcion());
       } else {
           System.out.println("Error, recurso no encontrado.");
       }
   }
   public List<Recurso> obtenerRecursos() {
       return recursos;
   }
   @Override
   public String toString() {
       StringBuilder detalles = new StringBuilder();
       detalles.append("Fecha: ").append(fecha).append("\n");
       detalles.append("Ubicación: ").append(ubicacion.cualEsTuDescripcion()).append("\n");
       detalles.append("Descripción: ").append(descripcion).append("\n");
       detalles.append("Recursos:\n");
       for (Recurso recurso : recursos) {
           detalles.append("- ").append(recurso.cualEsTuDescripcion()).append(" (").append(recurso.queTipoDeRecursoEs()).append(")\n");
       }
       return detalles.toString();
   }
}