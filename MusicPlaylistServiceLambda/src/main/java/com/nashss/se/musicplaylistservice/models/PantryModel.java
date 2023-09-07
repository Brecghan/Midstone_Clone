package com.nashss.se.musicplaylistservice.models;

import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class PantryModel {
    private final String pantryId;
    private final String pantryName;
    private final String userId;
    private final Set<Ingredient> inventory;

    private PantryModel(String pantryId, String pantryName, String userId,
                        Set<Ingredient> inventory) {
        this.pantryId = pantryId;
        this.pantryName = pantryName;
        this.userId = userId;
        this.inventory = inventory;
    }

    public String getPantryId() {
        return pantryId;
    }

    public String getPantryName() {
        return pantryName;
    }

    public String getUserId() {
        return userId;
    }

    public Set<Ingredient> getInventory() {
        return new HashSet<>(inventory);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PantryModel that = (PantryModel) o;

        return  Objects.equals(pantryId, that.pantryId) && Objects.equals(pantryName, that.pantryName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(inventory, that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pantryId, pantryName, userId, inventory);
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String pantryId;
        private String pantryName;
        private String userId;
        private Set<Ingredient> inventory;

        public Builder withPantryId(String pantryId) {
            this.pantryId = pantryId;
            return this;
        }

        public Builder withPantryName(String pantryName) {
            this.pantryName = pantryName;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withInventory(Set<Ingredient> inventory) {
//            this.inventory = new HashSet<>(inventory);
            if (null == inventory) {
                this.inventory = null;
            } else {
                this.inventory = new HashSet<>(inventory);
            }
            return this;
        }

        public PantryModel build() {
            return new PantryModel(pantryId, pantryName, userId, inventory);
        }
    }
}
