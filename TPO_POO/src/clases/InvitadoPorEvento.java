package clases;
public class InvitadoPorEvento extends Invitado {
    private Evento evento;
    private boolean asistencia;
    private String feedback;

    public InvitadoPorEvento(String nombre, String mail, Evento evento) {
        super(nombre, mail);
        this.evento = evento;
    }

    public void registrarAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }

    public Evento obtenerEvento() {
        return evento;
    }

    public boolean asistioAlEvento() {
        return asistencia;
    }
    
    public void darFeedback(String texto) {
    	this.feedback= texto;
    }
}