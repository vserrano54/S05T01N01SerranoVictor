package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="tbl_user")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "id_user", columnDefinition = "Integer")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUsuario;
	
	@NotEmpty
	@Column(name = "username", columnDefinition = "VARCHAR(45)")
	private String userName;
	
	@NotEmpty
	@Column(name = "password", columnDefinition = "VARCHAR(128)")
	private String password;
	
	@NotEmpty
	@Column(name = "enabled", columnDefinition = "TINYINT")
	private int enabled;
	
	@OneToMany
	@JoinColumn(name="id_user")
	private List<Rol> roles;
	
	public Usuario() {}

	public Usuario(int idUsuario, @NotEmpty String userName, @NotEmpty String password, @NotEmpty int enabled,
			List<Rol> roles) {
		this.idUsuario = idUsuario;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.roles = roles;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", userName=" + userName + ", password=" + password + ", enabled="
				+ enabled + ", roles=" + roles + "]";
	}
}
