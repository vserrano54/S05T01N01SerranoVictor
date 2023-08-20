package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="tbl_rol")
public class Rol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id_rol", columnDefinition = "Integer")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRol;
	
	@NotEmpty
	@Column(name = "rol", columnDefinition = "VARCHAR(45)")
	private String Rol;

	public Rol() {}
	
	public Rol(int idRol, @NotEmpty String rol) {
		this.idRol = idRol;
		Rol = rol;
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getRol() {
		return Rol;
	}

	public void setRol(String rol) {
		Rol = rol;
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", Rol=" + Rol + "]";
	}

}