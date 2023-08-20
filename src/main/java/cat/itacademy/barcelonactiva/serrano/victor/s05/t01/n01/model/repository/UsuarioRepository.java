package cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;



import cat.itacademy.barcelonactiva.serrano.victor.s05.t01.n01.model.domain.Usuario;

//@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUserName(String username);
}

