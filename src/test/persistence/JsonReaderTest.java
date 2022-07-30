package persistence;

import model.Playlist;
import model.Song;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Playlist p = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlaylist.json");
        try {
            Playlist p = reader.read();
            assertEquals(0, p.getSongList().size());
            assertEquals(0, p.getTitleList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPlaylist() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlaylist.json");
        try {
            Playlist p = reader.read();
            List<Song> songs = p.getSongList();
            assertEquals(4, songs.size());
            checkSong("Hello", "Adele", 2015, "pop", "2010-present", songs.get(0));
            checkSong("Jaded", "Aerosmith", 2001, "rock", "2000-2009", songs.get(1));
            checkSong("Counting Stars", "OneRepublic", 2013,
                    "pop", "2010-present", songs.get(2));
            checkSong("Paradise", "Coldplay", 2009, "pop", "2000-2009", songs.get(3));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}