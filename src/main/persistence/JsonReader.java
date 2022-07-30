package persistence;

import model.Playlist;
import model.Song;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads playlist from JSON data stored in file
// based on JsonSerializationDemo: link below
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads playlist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Playlist read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlaylist(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playlist from JSON object and returns it
    private Playlist parsePlaylist(JSONObject jsonObject) {
        Playlist p = new Playlist();
        addSongs(p, jsonObject);
        return p;
    }

    // MODIFIES: p
    // EFFECTS: parses songs from JSON object and adds them to playlist
    private void addSongs(Playlist p, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("songs");
        for (Object json : jsonArray) {
            JSONObject nextSong = (JSONObject) json;
            addSong(p, nextSong);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses song from JSON object and adds it to playlist
    private void addSong(Playlist p, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String artist = jsonObject.getString("artist");
        Integer year = jsonObject.getInt("year");
        String genre = jsonObject.getString("genre");
        String release = jsonObject.getString("release");
        Song song = new Song(title, artist, year, genre, release);
        p.addSong(song.getTitle());
    }
}