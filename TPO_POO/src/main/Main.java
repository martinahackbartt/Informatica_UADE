package main;

import clases.*;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GestorDeEventos gestorDeEventos = new GestorDeEventos();

    public static void main(String[] args) {
        boolean ejecutando = true;

        while (ejecutando) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Crear un nuevo evento");
            System.out.println("2. Mostrar eventos futuros");
            System.out.println("3. Mostrar eventos pasados");
            System.out.println("4. Mostrar eventos de un día específico");
            System.out.println("5. Ver detalles de un evento");
            System.out.println("6. Inscribir asistentes a un evento");
            System.out.println("7. Editar un evento");
            System.out.println("8. Mostrar calendario completo");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion == 1) {
                crearEvento();
            } else if (opcion == 2) {
                mostrarEventosFuturos();
            } else if (opcion == 3) {
                mostrarEventosPasados();
            } else if (opcion == 4) {
                mostrarEventosDeUnDia();
            } else if (opcion == 5) {
                verDetallesDeUnEvento();
            } else if (opcion == 6) {
                registrarAsistente();
            } else if (opcion == 7) {
                editarEvento();
            } else if (opcion == 8) {
                mostrarCalendario();
            } else if (opcion == 9) {
                ejecutando = false;
                System.out.println("Saliendo...");
            } else {
                System.out.println("Opción inválida.");
            }
        }
    }

    private static void crearEvento() {
        System.out.print("\nDescripción del evento: ");
        String descripcion = scanner.nextLine();

        System.out.print("Fecha (yyyy-MM-dd): ");
        String fechaStr = scanner.nextLine();
        Date fecha = new GregorianCalendar(
                Integer.parseInt(fechaStr.split("-")[0]),
                Integer.parseInt(fechaStr.split("-")[1]) - 1,
                Integer.parseInt(fechaStr.split("-")[2])
        ).getTime();

        System.out.print("Capacidad de la ubicación: ");
        int capacidad = scanner.nextInt();
        scanner.nextLine();

        Ubicacion ubicacion = new Ubicacion(capacidad);
        ubicacion.describir("Ubicación predeterminada");

        Evento evento = new Evento(fecha, ubicacion, descripcion);
        gestorDeEventos.agregarEvento(evento);

        System.out.println("Evento creado: " + evento);
    }

    private static void mostrarEventosFuturos() {
        System.out.println("\n--- Eventos Futuros ---");
        List<Evento> eventosFuturos = gestorDeEventos.obtenerEventosFuturos();
        mostrarListaEventos(eventosFuturos);
    }

    private static void mostrarEventosPasados() {
        System.out.println("\n--- Eventos Pasados ---");
        List<Evento> eventosPasados = gestorDeEventos.obtenerEventosPasados();
        mostrarListaEventos(eventosPasados);
    }

    private static void mostrarEventosDeUnDia() {
        System.out.print("\nDía del mes: ");
        int dia = scanner.nextInt();
        scanner.nextLine();

        List<Evento> eventosDelDia = gestorDeEventos.obtenerEventosDelDia(dia);
        mostrarListaEventos(eventosDelDia);
    }

    private static void verDetallesDeUnEvento() {
        mostrarEventosFuturos();
        System.out.print("Seleccione el índice del evento para ver detalles: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        try {
            Evento evento = gestorDeEventos.obtenerEventosFuturos().get(indice);
            mostrarDetalles(evento);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido.");
        }
    }

    private static void registrarAsistente() {
        mostrarEventosFuturos();
        System.out.print("Seleccione el índice del evento para inscribir asistentes: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        try {
            Evento evento = gestorDeEventos.obtenerEventosFuturos().get(indice);
            System.out.print("Nombre del asistente: ");
            String nombre = scanner.nextLine();
            System.out.print("Correo del asistente: ");
            String correo = scanner.nextLine();

            InvitadoPorEvento nuevoInvitado = new InvitadoPorEvento(nombre, correo, evento);
            evento.agregarInvitado(nuevoInvitado);
            System.out.println("Asistente registrado exitosamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido. Intente nuevamente.");
        }
    }

    private static void editarEvento() {
        mostrarEventosFuturos();
        System.out.print("Seleccione el índice del evento para editar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        try {
            Evento evento = gestorDeEventos.obtenerEventosFuturos().get(indice);
            System.out.println("Editando el evento: " + evento);

            System.out.print("Nueva descripción (deje en blanco para no cambiar): ");
            String nuevaDescripcion = scanner.nextLine();
            if (!nuevaDescripcion.isEmpty()) {
                evento.setDescripcion(nuevaDescripcion);
            }

            System.out.print("Nueva fecha (yyyy-MM-dd, deje en blanco para no cambiar): ");
            String nuevaFechaStr = scanner.nextLine();
            if (!nuevaFechaStr.isEmpty()) {
                Date nuevaFecha = new GregorianCalendar(
                        Integer.parseInt(nuevaFechaStr.split("-")[0]),
                        Integer.parseInt(nuevaFechaStr.split("-")[1]) - 1,
                        Integer.parseInt(nuevaFechaStr.split("-")[2])
                ).getTime();
                evento.setFecha(nuevaFecha);
            }

            System.out.print("Nueva capacidad de la ubicación (deje en blanco para no cambiar): ");
            String nuevaCapacidadStr = scanner.nextLine();
            if (!nuevaCapacidadStr.isEmpty()) {
                int nuevaCapacidad = Integer.parseInt(nuevaCapacidadStr);
                Ubicacion nuevaUbicacion = new Ubicacion(nuevaCapacidad);
                evento.setUbicacion(nuevaUbicacion);
            }

            System.out.println("Evento editado exitosamente.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Índice inválido. Intente nuevamente.");
        }
    }

    private static void mostrarCalendario() {
        System.out.println("\n--- Calendario de Eventos ---");
        for (int dia = 1; dia <= 31; dia++) { // Recorremos los días del mes
            List<Evento> eventosDelDia = gestorDeEventos.obtenerEventosDelDia(dia);
            if (!eventosDelDia.isEmpty()) {
                System.out.println("Día " + dia + ":");
                for (Evento evento : eventosDelDia) {
                    System.out.println("- " + evento.getDescripcion() + " en " + evento.getUbicacion().cualEsTuDescripcion());
                }
            }
        }
    }

    private static void mostrarListaEventos(List<Evento> eventos) {
        if (eventos.isEmpty()) {
            System.out.println("No hay eventos.");
        } else {
            for (int i = 0; i < eventos.size(); i++) {
                System.out.println(i + ". " + eventos.get(i).getDescripcion());
            }
        }
    }

    private static void mostrarDetalles(Evento evento) {
        System.out.println("\nDetalles del evento:");
        System.out.println(evento);
        System.out.println("Invitados registrados:");
        for (InvitadoPorEvento invitado : evento.obtenerListaInvitados()) {
            System.out.println("- " + invitado.comoTeLlamas() + " (" + invitado.cualEsTuMail() + ")");
        }
    }
}

