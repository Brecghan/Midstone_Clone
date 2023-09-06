package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = UpdatePantryIngredientRequest.Builder.class)
public class UpdatePantryIngredientRequest {
    private final String pantryId;
    private final String ingredientName;
    private final String userId;
    private final Double ingredientQuantity;
    private final String unitOfMeasure;

    private UpdatePantryIngredientRequest(String pantryId, String ingredientName,
                                          String userId, Double ingredientQuantity, String unitOfMeasure) {
        this.pantryId = pantryId;
        this.ingredientName = ingredientName;
        this.userId = userId;
        this.ingredientQuantity = ingredientQuantity;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getPantryId() {
        return pantryId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public String getUserId() {
        return userId;
    }

    public Double getIngredientQuantity() {
        return ingredientQuantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public String toString() {
        return "UpdatePlaylistRequest{" +
                "pantryId='" + pantryId + '\'' +
                ", ingredientName='" + ingredientName + '\'' +
                ", userId='" + userId + '\'' +
                ", ingredientQuantity='" + ingredientQuantity + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String pantryId;
        private String ingredientName;
        private String userId;
        private Double ingredientQuantity;
        private String unitOfMeasure;

        public Builder withPantryId(String pantryId) {
            this.pantryId = pantryId;
            return this;
        }

        public Builder withIngredientName(String ingredientName) {
            this.ingredientName = ingredientName;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withIngredientQuantity(Double ingredientQuantity) {
            this.ingredientQuantity = ingredientQuantity;
            return this;
        }

        public Builder withUnitOfMeasure(String unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }


        public UpdatePantryIngredientRequest build() {
            return new UpdatePantryIngredientRequest(pantryId, ingredientName, userId, ingredientQuantity, unitOfMeasure);
        }
    }
}
