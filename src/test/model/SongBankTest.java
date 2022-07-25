package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SongBankTest {
    SongBank sb;
    Song hello;
    Song paradise;
    Song loveStory;

    @BeforeEach
    void runBefore() {
        sb = new SongBank();
        hello = new Song("Hello", "Adele", 2015, "pop", "2010-present");
        paradise = new Song("Paradise", "Coldplay", 2009, "pop", "2000-2009");
    }

    @Test
    void testConstructor() {
        assertTrue(sb.getSongDatabase().isEmpty());
        assertTrue(sb.getMyRecTitle().isEmpty());
        assertTrue(sb.getMyRecSong().isEmpty());
    }

    @Test
    void testAddAllSongs() {
        assertEquals(0, sb.getSongDatabase().size());

        sb.addAllSongs();
        assertEquals(53, sb.getSongDatabase().size());
    }

    @Test
    void testApplyFilters() {
        assertTrue(sb.getMyRecTitle().isEmpty());
        assertTrue(sb.getMyRecSong().isEmpty());

        sb.addAllSongs();
        assertEquals(53, sb.getSongDatabase().size());

        sb.applyFilters("pop", "2010-present");
        assertEquals(6, sb.getMyRecTitle().size());
        assertEquals(6, sb.getMyRecTitle().size());
    }

    @Test
    void testFindSong() {
        sb.getSongDatabase().add(hello);
        assertEquals(hello, sb.findSong("Hello"));

        assertEquals(null, sb.findSong("Test Song"));
    }

    @Test
    void getSong() {
        assertEquals(null, sb.getSong("Test Song"));
        assertEquals(null, sb.getSong("Hello"));

        sb.getSongDatabase().add(paradise);
        assertEquals(paradise, sb.getSong("Paradise"));

        sb.addAllSongs();
        assertEquals(null, sb.getSong("Test Song"));

    }
}
