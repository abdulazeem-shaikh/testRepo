package com.internetbanking.app.customer.configuratin;

import javax.inject.Inject;
import javax.inject.Named;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.internetbanking.app.customer.properties.InternetBankingProperties;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

@Configuration
public class DBConfiguration {
	InternetBankingProperties properties;

	@Inject
	public DBConfiguration(InternetBankingProperties properties) {
		this.properties = properties;
	}

	MongoClientURI connectionString = new MongoClientURI("mongodb://localhost/bankdb");
	MongoClient mongoClient = new MongoClient(connectionString);
	MongoDatabase database = mongoClient.getDatabase("bankdb");
	CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
            fromProviders(PojoCodecProvider.builder().automatic(true).build()));

	@Bean
	@Named("customer")
	MongoCollection<Document> getCustmerCollection() {
		MongoCollection<Document> collection = database.withCodecRegistry(pojoCodecRegistry).getCollection("customer");
		return collection;
	}

	@Bean
	@Named("transaction")
	MongoCollection<Document> getTransactionCollection() {
		MongoCollection<Document> collection = database.withCodecRegistry(pojoCodecRegistry).getCollection("transaction");
		return collection;
	}
}
