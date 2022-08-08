package ui;

import model.Playlist;
import model.Song;
import model.SongBank;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Music Recommendation Generator application
public class MusicRecAppUI extends JFrame implements ListSelectionListener {
    public static final int WIDTH_HOME = 425;
    public static final int HEIGHT_HOME = 760;
    public static final int WIDTH_OTHER = 440;
    public static final int HEIGHT_OTHER = 320;

    private JFrame frameHome;
    private JFrame frameDiscover;
    private JFrame frameDatabase;

    private Playlist playlist;
    private SongBank songBank;

    private static final String JSON_STORE = "./data/playlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Font fontSmall;
    private Font fontItalic;
    private Color green;

    private ImageIcon playlistIcon;
    private ImageIcon errorIcon;

    private JPanel mainPanel;
    private JPanel titlePanel;
    private JPanel buttonsPanel;
    private JScrollPane playlistPanel;
    private JPanel recommendationPanel;
    private JScrollPane database;
    private JScrollPane suggestions;

    private JLabel homeLabel;
    private JLabel genreLabel;
    private JLabel releaseLabel;
    private JLabel preferenceLabel;
    private JLabel showResultLabel;

    private JButton buttonAddSong;
    private JButton buttonRemoveSong;
    private JButton buttonBegin;
    private JButton buttonViewDatabase;
    private JButton buttonSave;
    private JButton buttonLoad;
    private JButton buttonQuit;
    private JButton buttonRecommendation;

    private JTextField songGenre;
    private JTextField songReleasePeriod;
    private JTextField enterSongTitle;

    private JList list;
    private DefaultListModel listModel;
    private JList list2;
    private DefaultListModel listModel2;
    private JList list3;
    private DefaultListModel listModel3;

    private static final String addSong = "Add Song | ENTER";
    private static final String removeSong = "Remove Song | BACKSPACE";
    private static final String beginProgram = "Begin Discovering";
    private static final String viewDatabase = "View Song Database";
    private static final String saveString = "Save Playlist";
    private static final String loadString = "Load Playlist";
    private static final String quitProgram = "Quit Program";
    private static final String getRecommendation = "Get Recommendation";

    // EFFECTS: runs the music recommendation application
    public MusicRecAppUI() {
        super("Music Generator");

        initializeFieldsOne();
        initializeFieldsTwo();
        initializeFieldsThree();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS: initialize and sets fields for MusicRecAppUI
    private void initializeFieldsTwo() {
        frameHome = new JFrame();

        playlist = new Playlist();
        songBank = new SongBank();
        songBank.addAllSongs();
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);

        listModel2 = new DefaultListModel();
        list2 = new JList(listModel2);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list2.addListSelectionListener(this);
        list2.setVisibleRowCount(5);

        listModel3 = new DefaultListModel();
        list3 = new JList(listModel3);
        list3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list3.addListSelectionListener(this);
        list3.setVisibleRowCount(5);
    }

    // MODIFIES: this
    // EFFECTS: initialize and sets fields for MusicRecAppUI
    private void initializeFieldsThree() {
        mainPanel = new JPanel();
        titlePanel = new JPanel();
        playlistPanel = new JScrollPane(list);
        buttonsPanel = new JPanel();

        enterSongTitle = new JTextField();
        enterSongTitle.setText("enter title here");
        enterSongTitle.setFont(fontItalic);
        enterSongTitle.setHorizontalAlignment(SwingConstants.CENTER);

        database = new JScrollPane(list2);
        frameDatabase = new JFrame();

        // for BEGIN LISTENER
        frameDiscover = new JFrame();
        recommendationPanel = new JPanel();

        songGenre = new JTextField();
        songReleasePeriod = new JTextField();

        preferenceLabel = new JLabel();

        suggestions = new JScrollPane(list3);
    }

    // images from this method are from:
    // https://icons8.com/icon/102000/error-cloud
    // https://icons8.com/icon/24520/playlist
    // MODIFIES: this
    // EFFECTS: initializes and sets fields for MusicRecAppUI
    private void initializeFieldsOne() {
        fontSmall = new Font("Arial", Font.PLAIN, 11);
        fontItalic = new Font("Arial", Font.ITALIC, 13);
        green = new Color(113, 138, 106);

        // image icon resize method based on:
        // https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
        playlistIcon = new ImageIcon("./img/playlist.png");
        Image playlistImg = playlistIcon.getImage();
        Image playlistScaledImg = playlistImg.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        playlistIcon = new ImageIcon(playlistScaledImg);

        errorIcon = new ImageIcon("./img/error.png");
        Image errorImg = errorIcon.getImage();
        Image errorScaledImg = errorImg.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        errorIcon = new ImageIcon(errorScaledImg);

        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
    }

