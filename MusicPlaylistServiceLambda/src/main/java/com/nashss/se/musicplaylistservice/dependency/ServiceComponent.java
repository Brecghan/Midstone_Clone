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

    /**
     * Provides the relevant activity.
     * @return AddSongToPlaylistActivity
     */

    /**
     * Provides the relevant activity.
     * @return CreatePlaylistActivity
     */


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


    //--------------------------- DIGITAL PANTRY SERVICE COMPONENT

    CreatePantryActivity provideCreatePantryActivity();

    GetPantryListActivity provideGetPantryListActivity();

}
