package com.my.app.aws.adapter.dynamo;
/*
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Component
@Slf4j*/
public class DynamoDbTableCreator {
/*
    private final AmazonDynamoDB amazonDynamoDB;
    private final DynamoDBMapper mapper;

    public DynamoDbTableCreator(AmazonDynamoDB amazonDynamoDB) {
        this.amazonDynamoDB = amazonDynamoDB;
        mapper = new DynamoDBMapper(amazonDynamoDB);
    }

    @PostConstruct
    public void create(){
        Reflections reflections = new Reflections("com.my");
        Set<Class<?>> challengeClasses = reflections.getTypesAnnotatedWith(DynamoDBTable.class);

        challengeClasses
                .stream()
                .filter(this::filterNonExistent)
                .forEach(this::create);
    }

    private void create(Class<?> clazz) {
        CreateTableRequest tableRequest = mapper
                .generateCreateTableRequest(clazz);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);
        log.trace(tableRequest.getTableName() + " table created!");
    }


    private boolean filterNonExistent(Class<?> clazz) {
        List<String> existingTables = amazonDynamoDB.listTables().getTableNames();
        DynamoDBTable annotation = clazz.getAnnotation(DynamoDBTable.class);
        return !existingTables.contains(annotation.tableName());
    }
*/
}

