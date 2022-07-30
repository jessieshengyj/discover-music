package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a playlist having lists of songs added (as song objects or string) and a song bank
public class Playlist implements Writable {
    private ArrayList<Song> songList;
    private ArrayList<String> titleList;
    private SongBank allSongs;

    // EFFECTS: constructs a playlist with empty lists of songs added and empty song bank
    public Playlist() {
        songList = new ArrayList<>();
        titleList = new ArrayList<>();
        allSongs = new SongBank();
    }

    // REQUIRES: title has a non-zero length AND a song in full song bank has given title
    // MODIFIES: this
    // EFFECTS: if current list of songs added contains song with given title, return false,
    //          otherwise add the song to song list and return true
    public boolean addSong(String title) {
        if (songListContains(title)) {
            System.out.println("(Note: the song was added previously!)");
            return false;
        } else {
            allSongs.addAllSongs();
            songList.add(allSongs.getSong(title));
            return true;
        }
    }

    public boolean removeSong(String title) {
        if (songListContains(title)) {
            allSongs.addAllSongs();
            songList.remove(allSongs.getSong(title));
            titleList.remove("'" + allSongs.getSong(title).getTitle() + "' by "
                    + allSongs.getSong(title).getArtist());
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: title has a non-zero length AND a song in full song bank has given title
    // EFFECTS: checks song list for song with given title, returns true if found, otherwise returns false
    public boolean songListContains(String title) {
        for (Song song: songList) {
            if (title.equals(song.getTitle())) {
                return true;
            }
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: converts every new song in song list to string stating song and artist
    public ArrayList<String> songsToNames() {
        for (Song s : songList) {
            if (!(titleList.contains("'" + s.getTitle() + "' by " + s.getArtist()))) {
                titleList.add("'" + s.getTitle() + "' by " + s.getArtist());
            }
        }
        return titleList;
    }

    // EFFECTS: returns the list of songs added as Song objects
    public ArrayList<Song> getSongList() {
        return songList;
    }

    // EFFECTS: returns the list of songs added as "'title' by artist" strings
    public ArrayList<String> getTitleList() {
        return titleList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("songs", songsToJson());

        return json;
    }

    public JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songList) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }
}