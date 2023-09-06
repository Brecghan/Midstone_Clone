package com.nashss.se.musicplaylistservice.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a record in the pantries table.
 */
@DynamoDBTable(tableName = "Pantries")
public class Pantry {
    private String pantryId;
    private String pantryName;
    private String userId;
    private Set<Ingredient> inventory;

    @DynamoDBRangeKey(attributeName = "pantryId")
    public String getPantryId() {
        return pantryId;
    }

    public void setPantryId(String pantryId) {
        this.pantryId = pantryId;
    }

    @DynamoDBAttribute(attributeName = "pantryName")
    public String getPantryName() {
        return pantryName;
    }

    public void setPantryName(String pantryName) {
        this.pantryName = pantryName;
    }

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the set of Ingredients associated with this Pantry, null if there are none.
     *
     * @return Set of Ingredients for this Pantry
     */
    @DynamoDBAttribute(attributeName = "inventory")
    public Set<Ingredient> getInventory() {
        // normally, we would prefer to return an empty Set if there is no
        // inventory, but DynamoDB doesn't represent empty Sets...needs to be null
        // instead
        if (null == inventory) {
            return null;
        }

        return new HashSet<>(inventory);
    }

    /**
     * Sets the Inventory for this Pantry as a copy of input, or null if input is null.
     *
     * @param inventory Set of ingredients for this Pantry
     */
    public void setInventory(Set<Ingredient> inventory) {
        // see comment in getInventory()
        if (null == inventory) {
            this.inventory = null;
        } else {
            this.inventory = new HashSet<>(inventory);
        }

        this.inventory = inventory;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pantry pantry = (Pantry) o;
        return pantryId.equals(pantry.pantryId) &&
                pantryName.equals(pantry.pantryName) &&
                userId.equals(pantry.userId) &&
                Objects.equals(inventory, pantry.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pantryId, pantryName, userId, inventory);
    }

}
