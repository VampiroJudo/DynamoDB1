package com.pluralsight.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.*;
import com.pluralsight.dynamodb.domain.Item;

public class Utils {
	
	public static void createTables(AmazonDynamoDB dynamoDB) {
		DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(dynamoDB);
		
		createTable(Item.class, dynamoDBMapper, dynamoDB);
	}
	
	private static void createTable(Class<?> itemClass, DynamoDBMapper dynamoDBMapper, AmazonDynamoDB dynamoDB) {
        CreateTableRequest createTableRequest = dynamoDBMapper.generateCreateTableRequest(itemClass);
        createTableRequest.withProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
		
        if (!tableExists(dynamoDB, createTableRequest))
            dynamoDB.createTable(createTableRequest);
	}
	
	private static void waitForTableCreated(String tableName, AmazonDynamoDB dynamoDB) {
		while (true) {
			try {
				Thread.sleep(500);
				TableDescription table = dynamoDB.describeTable(new DescribeTableRequest(tableName)).getTable();
				if (table == null)
					continue;
				
				String tableStatus = table.getTableStatus();
				if (tableStatus.equals(TableStatus.ACTIVE.toString()))
					return;
			}  catch (ResourceNotFoundException ex) {
				
			}  catch (Exception ex) {
				 throw new RuntimeException(ex);
			}
		}
	}
}