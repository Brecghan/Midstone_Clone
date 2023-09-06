package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.IngredientModel;
import com.nashss.se.musicplaylistservice.models.PantryModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Converts between Data and API models.
 */
public class ModelConverter {
    /**
     * Converts a provided {@link Pantry} into a {@link PantryModel} representation.
     *
     * @param pantry the pantry to convert
     * @return the converted pantry
     */
    public PantryModel toPantryModel(Pantry pantry) {
        Set<Ingredient> inventory = null;
        if (pantry.getInventory() != null) {
            inventory = new HashSet<>(pantry.getInventory());
        }

        return PantryModel.builder()
                .withPantryId(pantry.getPantryId())
                .withPantryName(pantry.getPantryName())
                .withUserId(pantry.getUserId())
                .withInventory(inventory)
                .build();
    }
    /**
     * Converts a provided {@link Ingredient} into a {@link IngredientModel} representation.
     *
     * @param ingredient the ingredient to convert
     * @return the converted ingredient
     */
    public IngredientModel toIngredientModel(Ingredient ingredient) {

        return IngredientModel.builder()
                .withIngredientName(ingredient.getIngredientName())
                .withQuantity(ingredient.getQuantity())
                .withUnitOfMeasure(ingredient.getUnitOfMeasure().name())
                .build();
    }

    /**
     * Converts a list of Pantries to a list of PantryModels.
     *
     * @param pantries The Pantries to convert to PantryModels
     * @return The converted list of PantryModels
     */
    public List<PantryModel> toPantryModelList(List<Pantry> pantries) {
        List<PantryModel> pantryModels = new ArrayList<>();

        for (Pantry pantry : pantries) {
            pantryModels.add(toPantryModel(pantry));
        }

        return pantryModels;
    }
    /**
     * Converts a list of Ingredients to a list of IngredientModels.
     *
     * @param ingredients The Ingredients to convert to IngredientModels
     * @return The converted list of IngredientModels
     */
    public List<IngredientModel> toIngredientModelList(List<Ingredient> ingredients) {
        List<IngredientModel> ingredientModels = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientModels.add(toIngredientModel(ingredient));
        }

        return ingredientModels;
    }
}
