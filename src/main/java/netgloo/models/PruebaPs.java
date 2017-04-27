package netgloo.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

@Entity
@Table(name="Persistence")
public class PruebaPs {
	
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="paterno")
	private String paterno;
	
	@Column(name="materno")
	private String materno;
	
	@Id
	@Column(name="edad")
	private int edad;

	
	public PruebaPs() { }
	
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
}
