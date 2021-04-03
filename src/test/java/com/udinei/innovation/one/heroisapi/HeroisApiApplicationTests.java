package com.udinei.innovation.one.heroisapi;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.google.gson.Gson;
import com.udinei.innovation.one.heroisapi.buider.HeroiDTOBuilder;
import com.udinei.innovation.one.heroisapi.config.HeroisData;
import com.udinei.innovation.one.heroisapi.dto.HeroiDTO;
import com.udinei.innovation.one.heroisapi.exception.HeroiNotFoundException;
import com.udinei.innovation.one.heroisapi.repository.HeroisRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import static com.udinei.innovation.one.heroisapi.constants.HeroiConstant.*;
/**
 * Essa classe de testes tem dependencia de BD os dados usados nos testes dever ter
 * sido cadastrados anteriormente
 * */

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
public class HeroisApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;


	@Test
	public void getOneHeroiById(){

		webTestClient.get().uri(HEROIS_ENDPOINT_LOCAL.concat("/{id}"),"2")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeroiNotFound(){
		webTestClient.get().uri(HEROIS_ENDPOINT_LOCAL.concat("/{id}"),"14")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deleteHeroi(){
		// cria no banco o item que vai ser excluido cujo id=1
		createItemHaSerExcluido();

    // colocar um id valido para o teste passar
		webTestClient.delete().uri(HEROIS_ENDPOINT_LOCAL.concat("/{id}"),"200")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNoContent()
				.expectBody(Void.class);
	}


	@Test
	public void whenUpdateIsCalledWhithDTOWhatNotExistReturnisNotFound(){
		// biblioteca do google para conversao de entidades em json
		Gson gson = new Gson();

		//Essa instancia retorna HeroiDTO com id="2"
		HeroiDTO heroiDTO = HeroiDTOBuilder.builder().build().toHeroiDTO();

		heroiDTO.setId("200");// esse id nao existe no BD

		// converte DTO para json
		String json = gson.toJson(heroiDTO);

		webTestClient.put().uri(HEROIS_ENDPOINT_LOCAL)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(json)
				.accept(MediaType.APPLICATION_JSON)
		         .exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void whenUpdateIsCalledWhithDTOWhatExistReturnCreated(){
		// biblioteca do google para conversao de entidades em json
		Gson gson = new Gson();

		//Essa instancia retorna HeroiDTO com id="2"
		HeroiDTO heroiDTO = HeroiDTOBuilder.builder().build().toHeroiDTO();

		// converte DTO para json
		String json = gson.toJson(heroiDTO);

		webTestClient.put().uri(HEROIS_ENDPOINT_LOCAL)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(json)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isCreated();
	}

	@Test
	public void createItemHaSerExcluido() {
		DynamoDB conexao = HeroisData.getConexaoDynamoDB();

		// cria tabela no BD
		Table table = conexao.getTable("Herois_Table");

		// criacao de um novo item(heroi) a ser inserido no BD
		Item heroi = new Item()
				.withPrimaryKey("id","200")
				.withString("name","Super man")
				.withString("universe", "dc comics")
				.withNumber("films", 3);


		// insere o item heroi no banco
		PutItemOutcome outcome = table.putItem(heroi);
		System.out.println("PutItem 1 succeeded:\n" + outcome.getPutItemResult());
	}
}
