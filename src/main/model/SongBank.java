package model;

import java.util.ArrayList;

// Represents a song bank having a song database and lists of recommended songs,
// one list stores song titles and artists, other list stores song objects
public class SongBank {
    private ArrayList<Song> songDatabase;
    private ArrayList<String> myRecTitle;
    private ArrayList<Song> myRecSong;

    // EFFECTS: constructs a song bank with an empty song database and empty recommendation lists
    public SongBank() {
        songDatabase = new ArrayList<>();
        myRecTitle = new ArrayList<>();
        myRecSong = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds all songs to the song database
    public void addAllSongs() {
        addAllPopOne();
        addAllPopTwo();
        addAllRB();
        addAllRock();
        addAllCountryOne();
        addALlCountryTwo();
    }

    // MODIFIES: this
    // EFFECTS: adds all pop songs to the song database
    private void addAllPopOne() {
        // pop 2010-present
        songDatabase.add(new Song("Hello", "Adele", 2015, "pop", "2010-present"));
        songDatabase.add(new Song("I Like Me Better", "Lauv",
                2017, "pop", "2010-present"));
        songDatabase.add(new Song("Watermelon Sugar", "Harry Styles", 2020,
                "pop", "2010-present"));
        songDatabase.add(new Song("Best Day Of My Life", "American Authors",
                2013, "pop", "2010-present"));
        songDatabase.add(new Song("Riptide", "Vance Joy",2013, "pop", "2010-present"));
        songDatabase.add(new Song("Counting Stars", "OneRepublic",
                2013, "pop", "2010-present"));

        // pop 2000-2009
        songDatabase.add(new Song("Love Story", "Taylor Swift",
                2008, "pop", "2000-2009"));
        songDatabase.add(new Song("My Love", "Westlife", 2000, "pop", "2000-2009"));
        songDatabase.add(new Song("Paradise", "Coldplay", 2009, "pop", "2000-2009"));
    }

    // MODIFIES: this
    // EFFECTS: adds all pop songs to the song database
    private void addAllPopTwo() {
        // pop 1990-1999
        songDatabase.add(new Song("I Want It That Way", "Backstreet Boys",
                1999, "pop", "1990-1999"));
        songDatabase.add(new Song("Wannabe", "Spice Girls", 1996, "pop", "1990-1999"));
        songDatabase.add(new Song("...Baby One More Time", "Britney Spears",
                1998, "pop", "1990-1999"));

        // pop pre-1990
        songDatabase.add(new Song("Don't Stop Believin", "Journey",
                1981, "pop", "pre-1990"));
        songDatabase.add(new Song("Never Gonna Give You Up", "Rick Astley",
                1987, "pop", "pre-1990"));
        songDatabase.add(new Song("Your Love", "The Outfield",
                1987, "pop", "pre-1990"));
    }

    // MODIFIES: this
    // EFFECTS: adds all r&b songs to the song database
    private void addAllRB() {
        // r&b 2010-present
        songDatabase.add(new Song("Thinkin Bout You", "Frank Ocean",
                2012, "r&b", "2010-present"));
        songDatabase.add(new Song("Best Part", "Daniel Caesar",
                2017, "r&b", "2010-present"));
        songDatabase.add(new Song("Location", "Khalid", 2016, "r&b", "2010-present"));

        // r&b 2000-2009
        songDatabase.add(new Song("So Sick", "Ne-Yo", 2005, "r&b", "2000-2009"));
        songDatabase.add(new Song("Rehab", "Amy Winehouse", 2006, "r&b", "2000-2009"));
        songDatabase.add(new Song("We Belong Together", "Mariah Carey",
                2005, "r&b", "2000-2009"));

        // r&b 1990-1999
        songDatabase.add(new Song("That's the Way Love Goes", "Janet Jackson",
                1993, "r&b", "1990-1999"));
        songDatabase.add(new Song("On & On", "Erykah Badu", 1997, "r&b", "1990-1999"));
        songDatabase.add(new Song("Right Here", "SWV", 1992, "r&b", "1990-1999"));

        // r&b pre-1990
        songDatabase.add(new Song("Oh Sheila", "Ready for the World",
                1985, "r&b", "pre-1990"));
        songDatabase.add(new Song("Upside Down", "Diana Ross", 1980, "r&b", "pre-1990"));
        songDatabase.add(new Song("Casanova", "LeVert", 1987, "r&b", "pre-1990"));
    }

    // MODIFIES: this
    // EFFECTS: adds all rock songs to the song database
    private void addAllRock() {
        // rock 2010-present
        songDatabase.add(new Song("Heathens", "twenty one pilots",
                2016, "rock", "2010-present"));
        songDatabase.add(new Song("Pompeii", "Bastille", 2013, "rock", "2010-present"));
        songDatabase.add(new Song("Shut Up and Dance", "WALK THE MOON",
                2014, "rock", "2010-present"));

        // rock 2000-2009
        songDatabase.add(new Song("Kryptonite", "3 Doors Down",
                2000, "rock", "2000-2009"));
        songDatabase.add(new Song("Jaded", "Aerosmith", 2001, "rock", "2000-2009"));
        songDatabase.add(new Song("Snow (Hey Oh)", "Red Hot Chili Peppers",
                2006, "rock", "2000-2009"));

        // rock 1990-1999
        songDatabase.add(new Song("Basket Case", "Green Day", 1994, "rock", "1990-1999"));
        songDatabase.add(new Song("One", "U2", 1992, "rock", "1990-1999"));
        songDatabase.add(new Song("Wonderwall", "Oasis", 1995, "rock", "1990-1999"));

        // rock pre-1990
        songDatabase.add(new Song("Every Breath You Take", "The Police",
                1983, "rock", "pre-1990"));
        songDatabase.add(new Song("Right Here Waiting", "Richard Marx",
                1989, "rock", "pre-1990"));
        songDatabase.add(new Song("We Will Rock You", "Queen", 1977, "rock", "pre-1990"));
        songDatabase.add(new Song("Hotel California", "Eagles",
                1976, "rock", "pre-1990"));
        songDatabase.add(new Song("Wish You Were Here", "Pink Floyd",
                1976, "rock", "pre-1990"));
    }

    // MODIFIES: this
    // EFFECTS: adds all country songs to the song database
    private void addAllCountryOne() {
        // country 2010-present
        songDatabase.add(new Song("Forever After All", "Luke Combs",
                2020, "country", "2010-present"));
        songDatabase.add(new Song("Tequila", "Dan + Shay",
                2018, "country", "2010-present"));
        songDatabase.add(new Song("Follow Your Arrow", "Kacey Musgraves",
                2013, "country", "2010-present"));

        // country 2000-2009
        songDatabase.add(new Song("Our Song", "Taylor Swift",
                2007, "country", "2000-2009"));
        songDatabase.add(new Song("Watching Airplanes", "Gary Allan",
                2007, "country", "2000-2009"));
        songDatabase.add(new Song("Live Like You Were Dying", "Tim McGraw",
                2004, "country", "2000-2009"));
    }

    // MODIFIES: this
    // EFFECTS: adds all country songs to the song database
    private void addALlCountryTwo() {
        // country 1990-1999
        songDatabase.add(new Song("Friends in Low Places", "Garth Brooks",
                1990, "country", "1990-1999"));
        songDatabase.add(new Song("How Do I Live", "LeAnn Rimes",
                1997, "country", "1990-1999"));
        songDatabase.add(new Song("Meet in the Middle", "Diamond Rio",
                1991, "country", "1990-1999"));

        // country pre-1990
        songDatabase.add(new Song("9 to 5", "Dolly Parton",
                1980, "country", "pre-1990"));
        songDatabase.add(new Song("When You Say Nothing at All", "Keith Whitley",
                1988, "country", "pre-1990"));
        songDatabase.add(new Song("Seven Year Ache", "Rosanne Cash",
                1981, "country", "pre-1990"));
    }

    // REQUIRES: genre and release have a non-zero length AND genre and release are valid options
    // MODIFIES: this
    // EFFECTS: filters song database with given genre and release, adds matching songs to lists of recommendations
    public void applyFilters(String genre, String release) {
        for (Song s: songDatabase) {
            if ((genre.equals(s.getGenre())) && (release.equals(s.getReleasePeriod()))) {
                myRecTitle.add("'" + s.getTitle() + "' by " + s.getArtist());
                myRecSong.add(s);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searching for "
                + genre + " songs from " + release + "."));
    }

    // REQUIRES: title has a non-zero length AND a song in full song database has given title
    // MODIFIES: this
    // EFFECTS: searches full song database for song with given title, returns song if found, otherwise returns null
    public Song getSong(String title) {
        for (Song s: songDatabase) {
            if (s.getTitle().equals(title)) {
                return s;
            }
        }
        return null;
    }

    // EFFECTS: returns the song database
    public ArrayList<Song> getSongDatabase() {
        return songDatabase;
    }

    // EFFECTS: returns the recommendation list consisting of song title and artist
    public ArrayList<String> getMyRecTitle() {
        return myRecTitle;
    }

    // EFFECTS: returns the recommendation list consisting of song objects
    public ArrayList<Song> getMyRecSong() {
        return myRecSong;
    }
}