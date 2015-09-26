package org.gs.stream;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Singleton;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

@Singleton
public class MosaicMediaStreamer {
	private static String VERBOSE = "0";
	private Map<PlayerInstance, HeadlessMediaPlayer> players = new HashMap<>();
	
	public MosaicMediaStreamer() {
	}
	
	public static String getConfFileName(PlayerInstance type) {
		return "mosaic." + type.name().toLowerCase() + ".conf";
	}
	
	private HeadlessMediaPlayer initPlayer(PlayerInstance type) {
		HeadlessMediaPlayer currentPlayerOfType = this.players.get(type);
		if (currentPlayerOfType != null) {
			try {
				stopAndRelease(currentPlayerOfType);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String configFile = "--vlm-conf=" + getConfFileName(type);
		MediaPlayerFactory mFactory = new MediaPlayerFactory(configFile, "--verbose=" + VERBOSE, "--mosaic-width=1280", "--mosaic-height=360",
			"--mosaic-rows=1", "--mosaic-cols=2", "--mosaic-position=1", "--mosaic-order=1,2", "--sout-keep");
			
		HeadlessMediaPlayer newPlayerOfType = mFactory.newHeadlessMediaPlayer();
		this.players.put(type, newPlayerOfType);
		
		return newPlayerOfType;
		
		//		String mrl;
		//		if (args.length > 0) {
		//			mrl = args[0];
		//		}
		//		else {
		//mrl = "rtsp://viewer:Viewer123@192.168.10.64:554/live";
		//		mrl = "";
		//		}
		//		String opts = ":sout=#transcode{vcodec=theo,vb=1024,channels=1,ab=128,samplerate=44100}";
		//		opts += ":http{dst=:8080/webcam.ogg}";
	}
	
	public boolean status() {
		HeadlessMediaPlayer mediaPlayer = this.players.get(PlayerInstance.LIVE);
		return mediaPlayer != null && mediaPlayer.isPlaying();
	}
	
	public void restart(PlayerInstance type) {
		initPlayer(type);
	}
	
	public void pause() {
		this.pause(this.players.get(PlayerInstance.LIVE));
	}
	
	private void pause(HeadlessMediaPlayer mediaPlayer) {
		mediaPlayer.pause();
	}
	
	public void play(PlayerInstance type) {
		HeadlessMediaPlayer player = this.players.get(type);
		if (player == null) {
			player = initPlayer(type);
		}
		
		player.play();
	}
	
	private void stopAndRelease(HeadlessMediaPlayer mediaPlayer) {
		mediaPlayer.stop();
		mediaPlayer.release();
	}
}
