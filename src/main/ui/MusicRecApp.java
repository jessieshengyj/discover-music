package ui;

import model.SongBank;
import model.Playlist;
import model.Song;

import java.util.ArrayList;
import java.util.Scanner;

// Music Recommendation Generator application
// based on Teller app: link below
// https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
public class MusicRecApp {
    private SongBank allSongs;
    private ArrayList<Song> songRecs;
    private Playlist playlist;
    private Scanner input;
    private Boolean keepGoing;
    private String genre;
    private String release;

    // EFFECTS: runs the music recommendation application
    public MusicRecApp() {
        playlist = new Playlist();
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
                System.out.println("Your playlist is currently empty:");
            }
            System.out.println(playlist.songsToNames());
        } else if (command.equals("s")) {
            chooseGenre();
        } else {
            System.out.println("Invalid input... please re-enter :(");
            String s = input.next();
            processCommand(s);
        }
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
            System.out.println("Invalid input... please re-enter :(");
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
            System.out.println("Here are some recommended songs:");
            allSongs.applyFilters(genre, release);
            songRecs = allSongs.getMyRecSong();
            System.out.println(allSongs.getMyRecTitle());
            chooseAddOrQuit();
        } else {
            System.out.println("Invalid input... please re-enter :(");
            chooseRelease();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to choose to add a song or quit and processes command
    private void chooseAddOrQuit() {
        keepGoing = true;

        displayAddOrQuit();

        String command = input.next();

        if (command.equals("add")) {
            chooseSongsForPlaylist();
        } else if (command.equals("home")) {
            System.out.println("Taking you back to the home screen...");
            init();
        } else {
            System.out.println("Invalid input... please re-enter :(");
            chooseAddOrQuit();
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to select a song to add to playlist and processes command
    private void chooseSongsForPlaylist() {
        displayAddSongChoices();

        String command = input.next();

        if (songRecs.contains(allSongs.getSong(command))) {
            System.out.println("You have added: " + allSongs.getSong(command).getTitle());
            System.out.println("The song can now be found in your playlist •ᴗ•");
            playlist.addSong(command);
        } else {
            System.out.println("Invalid input... please re-enter :(");
            chooseSongsForPlaylist();
        }

        init(); // to re-initialize recommendation list and make it empty
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
        System.out.println("\tType and enter 's' to START");
        System.out.println("\tType and enter 'p' to view current PLAYLIST");
        System.out.println("\tType and enter 'e' to END program");
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
        System.out.println("Type and enter 'add' to add a song to your playlist");
        System.out.println("Type and enter 'home' to return to home page");
    }
}