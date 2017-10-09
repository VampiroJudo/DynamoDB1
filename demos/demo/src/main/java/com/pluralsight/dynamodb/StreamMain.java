package com.pluralsight.dynamodb;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.streamsadapter.AmazonDynamoDBStreamsAdapterClient;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.KinesisClientLibConfiguration;
import com.amazonaws.services.kinesis.clientlibrary.lib.worker.Worker;
import com.pluralsight.dynamodb.streams.StreamsRecordProcessorFactory;

public class StreamMain {
	public static void main(String...  args) {
		String streamArn = "arn:aws:dynamodb:us-east-1:710960103427:table/Orders/stream/2017-10-07T19:39:46.080";
		ProfileCredentialsProvider streamsCredentials = new ProfileCredentialsProvider();
		
		AmazonDynamoDBStreamsAdapterClient adaptorClient = 
				new AmazonDynamoDBStreamsAdapterClient(
						streamsCredentials,
						new ClientConfiguration()
				);
		KinesisClientLibConfiguration workerConfig = new KinesisClientLibConfiguration(
				"streams-adapter-demo",
				streamArn,
				streamsCredentials,
				"stream-demo-worker"
		)
		.withInitialPositionInStream(InitialPositionInStream.LATEST);
		
		Worker worker = new Worker.Builder()
				.recordProcessorFactory(new StreamsRecordProcessorFactory())
				.config(workerConfig)
				.kinesisClient(adaptorClient)
				.build();
				
		worker.run();
	}

}
