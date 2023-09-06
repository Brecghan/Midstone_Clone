package com.nashss.se.musicplaylistservice.dependency;

import com.nashss.se.musicplaylistservice.activity.*;

import dagger.Component;

import javax.inject.Singleton;

/**
 * Dagger component for providing dependency injection in the Music Playlist Service.
 */
@Singleton
@Component(modules = {DaoModule.class, MetricsModule.class})
public interface ServiceComponent {

<<<<<<< HEAD
    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */
=======
//    /**
//     * Provides the relevant activity.
//     * @return AddSongToPlaylistActivity
//     */
//    AddSongToPlaylistActivity provideAddSongToPlaylistActivity();
>>>>>>> main

//    /**
//     * Provides the relevant activity.
//     * @return CreatePlaylistActivity
//     */
//
//    CreatePlaylistActivity provideCreatePlaylistActivity();

<<<<<<< HEAD

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */

    /**
     * Provides the relevant activity.
     * @return GetPlaylistActivity
     */

    /**
     * Provides the relevant activity.
     * @return GetPlaylistSongsActivity
     */

    /**
     * Provides the relevant activity.
     * @return UpdatePlaylistActivity
     */

=======
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
>>>>>>> main

    //--------------------------- DIGITAL PANTRY SERVICE COMPONENT

    CreatePantryActivity provideCreatePantryActivity();

<<<<<<< HEAD
    GetPantryListActivity provideGetPantryListActivity();

=======
    GetPantryInventoryActivity provideGetPantryInventoryActivity();
    UpdatePantryIngredientActivity provideUpdatePantryIngredientActivity();
    //UpdatePantryActivity provideUpdatePantryActivity();
>>>>>>> main
}
