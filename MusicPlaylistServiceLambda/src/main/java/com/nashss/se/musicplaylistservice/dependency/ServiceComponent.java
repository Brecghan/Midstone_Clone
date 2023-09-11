package com.nashss.se.musicplaylistservice.dependency;

import com.nashss.se.musicplaylistservice.activity.CompareRecipeAndInventoryActivity;
import com.nashss.se.musicplaylistservice.activity.CreateMealPlanActivity;
import com.nashss.se.musicplaylistservice.activity.CreatePantryActivity;
import com.nashss.se.musicplaylistservice.activity.GetMealPlanListActivity;
import com.nashss.se.musicplaylistservice.activity.GetMealPlanRecipeSetActivity;
import com.nashss.se.musicplaylistservice.activity.GetPantryActivity;
import com.nashss.se.musicplaylistservice.activity.GetPantryInventoryActivity;
import com.nashss.se.musicplaylistservice.activity.GetPantryListActivity;
import com.nashss.se.musicplaylistservice.activity.GetRecipeDetailsActivity;
import com.nashss.se.musicplaylistservice.activity.GetRecipesActivity;
import com.nashss.se.musicplaylistservice.activity.UpdateMealPlanActivity;
import com.nashss.se.musicplaylistservice.activity.UpdateMealPlanRecipeSetActivity;
import com.nashss.se.musicplaylistservice.activity.UpdatePantryActivity;
import com.nashss.se.musicplaylistservice.activity.UpdatePantryIngredientActivity;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

    /**
     * Provides the relevant activity.
     * @return CreatePantryActivity
     */
    CreatePantryActivity provideCreatePantryActivity();

    /**
     * Provides the relevant activity.
     * @return GetPantryListActivity
     */
    GetPantryListActivity provideGetPantryListActivity();

    /**
     * Provides the relevant activity.
     * @return GetPantryInventoryActivity
     */
    GetPantryInventoryActivity provideGetPantryInventoryActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePantryIngredientActivity
     */
    UpdatePantryIngredientActivity provideUpdatePantryIngredientActivity();

    /**
     * Provides the relevant activity.
     * @return UpdatePantryActivity
     */
    UpdatePantryActivity provideUpdatePantryActivity();

    /**
     * Provides the relevant activity.
     * @return CreateMealPlanActivity
     */
    CreateMealPlanActivity provideCreateMealPlanActivity();

    /**
     * Provides the relevant activity.
     * @return GetMealPlanListActivity
     */
    GetMealPlanListActivity provideGetMealPlanListActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateMealPlanActivity
     */
    UpdateMealPlanActivity provideUpdateMealPlanActivity();

    /**
     * Provides the relevant activity.
     * @return GetMealPlanRecipeSetActivity
     */
    GetMealPlanRecipeSetActivity provideGetMealPlanRecipeSetActivity();

    /**
     * Provides the relevant activity.
     * @return UpdateMealPlanRecipeSetActivity
     */
    UpdateMealPlanRecipeSetActivity provideUpdateMealPlanRecipeSetActivity();

    /**
     * Provides the relevant activity.
     * @return GetRecipesActivity
     */
    GetRecipesActivity provideGetRecipesActivity();

    /**
     * Provides the relevant activity.
     * @return GetRecipeDetailsActivity
     */
    GetRecipeDetailsActivity provideGetRecipeDetailsActivity();

    /**
     * Provides the relevant activity.
     * @return CompareRecipeAndInventoryActivity
     */
    CompareRecipeAndInventoryActivity provideCompareRecipeAndInventoryActivity();

    /**
     * Provides the relevant activity.
     * @return CompareRecipeAndInventoryActivity
     */
    GetPantryActivity provideGetPantryActivity();
}
