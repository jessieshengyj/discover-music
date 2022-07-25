package model;

// Represents a song having a title, artist, year of release, genre, and release period
public class Song {
    private String title;
    private String artist;
    private int releaseYear;
    private String genre;
    private String releasePeriod;

    // REQUIRES: title, artist, genre, and release have a non-zero length AND year >= 0
    // EFFECTS: constructs a song with given title, artist, year (of release), genre, and release (period)
    public Song(String title, String artist, int year, String genre, String release) {
        this.title = title;
        this.artist = artist;
        this.releaseYear = year;
        this.genre = genre;
        this.releasePeriod = release;
    }

    // EFFECTS: returns title of the song
    public String getTitle() {
        return title;
    }

    // EFFECTS: returns artist of the song
    public String getArtist() {
        return artist;
    }

    // EFFECTS: returns year of release of the song
    public int getReleaseYear() {
        return releaseYear;
    }

    // EFFECTS: returns genre of the song
    public String getGenre() {
        return genre;
    }

    // EFFECTS: returns release period of the song
    public String getReleasePeriod() {
        return releasePeriod;
    }
}