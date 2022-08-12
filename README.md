# Music Generator

### My CPSC 210 Personal Project

*Hello and welcome* to my application!

This application is a **music recommendation generator**, which will allow the user to **discover new songs** for 
their playlists. First, the application will prompt the user to answer a series of questions regarding their 
music preference, or the type of music they hope to find. Based on their response, the program will filter 
and generate a list of songs that match the given description. Then, the user will have the option to continue 
choosing a song from these recommendations, or restart the program. To keep track of the user’s chosen songs, 
the application will allow the user to add the selected song to a playlist which is accessible from the home page.

This application is directed for individuals who hope to find more songs that match their music taste, or 
discover new songs from certain genres and time periods. For many people, music is an important and inseparable part
of their everyday life. As someone who also enjoys listening to music on a daily basis, I believe an application 
similar to this could help people like me **broaden our music selection**. Therefore, creating such a program would 
be an interesting process for me •◡•

***User Stories:***
- As a user, I want to be able to input my music preferences and receive recommendations
- As a user, I want to be able to select a song from the generated list and add it to my playlist
- As a user, I want to be able to view my updated playlist
- As a user, I want to be able to view the complete song database
- As a user, I want to be able to return to the home page and restart program
- As a user, I want to be able to end the program from the home page
- As a user, I want to be able to save my playlist to file from the home page
- As a user, I want to be able to load my playlist from file when I start the application

### Instructions for Grader

- You can generate the first required event by ***clicking*** the **Add Song button** to add the song corresponding 
to the user input above, and ***clicking*** the **Remove Song button** to remove the selected song from the playlist
- You can generate the second required event by ***pressing*** the **Enter key** to add inputted songs, 
and ***pressing*** the **Backspace key** to remove selected songs
- You can generate other events by pressing the **Begin Discovering button** to input preferences to receive 
recommendations, and pressing the **View Database button** to view the full song database
- You can locate my visual component in the **alert messages**: error and success
- You can save the state of my application by clicking the **Save Playlist** button
- You can reload the state of my application by clicking the **Load Playlist** button

### Phase 4: Task 2

Tue Aug 09 21:46:14 PDT 2022 <br />
Song added to your playlist: 'Hello' by Adele

Tue Aug 09 21:46:19 PDT 2022 <br />
Song added to your playlist: 'Paradise' by Coldplay

Tue Aug 09 21:46:26 PDT 2022 <br />
Song added to your playlist: 'Riptide' by Vance Joy

Tue Aug 09 21:46:29 PDT 2022 <br />
Song removed from your playlist: 'Paradise' by Coldplay

Tue Aug 09 21:46:31 PDT 2022 <br />
Song removed from your playlist: 'Riptide' by Vance Joy

Tue Aug 09 21:46:43 PDT 2022 <br />
Song added to your playlist: 'Best Part' by Daniel Caesar

Tue Aug 09 21:46:45 PDT 2022 <br />
Song removed from your playlist: 'Hello' by Adele

### Phase 4: Task 3

Future changes to improve the cohesion, coupling, readability and overall structure of my code:
- Remove the myRecSong field in SongBank, since the recommendation list could be obtained by filtering 
the original songDatabase list - this would remove one of the two associations between SongBank and Song
- Increase robustness of my program by creating and handling exceptions (for invalid song genre, title, etc.), 
rather than using if-else clauses in the MusicRecApp and MusicRecAppUI classes
- Further refactor the UI class by extracting methods to capture behaviour
