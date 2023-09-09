package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;
import com.nashss.se.musicplaylistservice.dynamodb.models.MealPlan;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.IngredientModel;
import com.nashss.se.musicplaylistservice.models.MealPlanModel;
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
    public List<IngredientModel> toIngredientModelList(Set<Ingredient> ingredients) {
        List<IngredientModel> ingredientModels = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientModels.add(toIngredientModel(ingredient));
        }

        return ingredientModels;
    }

    /**
     * Converts a provided {@link MealPlan} into a {@link MealPlanModel} representation.
     *
     * @param mealPlan the mealPlan to convert
     * @return the converted mealPlan
     */
    public MealPlanModel toMealPlanModel(MealPlan mealPlan) {
        Set<String> recipeSet = null;
        if (mealPlan.getRecipeSet() != null) {
            recipeSet = new HashSet<>(mealPlan.getRecipeSet());
        }

        return MealPlanModel.builder()
                .withMealPlanId(mealPlan.getMealPlanId())
                .withMealPlanName(mealPlan.getMealPlanName())
                .withUserId(mealPlan.getUserId())
                .withRecipeSet(recipeSet)
                .build();
    }

    /**
     * Converts a list of Meal Plans to a list of MealPlanModels.
     *
     * @param mealPlans The Meal Plans to convert to MealPlanModels
     * @return The converted list of MealPlanModels
     */
    public List<MealPlanModel> toMealPlanModelList(List<MealPlan> mealPlans) {
        List<MealPlanModel> mealPlanModels = new ArrayList<>();

        for (MealPlan mealPlan : mealPlans) {
            mealPlanModels.add(toMealPlanModel(mealPlan));
        }

        return mealPlanModels;
    }
}
