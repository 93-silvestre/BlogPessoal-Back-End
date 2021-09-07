package br.org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import br.org.generation.blogPessoal.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	    
	    @Autowired
		private UsuarioRepository usuarioRepository;
		
		@BeforeAll // execute uma unica vez e depois todos os testes serão inicializado na sequencia
		void start(){
		   
			LocalDate data = LocalDate.parse("1993-07-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			Usuario usuario = new Usuario(0, "Nathalia Silvestre", "nathalia@email.com.br", "13465278", data);

			if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
				usuarioRepository.save(usuario); // verificando se não tem usuario duplicado // ! - usuario nao esta presente
			
			usuario = new Usuario(0, "Manuel Silvestre", "manuel@email.com.br", "13465278", data);
			if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
				usuarioRepository.save(usuario);
			
			usuario = new Usuario(0, "Frederico Silvestre", "frederico@email.com.br", "13465278", data);
			if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
				usuarioRepository.save(usuario);

	        usuario = new Usuario(0, "Paulo Antunes", "paulo@email.com.br", "13465278", data);
	        if(!usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
	            usuarioRepository.save(usuario);
		}

		@Test
		@DisplayName("💾 Retorna o nome")
		public void findByNomeRetornaNome() {

			Usuario usuario = usuarioRepository.findByNome("Nathalia Silvestre"); // é verdade que o que procurou 
			assertTrue(usuario.getNome().equals("Nathalia Silvestre")); // esta encontrando o nome ?
		}

		@Test
		@DisplayName("💾 Retorna 3 usuarios")
		public void findAllByNomeContainingIgnoreCaseRetornaTresUsuarios() {

			List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silvestre");
			assertEquals(3, listaDeUsuarios.size()); // é verdade que a minha lista de usuarios é 3 , se achou 3 pessoas com sobrenome Silvestre 
		
		}

		@AfterAll
		public void end() {
			
			System.out.println("Teste Finalizado!");
			
		}
	}

