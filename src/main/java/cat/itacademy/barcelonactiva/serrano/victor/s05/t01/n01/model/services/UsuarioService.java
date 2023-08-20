package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Rol;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Usuario;
import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.repository.UsuarioRepository;
//import lombok.extern.slf4j.Slf4j;

@Service("userDeteilsSerice")
public class UsuarioService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usarioRepository;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usarioRepository.findByUserName(username);
		
		if (usuario == null) {
			
			throw new UsernameNotFoundException(username);
		}
		var roles = new ArrayList<GrantedAuthority>();
		
		for (Rol rol:usuario.getRoles() ) {
			roles.add(new SimpleGrantedAuthority(rol.getRol()));
		}
		return new User(usuario.getUserName(),usuario.getPassword(),roles);
	}
	
}
