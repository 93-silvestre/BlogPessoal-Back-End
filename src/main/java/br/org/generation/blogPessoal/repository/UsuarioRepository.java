package br.org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.generation.blogPessoal.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional <Usuario> findByUsuario(String usuario);

	public Usuario findByNome(String string);

	public List<Usuario> findAllByNomeContainingIgnoreCase(String string);
	

}
