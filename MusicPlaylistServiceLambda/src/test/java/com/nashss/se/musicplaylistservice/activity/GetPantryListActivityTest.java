package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.musicplaylistservice.activity.requests.GetPantryListRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPantryListResult;
//import com.nashss.se.musicplaylistservice.converters.ModelConverter;
import com.nashss.se.musicplaylistservice.dynamodb.PantryDao;
import com.nashss.se.musicplaylistservice.dynamodb.models.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetPantryListActivityTest {
    @Mock
    private PantryDao pantryDao;

    private GetPantryListActivity getPantryListActivity;

    @BeforeEach
    void setup() {
        openMocks(this);
        getPantryListActivity = new GetPantryListActivity(pantryDao);
    }

    @Test
    void handleRequest_userIdWithPantries_returnsListOfPantries() {
        // GIVEN
        Pantry pantry = new Pantry();
        Pantry pantry2 = new Pantry();
        String userId = "ExpectedID";
        pantry.setUserId(userId);
        pantry2.setUserId(userId);
        List<Pantry> pantryList = new ArrayList<>();
        pantryList.add(pantry);
        pantryList.add(pantry2);


        GetPantryListRequest request = GetPantryListRequest.builder()
                                              .withUserId(userId)
                                              .build();

        when(pantryDao.getUserPantries(userId)).thenReturn(pantryList);

        // WHEN
        GetPantryListResult result = getPantryListActivity.handleRequest(request);

        // THEN
        assertEquals(pantryList.get(0).getUserId(), result.getPantries().get(0).getUserId());
    }

//    @Test
//    void handleRequest_playlistExistsWithoutSongs_returnsEmptyList() {
//        // GIVEN
//        Playlist emptyPlaylist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(0);
//        String playlistId = emptyPlaylist.getId();
//        GetPantryListRequest request = GetPantryListRequest.builder()
//                                              .withId(playlistId)
//                                              .build();
//        when(pantryDao.getPlaylist(playlistId)).thenReturn(emptyPlaylist);
//
//        // WHEN
//        GetPlaylistSongsResult result = getPantryListActivity.handleRequest(request);
//
//        // THEN
//        assertTrue(result.getSongList().isEmpty(),
//                   "Expected song list to be empty but was " + result.getSongList());
//    }
//
//    @Test
//    void handleRequest_withDefaultSongOrder_returnsDefaultOrderedPlaylistSongs() {
//        // GIVEN
//        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(10);
//        String playlistId = playlist.getId();
//
//        GetPantryListRequest request = GetPantryListRequest.builder()
//                                              .withId(playlistId)
//                                              .withOrder(SongOrder.DEFAULT)
//                                              .build();
//        when(pantryDao.getPlaylist(playlistId)).thenReturn(playlist);
//
//        // WHEN
//        GetPlaylistSongsResult result = getPantryListActivity.handleRequest(request);
//
//        // THEN
//        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(playlist.getSongList(), result.getSongList());
//    }
//
//    @Test
//    void handleRequest_withReversedSongOrder_returnsReversedPlaylistSongs() {
//        // GIVEN
//        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(9);
//        String playlistId = playlist.getId();
//        List<AlbumTrack> reversedAlbumTracks = new LinkedList<>(playlist.getSongList());
//        Collections.reverse(reversedAlbumTracks);
//
//        GetPantryListRequest request = GetPantryListRequest.builder()
//                                              .withId(playlistId)
//                                              .withOrder(SongOrder.REVERSED)
//                                              .build();
//        when(pantryDao.getPlaylist(playlistId)).thenReturn(playlist);
//
//        // WHEN
//        GetPlaylistSongsResult result = getPantryListActivity.handleRequest(request);
//
//        // THEN
//        AlbumTrackTestHelper.assertAlbumTracksEqualSongModels(reversedAlbumTracks, result.getSongList());
//    }
//
//    @Test
//    void handleRequest_withShuffledSongOrder_returnsSongsInAnyOrder() {
//        Playlist playlist = PlaylistTestHelper.generatePlaylistWithNAlbumTracks(8);
//        String playlistId = playlist.getId();
//
//        List<SongModel> songModels = new ModelConverter().toSongModelList(playlist.getSongList());
//
//        GetPantryListRequest request = GetPantryListRequest.builder()
//                                              .withId(playlistId)
//                                              .withOrder(SongOrder.REVERSED)
//                                              .build();
//        when(pantryDao.getPlaylist(playlistId)).thenReturn(playlist);
//
//        // WHEN
//        GetPlaylistSongsResult result = getPantryListActivity.handleRequest(request);
//
//        // THEN
//        assertEquals(playlist.getSongList().size(),
//                     result.getSongList().size(),
//                     String.format("Expected album tracks (%s) and song models (%s) to be the same length",
//                                   playlist.getSongList(),
//                                   result.getSongList()));
//        assertTrue(
//            songModels.containsAll(result.getSongList()),
//            String.format("album list (%s) and song models (%s) are the same length, but don't contain the same " +
//                          "entries. Expected song models: %s. Returned song models: %s",
//                          playlist.getSongList(),
//                          result.getSongList(),
//                          songModels,
//                          result.getSongList()));
//    }
//
//    @Test
//    public void handleRequest_noMatchingPlaylistId_throwsPlaylistNotFoundException() {
//        // GIVEN
//        String id = "missingID";
//        GetPantryListRequest request = GetPantryListRequest.builder()
//                                              .withId(id)
//                                              .build();
//
//        // WHEN
//        when(pantryDao.getPlaylist(id)).thenThrow(new PlaylistNotFoundException());
//
//        // WHEN + THEN
//        assertThrows(PlaylistNotFoundException.class, () -> getPantryListActivity.handleRequest(request));
//    }
//
//    @Test
//    public void handleRequest_withInvalidSongOrder_throwsException() {
//        // GIVEN
//        Playlist playlist = PlaylistTestHelper.generatePlaylist();
//        String id = playlist.getId();
//        GetPantryListRequest request = GetPantryListRequest.builder()
//            .withId(id)
//            .withOrder("NOT A VALID ORDER")
//            .build();
//
//        // WHEN + THEN
//        assertThrows(InvalidAttributeValueException.class, () -> getPantryListActivity.handleRequest(request));
//    }
}
