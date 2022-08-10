package ui;

import model.SongBank;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Music Recommendation Generator application
// based on Teller app: link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class MusicRecApp {
    private static final String JSON_STORE = "./data/playlist.json";
    private SongBank allSongs;
    private ArrayList<Song> songRecs;
    private Playlist playlist;
    private Scanner input;
    private Boolean keepGoing;
    private String genre;
    private String release;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the music recommendation application
    public MusicRecApp() throws FileNotFoundException {
        songRecs = new ArrayList<>();
        playlist = new Playlist();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMusicRecApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMusicRecApp() {
        String command = null;
        keepGoing = true;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Thank you for using the Music Recommendation Generator!");
    }

    // REQUIRES: command has a non-zero length
    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("p")) {
            if (playlist.getSongList().isEmpty()) {
                System.out.println("Your playlist is currently empty :(");
            } else {
                System.out.println("Your Playlist:");
                for (String s: playlist.songsToNames()) {
                    System.out.println(s);
                }
                choosePlaylistOption();
            }
        } else if (command.equals("b")) {
            chooseGenre();
        } else if (command.equals("s")) {
            savePlaylist();
        } else if (command.equals("l")) {
            loadPlaylist();
        } else {
            System.out.println("Invalid input... please re-enter");
        }
    }

    // EFFECTS: saves the playlist to file
    private void savePlaylist() {
        try {
            jsonWriter.open();
            jsonWriter.write(playlist);
            jsonWriter.close();
            System.out.println("Saved your playlist to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads playlist from file
    private void loadPlaylist() {
        try {
            playlist = jsonReader.read();
            System.out.println("Loaded your playlist from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose to remove a song or quit and processes command
    private void choosePlaylistOption() {
        keepGoing = true;

        displayPlaylistOptions();

        String command = input.next();

        if (command.equals("h")) {
            System.out.println("Taking you back to the home screen...");
            init();
        } else if (command.equals("r")) {
            processRemoveSong();
        } else {
            System.out.println("Invalid input... please re-enter");
            choosePlaylistOption();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a song to remove from playlist and processes command
    private void processRemoveSong() {
        keepGoing = true;

        displayRemoveSongOptions();

        String command = input.next();

        if (playlist.songListContains((command))) {
            System.out.println("You have removed: '" + allSongs.getSong(command).getTitle()
                    + "' by " + allSongs.getSong(command).getArtist());
            System.out.println("The song is no longer in your playlist.");
            playlist.removeSong(command);
        } else {
            System.out.println("Invalid input... please re-enter");
            processRemoveSong();
        }

        init();
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a genre and processes command
    private void chooseGenre() {
        keepGoing = true;

        displayGenreChoices();

        String command = input.next();

        if (command.equals("pop") || command.equals("r&b") || command.equals("rock") || command.equals("country")) {
            this.genre = command;
            System.out.println("You chose " + command + " music!");
            chooseRelease();
        } else {
            System.out.println("Invalid input... please re-enter");
            chooseGenre();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a release period and processes command
    private void chooseRelease() {
        keepGoing = true;

        displayReleaseChoices();

        String command = input.next();

        if (command.equals("2010-present") || command.equals("2000-2009")
                || command.equals("1990-1999") || command.equals("pre-1990")) {
            this.release = command;
            System.out.println("You chose music from " + command + "!");
            System.out.println();
            System.out.println("Here are some recommended songs:");
            allSongs.applyFilters(genre, release);
            songRecs = allSongs.getMyRecSong();
            for (String s: allSongs.getMyRecTitle()) {
                System.out.println(s);
            }
            chooseAddOrQuit();
        } else {
            System.out.println("Invalid input... please re-enter");
            chooseRelease();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose to add a song or quit and processes command
    private void chooseAddOrQuit() {
        keepGoing = true;

        displayAddOrQuit();

        String command = input.next();

        if (command.equals("a")) {
            chooseSongsForPlaylist();
        } else if (command.equals("h")) {
            System.out.println("Taking you back to the home screen...");
            init();
        } else {
            System.out.println("Invalid input... please re-enter");
            chooseAddOrQuit();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a song to add to playlist and processes command
    private void chooseSongsForPlaylist() {
        keepGoing = true;

        displayAddSongChoices();

        String command = input.next();

        if (songRecs.contains(allSongs.getSong(command))) {
            System.out.println("You have added: " + allSongs.getSong(command).getTitle());
            System.out.println("The song can now be found in your playlist •ᴗ•");
            playlist.addSong(command);
        } else {
            System.out.println("Invalid input... please re-enter");
            chooseSongsForPlaylist();
        }

        init();
    }

    // MODIFIES: this
    // EFFECTS: initializes song bank
    private void init() {
        allSongs = new SongBank();
        allSongs.addAllSongs();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays home menu options to user
    private void displayMenu() {
        System.out.println();
        System.out.println("Welcome to the Music Recommendation Generator!");
        System.out.println("\tType and enter 'b' to BEGIN");
        System.out.println("\tType and enter 'p' to view current PLAYLIST");
        System.out.println("\tType and enter 's' to SAVE playlist to file");
        System.out.println("\tType and enter 'l' to LOAD playlist to file");
        System.out.println("\tType and enter 'e' to END program");
    }

    // EFFECTS: displays playlist options to user
    private void displayPlaylistOptions() {
        System.out.println(" ");
        System.out.println("Type and enter 'h' to return to home page");
        System.out.println("Type and enter 'r' to remove a song from your playlist");
    }

    // EFFECTS: displays remove song instructions to user
    private void displayRemoveSongOptions() {
        System.out.println(" ");
        System.out.println("Type and enter the song's title to remove from your playlist");
        System.out.println("(Ex. to remove 'Hello' by Adele, enter 'Hello')");
    }

    // EFFECTS: displays genre options to user
    private void displayGenreChoices() {
        System.out.println("\nInput a music genre from the following list, view playlist:");
        System.out.println("\tpop");
        System.out.println("\tr&b");
        System.out.println("\trock");
        System.out.println("\tcountry");
    }

    // EFFECTS: displays release period options to user
    private void displayReleaseChoices() {
        System.out.println("\nInput a release period from the following list:");
        System.out.println("\t2010-present");
        System.out.println("\t2000-2009");
        System.out.println("\t1990-1999");
        System.out.println("\tpre-1990");
    }

    // EFFECTS: displays add song instructions to user
    private void displayAddSongChoices() {
        System.out.println();
        System.out.println("Type and enter the song's title to add to your playlist");
        System.out.println("(Ex. to add 'Hello' by Adele, enter 'Hello')");
    }

    // EFFECTS: displays add song or quit options to user
    private void displayAddOrQuit() {
        System.out.println();
        System.out.println("Type and enter 'a' to add a song to your playlist");
        System.out.println("Type and enter 'h' to return to home page");
    }
}