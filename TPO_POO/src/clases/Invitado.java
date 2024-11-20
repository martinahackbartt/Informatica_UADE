package clases;
public class Invitado {
	
	private String nombre;
	private String mail;
	
	public Invitado (String nombre, String mail) {
		this.nombre = nombre;
		this.mail = mail;
	}
	
	public String comoTeLlamas() {
		return nombre;
	}
	
	public String cualEsTuMail() {
		return mail;
	}

}

