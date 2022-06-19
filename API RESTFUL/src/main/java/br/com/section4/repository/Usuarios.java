package br.com.section4.repository;

import br.com.section4.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Usuarios extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByUsername(String username);
}
