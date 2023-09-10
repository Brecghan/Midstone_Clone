package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.nashss.se.musicplaylistservice.converters.IngredientLinkedListConverter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a record in the pantries table.
 */
@DynamoDBTable(tableName = "Recipes")
public class Recipe {
    public static final String RECIPE_REGION_INDEX = "RecipeRegionIndex";
    private String recipeId;
    private String recipeName;
    private String recipeDescription;
    private Set<Ingredient> neededIngredients;
    private String region;
    private Set<String> dietaryRestrictions;

    @DynamoDBHashKey(attributeName = "recipeId")
    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    @DynamoDBRangeKey(attributeName = "recipeName")
    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    @DynamoDBAttribute(attributeName = "recipeDescription")
    public String getRecipeDescription() {
        return recipeDescription;
    }

    public void setRecipeDescription(String recipeDescription) {
        this.recipeDescription = recipeDescription;
    }

    @DynamoDBIndexHashKey(globalSecondaryIndexNames = RECIPE_REGION_INDEX, attributeName = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * Returns the set of Ingredients associated with this Recipe, null if there are none.
     *
     * @return Set of RecipeId Strings for this Pantry
     */
    @DynamoDBTypeConverted(converter = IngredientLinkedListConverter.class)
    @DynamoDBAttribute(attributeName = "neededIngredients")
    public Set<Ingredient> getNeededIngredients() {
        // normally, we would prefer to return an empty Set if there is no
        // inventory, but DynamoDB doesn't represent empty Sets...needs to be null
        // instead
        if (null == neededIngredients) {
            return null;
        }

        return new HashSet<>(neededIngredients);
    }

    /**
     * Sets the neededIngredients for this Recipe as a copy of input, or null if input is null.
     *
     * @param neededIngredients Set of Ingredients for this Recipe
     */
    public void setNeededIngredients(Set<Ingredient> neededIngredients) {
        // see comment in getRecipeSet()
        if (null == neededIngredients) {
            this.neededIngredients = null;
        } else {
            this.neededIngredients = new HashSet<>(neededIngredients);
        }
    }

    /**
     * Returns the set of dietary restriction Strings associated with this recipe, null if there are none.
     *
     * @return Set of dietary restriction Strings for this Recipe
     */
    @DynamoDBAttribute(attributeName = "dietaryRestrictions")
    public Set<String> getDietaryRestrictions() {
        // normally, we would prefer to return an empty Set if there is no
        // inventory, but DynamoDB doesn't represent empty Sets...needs to be null
        // instead
        if (null == dietaryRestrictions) {
            return null;
        }

        return new HashSet<>(dietaryRestrictions);
    }

    /**
     * Sets the dietaryRestrictions for this Recipe as a copy of input, or null if input is null.
     *
     * @param dietaryRestrictions Set of dietaryRestriction Strings for this Recipe
     */
    public void setDietaryRestrictions(Set<String> dietaryRestrictions) {
        // see comment in getRecipeSet()
        if (null == dietaryRestrictions) {
            this.dietaryRestrictions = null;
        } else {
            this.dietaryRestrictions = new HashSet<>(dietaryRestrictions);
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Recipe pantry = (Recipe) o;
        return recipeId.equals(pantry.recipeId) &&
                recipeName.equals(pantry.recipeName) &&
                recipeDescription.equals(pantry.recipeDescription) &&
                Objects.equals(neededIngredients, pantry.neededIngredients) &&
                region.equals(pantry.region) &&
                Objects.equals(dietaryRestrictions, pantry.dietaryRestrictions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, recipeName, recipeDescription, neededIngredients, region, dietaryRestrictions);
    }

}
