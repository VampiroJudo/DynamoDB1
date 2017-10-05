package com.pluralsight.dynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.local.embedded.DynamoDBEmbedded;
import com.pluralsight.dynamodb.dao.ItemDao;
import com.pluralsight.dynamodb.domain.Item;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class testItemDao {
	static AmazonDynamoDB dynamoDB;
	static ItemDao itemDao;
	
	@Before
	public void before() {
		dynamoDB = DynamoDBEmbedded.create().amazonDynamoDB();
		Utils.createTables(dynamoDB);
		itemDao = new ItemDao(dynamoDB);
	}
	
	@Test
	public void testDynamoDB() {
		Item item = itemDao.put(new Item());
		assertNotNull(itemDao.get(item.getId()));
	}
 
}
