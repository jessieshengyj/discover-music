package model;

import java.util.ArrayList;
import java.util.Collection;

// Represents a songList having lists of songs added (as song objects or string) and a song bank
public class Playlist {
    private ArrayList<Song> songList;
    private ArrayList<String> titleList;
    private SongBank allSongs;

    // EFFECTS: constructs a playlist with empty song list and empty song bank
    public Playlist() {
        songList = new ArrayList<>();
        titleList = new ArrayList<>();
        allSongs = new SongBank();
    }

    // REQUIRES: title has a non-zero length AND a song in full database of SongBank has given title
    // MODIFIES: this
    // EFFECTS: if song list contains song with given title, return false,
    //          otherwise add the song to song list and return true
    public boolean addSong(String title) {
        if (songListContains(title)) {
            System.out.println("(Note: the song was added previously!)");
            return false;
        } else {
            songList.add(allSongs.findSong(title));
            return true;
        }
    }

    // REQUIRES: title has a non-zero length AND a song in full database of SongBank has given title
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

    // EFFECTS: returns song list
    public ArrayList<Song> getSongList() {
        return songList;
    }

    // EFFECTS: returns the list of song titles and artists
    public ArrayList<String> getTitleList() {
        return titleList;
    }
}