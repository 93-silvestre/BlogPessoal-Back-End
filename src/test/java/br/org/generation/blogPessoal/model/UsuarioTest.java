package br.org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;



@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // porta estiver em uso , criar uma nova
public class UsuarioTest {
	
	    public Usuario usuario;
		public Usuario usuarioNulo = new Usuario(); // inicializando usuario nulo

		@Autowired
		private  ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		
		Validator validator = factory.getValidator(); // verificar se existe alguma condição que não esta sendo respeitada e vai verificar aonde tem erro

		@BeforeEach // execute este metodo antes de cada teste
		public void start() {

			LocalDate data = LocalDate.parse("1993-07-23", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
	        usuario = new Usuario(0L, "Nathalia Silvestre", "nathalia@email.com.br", "13465278", data);

		}

		@Test // criando teste 
		@DisplayName("✔ Valida Atributos Não Nulos ") // criar nomes assertivos // criar emoji windows+.
		void testValidaAtributos() {
            
			//set não aceita dado duplicado
			Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
			
			System.out.println(violacao.toString());

			assertTrue(violacao.isEmpty());
		}

		@Test
		@DisplayName("✖ Não Valida Atributos Nulos")
		void testNaoValidaAtributos() {

			Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);

			System.out.println(violacao.toString());

			assertFalse(violacao.isEmpty());
		}
}
