package composite;

import java.util.ArrayList;

public class Program {

	public static void main(String args[]) {

		// Make new empty "Study" playlist
		Playlist studyPlaylist = new Playlist("Study");

		// Make "Synth Pop" playlist and add 2 songs to it.
		Playlist synthPopPlaylist = new Playlist("Synth Pop");
		Song synthPopSong1 = new Song("Girl Like You", "Toro Y Moi");
		Song synthPopSong2 = new Song("Outside", "TOPS");
		synthPopPlaylist.add(synthPopSong1);
		synthPopPlaylist.add(synthPopSong2);

		// Make "Experimental" playlist and add 3 songs to it,
		// then set playback speed of the playlist to 0.5x
		Playlist experimentalPlaylist = new Playlist("Experimental");
		Song experimentalSong1 = new Song("About you", "XXYYXX");
		Song experimentalSong2 = new Song("Motivation", "Clams Casino");
		Song experimentalSong3 = new Song("Computer Vision", "Oneohtrix Point Never");
		experimentalPlaylist.add(experimentalSong1);
		experimentalPlaylist.add(experimentalSong2);
		experimentalPlaylist.add(experimentalSong3);
		float slowSpeed = 0.5f;
		experimentalPlaylist.setPlaybackSpeed(slowSpeed);

		// Add the "Synth Pop" playlist to the "Experimental" playlist
		experimentalPlaylist.add(synthPopPlaylist);

		// Add the "Experimental" playlist to the "Study" playlist
		studyPlaylist.add(experimentalPlaylist);

		// Create a new song and set its playback speed to 1.25x, play this song,
		// get the name of glitchSong → "Textuell", then get the artist of this song →
		// "Oval"
		Song glitchSong = new Song("Textuell", "Oval");
		float fasterSpeed = 1.25f;
		glitchSong.setPlaybackSpeed(fasterSpeed);
		glitchSong.play();
		String name = glitchSong.getName();
		String artist = glitchSong.getArtist();
		System.out.println("The song name is " + name);
		System.out.println("The song artist is " + artist);

		// Add glitchSong to the "Study" playlist
		studyPlaylist.add(glitchSong);

		// Play "Study" playlist.
		System.out.println("==> Playlist " + studyPlaylist.getName() + " <==");
		System.out.println("  List of songs:");
		studyPlaylist.play();

		// Get the playlist name of studyPlaylist → "Study"
		System.out.println("The Playlist's name is " + studyPlaylist.getName());
	}
}

interface IComponent {

	void play();

	void setPlaybackSpeed(float speed);

	String getName();

}

class Playlist implements IComponent {

	private String playlistName;
	public ArrayList<IComponent> playlist = new ArrayList<>();

	public Playlist(String playlistName) {
		this.playlistName = playlistName;
	}

	public void add(Playlist playlist) {
		this.playlist.add(playlist);
	}

	public void add(Song song) {
		playlist.add(song);
	}

	@Override
	public void play() {
		for (IComponent component : playlist) {
			if (component.getClass().isAssignableFrom(Playlist.class)) {
				Playlist playlistComponent = (Playlist) component;
				playlistComponent.play();
			} else {
				Song song = (Song) component;
				System.out.println("  " + song.getName() + " by " + song.getArtist());
			}
		}
	}

	@Override
	public void setPlaybackSpeed(float speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getName() {
		return playlistName;
	}

}

class Song implements IComponent {
	private String name;
	private String artist;
	public float speed = 1; // Default playback speed

	public Song(String name, String artist) {
		this.name = name;
		this.artist = artist;
	}

	public String getArtist() {
		return this.artist;
	}

	@Override
	public void play() {
		System.out.println("Playing " + name);

	}

	@Override
	public void setPlaybackSpeed(float speed) {
		this.speed = speed;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
