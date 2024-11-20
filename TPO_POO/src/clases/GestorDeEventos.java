package clases;
import java.util.*;

public class GestorDeEventos {
   private HashMap<Integer, List<Evento>> calendario;
   private List<Evento> eventos;
   public GestorDeEventos() {
       this.calendario = new HashMap<>();
       this.eventos = new ArrayList<>();
       for (int i = 1; i <= 31; i++) {
           calendario.put(i, new ArrayList<>());
       }
   }
   public void agregarEvento(Evento evento) {
       eventos.add(evento);
       int dia = evento.getFecha().getDate();
       calendario.get(dia).add(evento);
   }
   public List<Evento> obtenerEventosFuturos() {
       List<Evento> eventosFuturos = new ArrayList<>();
       for (Evento evento : eventos) {
           if (evento.esFuturo()) {
               eventosFuturos.add(evento);
           }
       }
       return eventosFuturos;
   }
   public List<Evento> obtenerEventosPasados() {
       List<Evento> eventosPasados = new ArrayList<>();
       for (Evento evento : eventos) {
           if (evento.esPasado()) {
               eventosPasados.add(evento);
           }
       }
       return eventosPasados;
   }
   public void mostrarEventos(List<Evento> eventos) {
       for (Evento evento : eventos) {
           System.out.println(evento);
       }
   }
   public List<Evento> obtenerEventosDelDia(int dia) {
       return calendario.getOrDefault(dia, new ArrayList<>());
   }
   public void mostrarCalendario() {
       System.out.println("Calendario de Eventos:");
       for (int dia : calendario.keySet()) {
           List<Evento> eventosDelDia = calendario.get(dia);
           if (!eventosDelDia.isEmpty()) {
               System.out.println("Día " + dia + ":");
               for (Evento evento : eventosDelDia) {
                   System.out.println("- " + evento.getDescripcion() + " en " + evento.getUbicacion().cualEsTuDescripcion());
               }
           }
       }
   }
}
