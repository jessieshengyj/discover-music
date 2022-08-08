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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MusicRecAppUI extends JFrame implements ListSelectionListener {
    public static final int WIDTH_HOME = 425;
    public static final int HEIGHT_HOME = 760;
    public static final int WIDTH_OTHER = 440;
    public static final int HEIGHT_OTHER = 320;

    private JFrame frameHome;
    private JFrame frameChoose;
    private JFrame frameDatabase;

    private Playlist playlist;
    private SongBank songBank;

    private static final String JSON_STORE = "./data/playlist.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Font fontSmall;
    private Color green;

    private ImageIcon welcomeIcon;
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

    private static final String addSong = "Add Song (enter song title above)";
    private static final String removeSong = "Remove Song";
    private static final String beginProgram = "Begin Discovering";
    private static final String viewDatabase = "View Song Database";
    private static final String saveString = "Save Playlist";
    private static final String loadString = "Load Playlist";
    private static final String quitProgram = "Quit Program";
    private static final String getRecommendation = "Get Recommendation";


    public MusicRecAppUI() {
        super("Music Generator");

        initializeFieldsOne();
        initializeFieldsTwo();
        initializeFieldsThree();
        initializeGraphics();
    }

    private void initializeFieldsOne() {
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

    private void initializeFieldsTwo() {
        mainPanel = new JPanel();
        titlePanel = new JPanel();
        playlistPanel = new JScrollPane(list);
        buttonsPanel = new JPanel();

        enterSongTitle = new JTextField();

        database = new JScrollPane(list2);
        frameDatabase = new JFrame();

        // for BEGIN LISTENER
        frameChoose = new JFrame();
        recommendationPanel = new JPanel();

        songGenre = new JTextField();
        songReleasePeriod = new JTextField();

        preferenceLabel = new JLabel();

        suggestions = new JScrollPane(list3);
    }

    private void initializeFieldsThree() {
        fontSmall = new Font("Arial", Font.PLAIN, 11);
        green = new Color(113, 138, 106);

        // resize method based on: https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
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
    }

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

    private void addButtonActionListener() {
        buttonBegin.addActionListener(new BeginListener());
        buttonAddSong.addActionListener(new AddListener());
        buttonRemoveSong.addActionListener(new RemoveListener());
        buttonViewDatabase.addActionListener(new DataListener());
        buttonSave.addActionListener(new SaveListener());
        buttonLoad.addActionListener(new LoadListener());
        buttonQuit.addActionListener(new QuitListener());
    }

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

    private void initializeButtonsPanel() {
        buttonsPanel.setLayout(new GridLayout(8, 1));
        buttonsPanel.setBackground(Color.WHITE);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

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
        borderSouth.setPreferredSize(new Dimension(55, 80));
        borderWest.setPreferredSize(new Dimension(70, 70));

        addPlaylistText(borderNorth);

        playlistPanel.setBackground(Color.WHITE);

        mainPanel.add(borderNorth, BorderLayout.NORTH);
        mainPanel.add(borderEast, BorderLayout.EAST);
        mainPanel.add(borderSouth, BorderLayout.SOUTH);
        mainPanel.add(borderWest, BorderLayout.WEST);

        mainPanel.add(playlistPanel, BorderLayout.CENTER);
    }

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

    private void initializeLabels() {
        homeLabel = new JLabel("Discover Music");
        homeLabel.setHorizontalAlignment(JLabel.CENTER);
        homeLabel.setForeground(green);
        homeLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));

        titlePanel.add(homeLabel);
    }

    public void displayMsg(String message, String title) {
        JOptionPane.showMessageDialog(playlistPanel, message, title, JOptionPane.PLAIN_MESSAGE, playlistIcon);
    }

    public void warningMsg(String message, String title) {
        JOptionPane.showMessageDialog(playlistPanel, message, title, JOptionPane.PLAIN_MESSAGE, errorIcon);
    }

    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (!(list.getSelectedIndex() == -1)) {
                //Selection, enable the fire button.
                buttonBegin.setEnabled(true);
                buttonAddSong.setEnabled(true);
                buttonRemoveSong.setEnabled(true);
                buttonSave.setEnabled(true);
                buttonLoad.setEnabled(true);
                buttonQuit.setEnabled(true);
            }
        }
    }

    class BeginListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            initializeFieldsChoose();
            initializeFrameChoose();
            initializeGraphicsChoose();
            addFrameChooseObjects();
        }

        private void initializeFieldsChoose() {
            genreLabel = new JLabel("Enter a music genre:");
            releaseLabel = new JLabel("Enter a release period:");
            showResultLabel = new JLabel("Showing results for:");
            buttonRecommendation = new JButton(getRecommendation);
        }

        private void initializeFrameChoose() {
            frameChoose.setLayout(new BorderLayout());
            frameChoose.setMinimumSize(new Dimension(WIDTH_OTHER, HEIGHT_OTHER));
            frameChoose.setMaximumSize(new Dimension(WIDTH_OTHER, HEIGHT_OTHER));
            frameChoose.getContentPane().setBackground(Color.WHITE);
            frameChoose.setResizable(true);
            frameChoose.setTitle("Song Generator");
        }

        private void initializeGraphicsChoose() {
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

        private void addFrameChooseObjects() {
            recommendationPanel.add(genreLabel);
            recommendationPanel.add(releaseLabel);
            recommendationPanel.add(showResultLabel);
            recommendationPanel.add(preferenceLabel);
            recommendationPanel.add(songGenre);
            recommendationPanel.add(buttonRecommendation);
            recommendationPanel.add(songGenre);
            recommendationPanel.add(songReleasePeriod);

            frameChoose.add(recommendationPanel, BorderLayout.NORTH);
            frameChoose.add(suggestions, BorderLayout.CENTER);

            frameChoose.setVisible(true);

            buttonRecommendation.addActionListener(new RecommendationListener());
        }

        private class RecommendationListener implements ActionListener {
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
                    warningMsg2("Invalid genre or release period", "Error");
                }

                songGenre.setText("pop, r&b, rock, country");
                songReleasePeriod.setText("2010-present, 2000-2009, 1990-1999, pre-1990");
            }
        }

        private void warningMsg2(String message, String title) {
            JOptionPane.showMessageDialog(frameChoose, message, title, JOptionPane.PLAIN_MESSAGE, errorIcon);
        }
    }

    class AddListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String title = enterSongTitle.getText();

            if (title.equals("") || playlist.songListContains(title)
                    || !songBank.getSongDatabase().contains(songBank.getSong(title))) {
                Toolkit.getDefaultToolkit().beep();
                enterSongTitle.requestFocusInWindow();
                warningMsg("Invalid or Duplicate Song", "Error");
                return;
            }
            int index = listModel.size();

            Song s = songBank.getSong(title);
            listModel.add(listModel.size(), "'" + s.getTitle() + "' by " + s.getArtist());
            playlist.addSong(title);

            enterSongTitle.setText("");
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }
    }

    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

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
    }

    class DataListener implements ActionListener {
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
        // EFFECTS: loads playlist from file
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(playlist);
                jsonWriter.close();

                displayMsg("Saved playlist to " + JSON_STORE, "Save Playlist");
            } catch (FileNotFoundException fnf) {
                warningMsg("Unable to write to file: " + JSON_STORE, "Save Playlist");
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    class LoadListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads playlist from file
        public void actionPerformed(ActionEvent e) {
            try {
                playlist = jsonReader.read();
                List<String> loadedSongs = playlist.songsToNames();
                listModel.clear();
                for (String s : loadedSongs) {
                    listModel.addElement(s);
                }
                buttonRemoveSong.setEnabled(false);

                displayMsg("Loaded playlist from " + JSON_STORE, "Load Playlist");
            } catch (IOException ie) {
                warningMsg("Unable to read from file: " + JSON_STORE, "Load Playlist");
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    // listener for quit program button
    class QuitListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: loads playlist from file
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}