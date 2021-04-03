package com.udinei.innovation.one.heroisapi.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

import static com.udinei.innovation.one.heroisapi.constants.HeroiConstant.*;
/*
Essa classe grava os dados na tabela do dynamoDB

* **/
public class HeroisData {

    public static DynamoDB dynamoDB = getConexaoDynamoDB();
    public static void main(String[] args) {


        // cria tabela no BD
        Table table = dynamoDB.getTable("Herois_Table");

        // criacao de um novo item(heroi) a ser inserido no BD
        Item heroi = new Item()
        .withPrimaryKey("id","2")
                .withString("name","Mulher Maravilha")
                .withString("universe", "dc comics")
                .withNumber("films", 3);

        Item heroi2 = new Item()
                .withPrimaryKey("id", "3")
                .withString("name", "Viuva negra")
                .withString("universe", "marvel")
                .withNumber("films", 4);

        Item heroi3 = new Item()
                .withPrimaryKey("id", "4")
                .withString("name", "Capita marvel")
                .withString("universe", "marvel")
                .withNumber("films", 5);

        // insere o item heroi no banco
        PutItemOutcome outcome = table.putItem(heroi);
        PutItemOutcome outcome2 = table.putItem(heroi2);
        PutItemOutcome outcome3 = table.putItem(heroi3);

        System.out.println("PutItem 1 succeeded:\n" + outcome.getPutItemResult());
        System.out.println("PutItem 2 succeeded:\n" + outcome2.getPutItemResult());
        System.out.println("PutItem 3 succeeded:\n" + outcome3.getPutItemResult());

    }

    public static  DynamoDB getConexaoDynamoDB() {
        // conecta o cliente no endpoint - ENDPOINT_DYNAMO
        // na regiao REGION_DYNAMO e retorna um cliente abilitado pela AWS
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
        .build();

        // cria uma conexao do cliente AWS com o dynamoDB
        DynamoDB dynamoDB = new DynamoDB(client);
        return dynamoDB;
    }
}
