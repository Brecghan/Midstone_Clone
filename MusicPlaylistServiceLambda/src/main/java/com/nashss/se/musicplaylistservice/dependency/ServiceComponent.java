package com.nashss.se.musicplaylistservice.dependency;


import com.nashss.se.musicplaylistservice.activity.CreatePantryActivity;
import com.nashss.se.musicplaylistservice.activity.GetPantryInventoryActivity;
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


//    /**
//     * Provides the relevant activity.
//     * @return AddSongToPlaylistActivity
//     */
//    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

//    /**
//     * Provides the relevant activity.
//     * @return CreatePlaylistActivity
//     */
//
//    CreatePlaylistActivity provideCreatePlaylistActivity();


//    /**
//     * Provides the relevant activity.
//     * @return GetPlaylistActivity
//     */
//    //GetPlaylistActivity provideGetPlaylistActivity();
//
//    /**
//     * Provides the relevant activity.
//     * @return GetPlaylistActivity
//     */
//    SearchPlaylistsActivity provideSearchPlaylistsActivity();

//    /**
//     * Provides the relevant activity.
//     * @return GetPlaylistSongsActivity
//     */
//    GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

//    /**
//     * Provides the relevant activity.
//     * @return UpdatePlaylistActivity
//     */
//    UpdatePlaylistActivity provideUpdatePlaylistActivity();

    //--------------------------- DIGITAL PANTRY SERVICE COMPONENT

    CreatePantryActivity provideCreatePantryActivity();

    GetPantryListActivity provideGetPantryListActivity();

    GetPantryInventoryActivity provideGetPantryInventoryActivity();
    UpdatePantryIngredientActivity provideUpdatePantryIngredientActivity();
    UpdatePantryActivity provideUpdatePantryActivity();

}
