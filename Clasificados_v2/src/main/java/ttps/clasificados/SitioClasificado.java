package ttps.clasificados;

public class SitioClasificado {
	private String nombre, mail, telefono;

	public SitioClasificado(String nombre, String mail, String telefono) {
		super();
		this.nombre = nombre;
		this.mail = mail;
		this.telefono = telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "SitioClasificado [nombre=" + nombre + ", mail=" + mail + ", telefono=" + telefono + "]";
	}
	
	
	
	
}
