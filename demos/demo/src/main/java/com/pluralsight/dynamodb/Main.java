package com.pluralsight.dynamodb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomain;
import com.amazonaws.services.cloudsearchdomain.AmazonCloudSearchDomainClientBuilder;
import com.amazonaws.services.cloudsearchdomain.model.SearchRequest;
import com.amazonaws.services.cloudsearchdomain.model.SearchResult;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.pluralsight.dynamodb.dao.CommentDao;
import com.pluralsight.dynamodb.dao.ItemDao;
import com.pluralsight.dynamodb.domain.Comment;
import com.pluralsight.dynamodb.domain.Item;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Main {

		public static void main(String... args) {
			AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
					.withRegion(Regions.US_EAST_1)
					.build();
			
			lowLevelDemo(client);
		}
		
		private static void lowLevelDemo(AmazonDynamoDB client) {
			ItemDao itemDao = new ItemDao(client);
			
			Item item = new Item();
			item.setId(UUID.randomUUID().toString());
			item.setName("Bitcoin miner");
			
			itemDao.put(item);
		}
    
}
