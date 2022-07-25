package tests;

import model.Playlist;
import model.Song;
import model.SongBank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {
    Playlist playlist;
    SongBank allSongs;

    @BeforeEach
    void runBefore() {
        playlist = new Playlist();
        allSongs = new SongBank();
    }

    @Test
    void testConstructor() {
        assertTrue(playlist.getSongList().isEmpty());
        assertTrue(playlist.getTitleList().isEmpty());
    }

    @Test
    void testAddSong() {
        allSongs.addAllSongs();

        assertTrue(playlist.addSong("Hello"));
        assertEquals(1, playlist.getSongList().size());

        assertTrue(playlist.addSong("Paradise"));
        assertEquals(2, playlist.getSongList().size());

        assertFalse(playlist.addSong("Hello"));
        assertEquals(2, playlist.getSongList().size());
    }

    @Test
    void testSongListContains() {
        assertFalse(playlist.songListContains("Hello"));

        allSongs.addAllSongs();
        assertTrue(playlist.addSong("Hello"));
        assertTrue(playlist.addSong("Paradise"));
        assertTrue(playlist.addSong("Love Story"));

        assertTrue(playlist.songListContains("Hello"));
        assertTrue(playlist.songListContains("Paradise"));
        assertTrue(playlist.songListContains("Love Story"));

        assertFalse(playlist.songListContains("Test Song"));

    }

    @Test
    void testSongsToNames() {
        allSongs.addAllSongs();
        assertTrue(playlist.addSong("Hello"));
        assertTrue(playlist.addSong("Paradise"));
        assertTrue(playlist.addSong("Love Story"));
        playlist.songsToNames();

        assertEquals(3, playlist.getTitleList().size());
        assertTrue(playlist.getTitleList().contains("'Hello' by Adele"));
        assertTrue(playlist.getTitleList().contains("'Paradise' by Coldplay"));
        assertTrue(playlist.getTitleList().contains("'Love Story' by Taylor Swift"));
    }
}
