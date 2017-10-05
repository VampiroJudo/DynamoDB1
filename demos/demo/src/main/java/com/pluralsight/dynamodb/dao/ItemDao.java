package com.pluralsight.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.pluralsight.dynamodb.domain.Item;

import java.util.List;

public class ItemDao {
	private final AmazonDynamoDB dynamoDB;
	
    public ItemDao(AmazonDynamoDB dynamoDB) {
    		this.dynamoDB = dynamoDB;
    }
    
    public void put(Item item) {
    		Map<String, AttributeValue> itemMap = new HashMap<String, AttributeValue>();
    		itemMap.put("id",
    				new AttributeValue().withS(item.getId()));
    		
    		if (item.getName() != null)
    			itemMap.put("name",
    					new AttributeValue().withS(item.getName()));
    		
    		if (item.getDescription() != null)
    			itemMap.put("description",
    					new AttributeValue().withS(item.getDescription()));
    		
    		itemMap.put("totalRating",
    				new AttributeValue().withN (
    						Integer.String(item.getTotalRating())
    				));
    		
    		itemMap.put("totalComments",
    				new AttributeValue().withN(
    						Integer.toString(item.getTotalComments())
    				));
    		
    		PutItemRequest putItemRequest = new PutItemRequest( tablename: "Items", itemMap);
    		dynamoDB.putItem(putItemRequest);
    }
    
    
}
