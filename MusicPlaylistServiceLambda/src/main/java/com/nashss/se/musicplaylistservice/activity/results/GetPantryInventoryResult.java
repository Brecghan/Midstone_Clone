package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.dynamodb.models.IngredientModel;

import java.util.ArrayList;
import java.util.List;

public class GetPantryInventoryResult {
    private final List<IngredientModel> inventory;

    private GetPantryInventoryResult(List<IngredientModel> inventory) {
        this.inventory = inventory;
    }

    public List<IngredientModel> getInventory() {
        return new ArrayList<>(inventory);
    }

    @Override
    public String toString() {
        return "GetPantryInventoryResult{" +
                "inventory=" + inventory +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<IngredientModel> inventory;

        public Builder withInventory(List<IngredientModel> inventory) {
            this.inventory = new ArrayList<>(inventory);
            return this;
        }

        public GetPantryInventoryResult build() {
            return new GetPantryInventoryResult(inventory);
        }
    }
}
