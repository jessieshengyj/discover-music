package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SongTest {
    Song song;

    @BeforeEach
    void runBefore() {
        song = new Song("title", "artist", 2022, "genre", "release");
    }

    @Test
    void testConstructor() {
        assertEquals("title", song.getTitle());
        assertEquals("artist", song.getArtist());
        assertEquals(2022, song.getReleaseYear());
        assertEquals("genre", song.getGenre());
        assertEquals("release", song.getReleasePeriod());
    }
}
