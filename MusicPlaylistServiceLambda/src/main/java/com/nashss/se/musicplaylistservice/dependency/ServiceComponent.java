package com.nashss.se.musicplaylistservice.dependency;

import com.nashss.se.musicplaylistservice.activity.CreatePantryActivity;
import com.nashss.se.musicplaylistservice.activity.GetPantryInventoryActivity;
import com.nashss.se.musicplaylistservice.activity.GetPantryListActivity;
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

}
