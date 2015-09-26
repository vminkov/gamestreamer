package org.nalb.stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

@Singleton
public class MosaicMediaStreamer {
	private static String VERBOSE = "0";
	private HeadlessMediaPlayer mediaPlayer;
	
	public MosaicMediaStreamer() {
	}
	
	private void initPlayer() {
		MediaPlayerFactory mFactory = new MediaPlayerFactory("--vlm-conf=mosaic.conf", "--verbose=" + VERBOSE, "--mosaic-width=1280", "--mosaic-height=360",
			"--mosaic-rows=1", "--mosaic-cols=2", "--mosaic-position=1", "--mosaic-order=1,2", "--sout-keep");
		HeadlessMediaPlayer mediaPlayer = mFactory.newHeadlessMediaPlayer();
		
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
		
		this.mediaPlayer = mediaPlayer;
	}
	
	public void play() {
		if (this.mediaPlayer == null) {
			initPlayer();
		}
		
		this.mediaPlayer.play();
	}
	
	public void pause() {
		this.mediaPlayer.pause();
	}
}
