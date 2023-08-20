package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.dto;

import javax.validation.constraints.NotEmpty;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Pais;

public class SucursalDto {
	
	private int id;
	@NotEmpty
	private String nombre;
	private Pais pais;
	private String tipoSucursal;
	
	public SucursalDto() {}
	
	public SucursalDto(int id, String nombre, Pais pais, String tipoSucursal) {
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.tipoSucursal = tipoSucursal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getTipoSucursal() {
		return tipoSucursal;
	}

	public void setTipoSucursal(String tipoSucursal) {
		this.tipoSucursal = tipoSucursal;
	}

	@Override
	public String toString() {
		return "SucursalDto [id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", tipoSucursal=" + tipoSucursal + "]";
	}
	
}
