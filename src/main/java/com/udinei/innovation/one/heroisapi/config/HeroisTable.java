package com.udinei.innovation.one.heroisapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import static com.udinei.innovation.one.heroisapi.constants.HeroiConstant.REGION_DYNAMO;
import static com.udinei.innovation.one.heroisapi.constants.HeroiConstant.ENDPOINT_DYNAMO;
import com.amazonaws.services.dynamodbv2.model.*;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * Essa classe cria a tabela Herois_Table no banco DynamoDB
 * tendo como chave da tabela o id
 * */
@Configuration
@EnableDynamoDBRepositories
public class HeroisTable {

    public static void main(String[] args) {
        // instanciando cliente do Banco no endpoint e regiao passadas como parametro
        // o Banco e as credenciais do AWS ambos devem ser configurados na mesma regiao
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        // instancia e habilita driver para uso do cliente
        DynamoDB dynamoDB = new DynamoDB(client);

        // nome da tabela que ira armazenar os herois
        String tableName = "Herois_Table";

        try {
            System.out.println("Criando tabela, aguarde...");

            // cria tabela no BD usando id como chave
            Table table = dynamoDB.createTable(tableName,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)
                    ),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)
                    ),
                    // taxa de transferência provisionadas para tabela ou índice.
                    new ProvisionedThroughput(5L, 5L));

            table.waitForActive(); // aguarda tabela a ser criada

            System.out.println("Tabela criada com Successo!........" + table.getDescription().getTableStatus());

        } catch (Exception e) {
            System.err.println("Não foi possível criar a tabela");
            System.err.println(e.getMessage());
        }
    }

}
