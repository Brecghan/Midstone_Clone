package com.nashss.se.musicplaylistservice.converters;

import com.nashss.se.musicplaylistservice.dynamodb.models.Ingredient;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import com.nashss.se.musicplaylistservice.models.IngredientModel;
import com.nashss.se.musicplaylistservice.models.PantryModel;

import com.nashss.se.musicplaylistservice.dynamodb.models.AlbumTrack;
import com.nashss.se.musicplaylistservice.dynamodb.models.Playlist;
import com.nashss.se.musicplaylistservice.models.PlaylistModel;
import com.nashss.se.musicplaylistservice.models.SongModel;

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

    public IngredientModel toIngredientModel(Ingredient ingredient) {

        return IngredientModel.builder()
                .withIngredientName(ingredient.getIngredientName())
                .withQuantity(ingredient.getQuantity())
                .withUnitOfMeasure(ingredient.getUnitOfMeasure().name())
                .build();
    }
//
//    /**
//     * Converts a provided AlbumTrack into a SongModel representation.
//     *
//     * @param albumTrack the AlbumTrack to convert to SongModel
//     * @return the converted SongModel with fields mapped from albumTrack
//     */
//    public SongModel toSongModel(AlbumTrack albumTrack) {
//        return SongModel.builder()
//                .withAsin(albumTrack.getAsin())
//                .withTrackNumber(albumTrack.getTrackNumber())
//                .withAlbum(albumTrack.getAlbumName())
//                .withTitle(albumTrack.getSongTitle())
//                .build();
//    }
//
//    /**
//     * Converts a list of AlbumTracks to a list of SongModels.
//     *
//     * @param albumTracks The AlbumTracks to convert to SongModels
//     * @return The converted list of SongModels
//     */
//    public List<SongModel> toSongModelList(List<AlbumTrack> albumTracks) {
//        List<SongModel> songModels = new ArrayList<>();
//
//        for (AlbumTrack albumTrack : albumTracks) {
//            songModels.add(toSongModel(albumTrack));
//        }
//
//        return songModels;
//    }
//
//    /**
//     * Converts a list of Playlists to a list of PlaylistModels.
//     *
//     * @param playlists The Playlists to convert to PlaylistModels
//     * @return The converted list of PlaylistModels
//     */
//    public List<PlaylistModel> toPlaylistModelList(List<Playlist> playlists) {
//        List<PlaylistModel> playlistModels = new ArrayList<>();
//
//        for (Playlist playlist : playlists) {
//            playlistModels.add(toPlaylistModel(playlist));
//        }
//
//        return playlistModels;
//    }
}
