package com.nashss.se.musicplaylistservice.converters;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.LinkedList;
import java.util.Set;


/**
 * PARTICIPANTS: The class should NOT be touched when working on the project!
 * DynamoDBMapper converts lists to {@link java.util.ArrayList}s by default, but for our project,
 * we want to convert to a {@link LinkedList}
 */
public class RecipeSetConverter implements DynamoDBTypeConverter<String, Set<String>> {
    private static final Gson GSON = new Gson();
    private final Logger log = LogManager.getLogger();

    @Override
    public String convert(Set setToBeConverted) {
        return GSON.toJson(setToBeConverted);
    }

    @Override
    public Set<String> unconvert(String dynamoDbRepresentation) {
        // need to provide the type parameter of the list to convert correctly
        return GSON.fromJson(dynamoDbRepresentation, new TypeToken<Set<String>>() { } .getType());
    }
}
