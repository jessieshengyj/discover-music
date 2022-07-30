package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkSong(String title, String artist, int year, String genre, String release, Song song) {
        assertEquals(title, song.getTitle());
        assertEquals(artist, song.getArtist());
        assertEquals(year, song.getReleaseYear());
        assertEquals(genre, song.getGenre());
        assertEquals(release, song.getReleasePeriod());
    }
}