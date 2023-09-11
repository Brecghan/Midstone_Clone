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
@DynamoDBTable(tableName = "MealPlans")
public class MealPlan {
    private String mealPlanId;
    private String mealPlanName;
    private String userId;
    private Set<String> recipeSet;

    @DynamoDBRangeKey(attributeName = "mealPlanId")
    public String getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(String mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    @DynamoDBAttribute(attributeName = "mealPlanName")
    public String getMealPlanName() {
        return mealPlanName;
    }

    public void setMealPlanName(String mealPlanName) {
        this.mealPlanName = mealPlanName;
    }

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the set of RecipeId Strings associated with this MealPlan, null if there are none.
     *
     * @return Set of RecipeId Strings for this Pantry
     */
    @DynamoDBAttribute(attributeName = "recipeSet")
    public Set<String> getRecipeSet() {
        // normally, we would prefer to return an empty Set if there is no
        // inventory, but DynamoDB doesn't represent empty Sets...needs to be null
        // instead
        if (null == recipeSet) {
            return null;
        }

        return new HashSet<>(recipeSet);
    }

    /**
     * Sets the recipeSet for this MealPlan as a copy of input, or null if input is null.
     *
     * @param recipeSet Set of rescipeId Strings for this Meal Plan
     */
    public void setRecipeSet(Set<String> recipeSet) {
        // see comment in getRecipeSet()
        if (null == recipeSet) {
            this.recipeSet = null;
        } else {
            this.recipeSet = new HashSet<>(recipeSet);
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
        MealPlan pantry = (MealPlan) o;
        return mealPlanId.equals(pantry.mealPlanId) &&
                mealPlanName.equals(pantry.mealPlanName) &&
                userId.equals(pantry.userId) &&
                Objects.equals(recipeSet, pantry.recipeSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealPlanId, mealPlanName, userId, recipeSet);
    }

    @Override
    public String toString() {
        return "MealPlan{" +
                "mealPlanId='" + mealPlanId + '\'' +
                ", mealPlanName='" + mealPlanName + '\'' +
                ", userId='" + userId + '\'' +
                ", recipeSet=" + recipeSet +
                '}';
    }
}
