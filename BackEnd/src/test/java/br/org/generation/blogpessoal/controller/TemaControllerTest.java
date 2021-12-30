package br.org.generation.blogpessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.org.generation.blogpessoal.model.Tema;
import br.org.generation.blogpessoal.repository.TemaRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TemaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private TemaRepository temaRepository;

	@Test
	@Order(1)
	@DisplayName("Cadastrar um Tema")
	public void deveCriarUmTema() {

		HttpEntity<Tema> requisicao = new HttpEntity<Tema>(new Tema(0L, "Java123"));

		ResponseEntity<Tema> resposta = testRestTemplate.withBasicAuth("root", "root").exchange("/temas",
				HttpMethod.POST, requisicao, Tema.class);

		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
		assertEquals(requisicao.getBody().getDescricao(), resposta.getBody().getDescricao());
	}

	@Test
	@Order(2)
	@DisplayName("Alterar um Tema")
	public void deveAtualizarUmtema() {

		Tema temaCreate = temaRepository.save(new Tema(0L, "java12"));

		Tema temaUpdate = new Tema(temaCreate.getId(), "Assembly");

		HttpEntity<Tema> requisicao = new HttpEntity<Tema>(temaUpdate);

		ResponseEntity<Tema> resposta = testRestTemplate.withBasicAuth("root", "root").exchange("/temas",
				HttpMethod.PUT, requisicao, Tema.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(temaUpdate.getDescricao(), resposta.getBody().getDescricao());

	}

	@Test
	@Order(3)
	@DisplayName("Listar todos os Temas")
	public void deveMostrarTodosTemas() {
		temaRepository.save(new Tema(0L, "Cobol"));

		temaRepository.save(new Tema(0L, "Swift"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root", "root").exchange("/temas",
				HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(4)
	@DisplayName("Apagar um Tema")
	public void deveApagarUmTema() {

		Tema temaDelete = temaRepository.save(new Tema(0L, "JavaScript"));

		ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root", "root")
				.exchange("/temas/" + temaDelete.getId(), HttpMethod.DELETE, null, String.class);

		assertEquals(HttpStatus.NO_CONTENT, resposta.getStatusCode());
	}

}
