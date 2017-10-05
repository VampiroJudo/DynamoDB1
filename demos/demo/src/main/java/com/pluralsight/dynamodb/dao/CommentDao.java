package com.pluralsight.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.transactions.Transaction;
import com.amazonaws.services.dynamodbv2.transactions.TransactionManager;
import com.pluralsight.dynamodb.domain.Comment;
import com.pluralsight.dynamodb.domain.Item;

import java.util.List;

public class CommentDao {
	private DynamoDBMapper mapper;
	
	public CommentDao(AmazonDynamoDB dynamoDB) {
		mapper = new DynamoDBMapper(dynamoDB);
	}
	
	public Comment put(Comment comment) {
		mapper.save(comment);
		return comment;
	}
	
	public Comment get(String itemId, String messageId) {
		Comment comment = new Comment();
		comment.setItemId(itemId);
		comment.setMessage(messageId);
		
		return mapper.load(comment);
	}
	
	public void delete(String itenId, String messageId) {
		Comment comment = new Comment();
		comment.setItemId(itemId);
		comment.setMessageId(messageId);
		
		mapper.delete(comment);
	}
	
	public List<Comment> getAll() {
		return mapper.scan(Comment.class, new DynamoDBScanExpression());
	}
}
