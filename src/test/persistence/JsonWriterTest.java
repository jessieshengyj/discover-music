package persistence;

import model.Playlist;

import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Playlist p = new Playlist();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlaylist() {
        try {
            Playlist p = new Playlist();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlaylist.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlaylist.json");
            p = reader.read();
            assertEquals(0, p.getSongList().size());
            assertEquals(0, p.getTitleList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown!");
        }
    }

    @Test
    void testWriterGeneralPlaylist() {
        try {
            Playlist p = new Playlist();
            p.addSong("Hello");
            p.addSong("Love Story");
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlaylist.json");
            writer.open();
            writer.write(p);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlaylist.json");
            p = reader.read();
            List<Song> songs = p.getSongList();
            assertEquals(2, songs.size());
            checkSong("Hello", "Adele", 2015, "pop", "2010-present", songs.get(0));
            checkSong("Love Story", "Taylor Swift", 2008, "pop", "2000-2009", songs.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown!");
        }
    }
}