package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import java.util.Objects;

/**
 * Represents a record in the album_tracks table.
 */
@DynamoDBDocument
public class Ingredient {
    private String ingredientName;
    private Double quantity;
    private UnitOfMeasure unitOfMeasure;
    public enum UnitOfMeasure { SHOT, CUP, LBS, EACH, HALF }

    @DynamoDBAttribute(attributeName = "IngredientName")
    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    @DynamoDBAttribute(attributeName = "Quantity")
    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    @DynamoDBTypeConvertedEnum
    @DynamoDBAttribute(attributeName = "UnitOfMeasure")
    public UnitOfMeasure getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;
        return ingredientName.equals(that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
               "IngredientName='" + ingredientName + '\'' +
               ", Quantity=" + quantity +
               ", UnitOfMeasure='" + unitOfMeasure + '\'' +
               '}';
    }
}
