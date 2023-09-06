package com.nashss.se.musicplaylistservice.models;

import java.util.Objects;

public class IngredientModel {
    private final String ingredientName;
    private final Double quantity;
    private final String unitOfMeasure;

    private IngredientModel(String ingredientName, Double quantity, String unitOfMeasure) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unitOfMeasure = unitOfMeasure;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        IngredientModel that = (IngredientModel) o;

        return  Objects.equals(ingredientName, that.ingredientName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String ingredientName;
        private Double quantity;
        private String unitOfMeasure;

        public Builder withIngredientName(String ingredientName) {
            this.ingredientName = ingredientName;
            return this;
        }

        public Builder withQuantity(Double quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder withUnitOfMeasure(String unitOfMeasure) {
            this.unitOfMeasure = unitOfMeasure;
            return this;
        }

        public IngredientModel build() {
            return new IngredientModel(ingredientName, quantity, unitOfMeasure);
        }
    }
}