    // MODIFIES: this
    // EFFECTS: draws the frame for MusicRecAppUI
    private void initializeGraphics() {
        setTitle("Music Generator");
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH_HOME, HEIGHT_HOME));
        getContentPane().setBackground(Color.WHITE);
        setResizable(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeButtons();
        initializePanels();
        initializeLabels();

        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: initializes buttons for buttonsPanel with text
    private void initializeButtons() {
        buttonAddSong = new JButton(addSong);
        buttonRemoveSong = new JButton(removeSong);
        buttonRemoveSong.setEnabled(false);
        buttonBegin = new JButton(beginProgram);
        buttonViewDatabase = new JButton(viewDatabase);
        buttonSave = new JButton(saveString);
        buttonLoad = new JButton(loadString);
        buttonQuit = new JButton(quitProgram);

        addButtons();
        addButtonActionListener();

        addKeyListenerAddFunction();
        addKeyListenerRemoveFunction();
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to buttonsPanel
    private void addButtons() {
        buttonsPanel.add(buttonBegin);
        buttonsPanel.add(enterSongTitle);
        buttonsPanel.add(buttonAddSong);
        buttonsPanel.add(buttonRemoveSong);
        buttonsPanel.add(buttonViewDatabase);
        buttonsPanel.add(buttonSave);
        buttonsPanel.add(buttonLoad);
        buttonsPanel.add(buttonQuit);

        frameHome.add(buttonsPanel);
    }

    // MODIFIES: this
    // EFFECTS: adds respective action listener to each button
    private void addButtonActionListener() {
        buttonBegin.addActionListener(new BeginListener());
        buttonAddSong.addActionListener(new AddListener());
        buttonRemoveSong.addActionListener(new RemoveListener());
        buttonViewDatabase.addActionListener(new DataListener());
        buttonSave.addActionListener(new SaveListener());
        buttonLoad.addActionListener(new LoadListener());
        buttonQuit.addActionListener(new QuitListener());
    }

    // listener for add song function
    private void addKeyListenerAddFunction() {
        enterSongTitle.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            // MODIFIES: this
            // EFFECTS: adds song to playlist if enter key is pressed
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    actionAddSong();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    // listener for remove song function
    private void addKeyListenerRemoveFunction() {
        list.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            // MODIFIES: this
            // EFFECTS: removes song from playlist if backspace key is pressed and playlist is not empty
            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_BACK_SPACE) && (listModel.getSize() != 0)) {
                    actionRemoveSong();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes panels for application's home page
    private void initializePanels() {
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setPreferredSize(new Dimension(WIDTH_HOME, 80));

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        add(titlePanel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

        initializeButtonsPanel();
        initializeMainPanel();
    }

    // MODIFIES: this
    // EFFECTS: initializes the buttons panel
    private void initializeButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(8, 1));
        buttonsPanel.setBackground(Color.WHITE);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: initializes the main panel for displaying playlist with borders
    private void initializeMainPanel() {
        JPanel borderNorth = new JPanel();
        JPanel borderEast = new JPanel();
        JPanel borderSouth = new JPanel();
        JPanel borderWest = new JPanel();

        borderNorth.setBackground(green);
        borderEast.setBackground(green);
        borderSouth.setBackground(green);
        borderWest.setBackground(green);
        borderNorth.setPreferredSize(new Dimension(55, 80));
        borderEast.setPreferredSize(new Dimension(70, 70));
        borderSouth.setPreferredSize(new Dimension(55, 70));
        borderWest.setPreferredSize(new Dimension(70, 70));

        addPlaylistText(borderNorth);

        playlistPanel.setBackground(Color.WHITE);

        mainPanel.add(borderNorth, BorderLayout.NORTH);
        mainPanel.add(borderEast, BorderLayout.EAST);
        mainPanel.add(borderSouth, BorderLayout.SOUTH);
        mainPanel.add(borderWest, BorderLayout.WEST);

        mainPanel.add(playlistPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds "Your Playlist" text to the main panel
    private void addPlaylistText(JPanel borderNorth) {
        borderNorth.setLayout(new BorderLayout());
        JPanel playlistTextPanel = new JPanel();
        JLabel yourPlaylist = new JLabel("Your Playlist:");

        playlistTextPanel.setBackground(green);
        playlistTextPanel.setPreferredSize(new Dimension(55, 40));

        yourPlaylist.setForeground(Color.WHITE);
        yourPlaylist.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));

        borderNorth.add(playlistTextPanel, BorderLayout.SOUTH);
        playlistTextPanel.add(yourPlaylist);
    }

    // MODIFIES: this
    // EFFECTS: initializes main label for application's home page
    private void initializeLabels() {
        homeLabel = new JLabel("Discover Music");
        homeLabel.setHorizontalAlignment(JLabel.CENTER);
        homeLabel.setForeground(green);
        homeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));

        titlePanel.add(homeLabel);
    }

    // MODIFIES: this
    // EFFECTS: displays given message with title and icon
    public void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(playlistPanel, message, title, JOptionPane.PLAIN_MESSAGE, playlistIcon);
    }

    // MODIFIES: this
    // EFFECTS: displays given warning message with title and icon
    public void warningMessage(String message, String title) {
        JOptionPane.showMessageDialog(playlistPanel, message, title, JOptionPane.PLAIN_MESSAGE, errorIcon);
    }

    // this method is required by ListSelectionListener
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (!(list.getSelectedIndex() == -1)) {
                // Selection, enable buttons
                buttonBegin.setEnabled(true);
                buttonAddSong.setEnabled(true);
                buttonRemoveSong.setEnabled(true);
                buttonSave.setEnabled(true);
                buttonLoad.setEnabled(true);
                buttonQuit.setEnabled(true);
            }
        }
    }

    // listener for begin discovering button
    class BeginListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: initializes
        public void actionPerformed(ActionEvent e) {
            initializeFieldsDiscover();
            initializeFrameDiscover();
            setGraphicsDiscover();
            addFrameDiscoverObjects();
        }

        // MODIFIES: this
        // EFFECTS: initializes fields for begin discovering frame
        private void initializeFieldsDiscover() {
            genreLabel = new JLabel("Enter a music genre:");
            releaseLabel = new JLabel("Enter a release period:");
            showResultLabel = new JLabel("Showing results for:");
            buttonRecommendation = new JButton(getRecommendation);
        }

        // MODIFIES: this
        // EFFECTS: draws the window for begin discovering frame
        private void initializeFrameDiscover() {
            frameDiscover.setLayout(new BorderLayout());
            frameDiscover.setMinimumSize(new Dimension(WIDTH_OTHER, HEIGHT_OTHER));
            frameDiscover.setMaximumSize(new Dimension(WIDTH_OTHER, HEIGHT_OTHER));
            frameDiscover.getContentPane().setBackground(Color.WHITE);
            frameDiscover.setResizable(true);
            frameDiscover.setTitle("Song Generator");
        }

        // MODIFIES: this
        // EFFECTS: sets the panels, labels, text fields, and buttons for begin discovering frama
        private void setGraphicsDiscover() {
            recommendationPanel.setLayout(null);
            recommendationPanel.setBackground(green);
            recommendationPanel.setPreferredSize(new Dimension(WIDTH_OTHER, 160));

            genreLabel.setForeground(Color.WHITE);
            genreLabel.setFont(new Font("Arial", Font.BOLD, 14));
            genreLabel.setBounds(10, 20, 155, 25);

            releaseLabel.setForeground(Color.WHITE);
            releaseLabel.setFont(new Font("Arial", Font.BOLD, 14));
            releaseLabel.setBounds(10, 55, 155, 25);

            showResultLabel.setForeground(Color.WHITE);
            showResultLabel.setFont(new Font("Arial", Font.PLAIN, 13));
            showResultLabel.setBounds(10, 130, 155, 25);

            preferenceLabel.setForeground(Color.WHITE);
            preferenceLabel.setFont(new Font("Arial", Font.ITALIC, 13));
            preferenceLabel.setBounds(135, 130, 200, 25);

            songGenre.setText("pop, r&b, rock, country");
            songGenre.setBounds(170, 20, 255, 28);
            songGenre.setFont(fontSmall);
            songReleasePeriod.setText("2010-present, 2000-2009, 1990-1999, pre-1990");
            songReleasePeriod.setFont(fontSmall);
            songReleasePeriod.setBounds(170, 55, 255, 28);
            buttonRecommendation.setBounds(150, 95, 160, 28);
        }

        // MODIFIES: this
        // EFFECTS: adds panels, labels, text fields, and buttons to begin discovering frame
        private void addFrameDiscoverObjects() {
            recommendationPanel.add(genreLabel);
            recommendationPanel.add(releaseLabel);
            recommendationPanel.add(showResultLabel);
            recommendationPanel.add(preferenceLabel);
            recommendationPanel.add(songGenre);
            recommendationPanel.add(buttonRecommendation);
            recommendationPanel.add(songGenre);
            recommendationPanel.add(songReleasePeriod);

            frameDiscover.add(recommendationPanel, BorderLayout.NORTH);
            frameDiscover.add(suggestions, BorderLayout.CENTER);

            frameDiscover.setVisible(true);

            buttonRecommendation.addActionListener(new RecommendationListener());
        }

        // listener for get recommendation button
        private class RecommendationListener implements ActionListener {

            // MODIFIES: this
            // EFFECTS: filters song database and displays songs that satisfy user inputs
            //          displays warning message if inputs are invalid
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> allGenres = new ArrayList<>();
                Collections.addAll(allGenres, "pop", "r&b", "rock", "country");
                ArrayList<String> allRelease = new ArrayList<>();
                Collections.addAll(allRelease, "2010-present", "2000-2009", "1990-1999", "pre-1990");

                SongBank filteredList = new SongBank();
                filteredList.addAllSongs();
                String genre = songGenre.getText();
                String release = songReleasePeriod.getText();

                if (allGenres.contains(genre) && allRelease.contains(release)) {
                    listModel3.clear();
                    filteredList.applyFilters(genre, release);
                    for (String s : filteredList.getMyRecTitle()) {
                        listModel3.addElement(s);
                    }
                    preferenceLabel.setText(genre + " songs from " + release);
                } else {
                    Toolkit.getDefaultToolkit().beep();
                    warningMessageDiscover("Invalid Genre or Release Period", "Error");
                }

                songGenre.setText("pop, r&b, rock, country");
                songReleasePeriod.setText("2010-present, 2000-2009, 1990-1999, pre-1990");
            }
        }

        // MODIFIES: this
        // EFFECTS: displays given warning message with title and icon
        private void warningMessageDiscover(String message, String title) {
            JOptionPane.showMessageDialog(frameDiscover, message, title, JOptionPane.PLAIN_MESSAGE, errorIcon);
        }
    }

    // listener for add song button
    class AddListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: adds the song with title that matches user input
        //          displays warning message if inputs are invalid
        public void actionPerformed(ActionEvent e) {
            actionAddSong();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds the song with title that matches user input
    //          displays warning message if inputs are invalid
    private void actionAddSong() {
        String title = enterSongTitle.getText();

        if (title.equals("") || playlist.songListContains(title)
                || !songBank.getSongDatabase().contains(songBank.getSong(title))) {
            Toolkit.getDefaultToolkit().beep();
            enterSongTitle.requestFocusInWindow();
            warningMessage("Invalid or Duplicate Song", "Error");
            return;
        }
        int index = listModel.size();

        Song s = songBank.getSong(title);
        listModel.add(listModel.size(), "'" + s.getTitle() + "' by " + s.getArtist());
        playlist.addSong(title);

        enterSongTitle.setText("enter title here");
        enterSongTitle.setFont(fontItalic);
        list.setSelectedIndex(index);
        list.ensureIndexIsVisible(index);
    }

    // listener for remove song button
    class RemoveListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: removes the song at the user's selected index
        public void actionPerformed(ActionEvent e) {
            actionRemoveSong();
        }
    }

    // MODIFIES: this
    // EFFECTS: removes the song at the user's selected index
    private void actionRemoveSong() {
        int index = list.getSelectedIndex();
        listModel.remove(index);
        playlist.getSongList().remove(index);

        if (listModel.getSize() == 0) {
            buttonRemoveSong.setEnabled(false);
        } else {
            if (index == listModel.getSize()) {
                index--;
            }
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    // listener for view database button
    class DataListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: displays the full song database
        public void actionPerformed(ActionEvent e) {
            frameDatabase.setLayout(new BorderLayout());
            frameDatabase.setMinimumSize(new Dimension(WIDTH_OTHER - 120, HEIGHT_OTHER));
            frameDatabase.getContentPane().setBackground(Color.WHITE);
            frameDatabase.setResizable(true);
            frameDatabase.setTitle("Song Database");

            for (Song s : songBank.getSongDatabase()) {
                listModel2.addElement("'" + s.getTitle() + "' by " + s.getArtist());
            }

            frameDatabase.add(database);
            frameDatabase.setVisible(true);
        }
    }

    // listener for save playlist button
    class SaveListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: saves the playlist to file
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(playlist);
                jsonWriter.close();

                displayMessage("Saved playlist to " + JSON_STORE, "Save Playlist");
            } catch (FileNotFoundException fnf) {
                warningMessage("Unable to write to file: " + JSON_STORE, "Save Playlist");
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    // listener for load playlist button
    class LoadListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads the playlist from file
        public void actionPerformed(ActionEvent e) {
            try {
                playlist = jsonReader.read();
                List<String> loadedSongs = playlist.songsToNames();
                listModel.clear();
                for (String s : loadedSongs) {
                    listModel.addElement(s);
                }
                buttonRemoveSong.setEnabled(false);

                displayMessage("Loaded playlist from " + JSON_STORE, "Load Playlist");
            } catch (IOException ie) {
                warningMessage("Unable to read from file: " + JSON_STORE, "Load Playlist");
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    // listener for quit program button
    class QuitListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: ends the program
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}