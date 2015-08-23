package org.nalb;

import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

public class Start {
	private static String VERBOSE = "0";
	
	public static void main(String[] args) throws InterruptedException {
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
		
		mediaPlayer.play();
		Thread.currentThread().join();
	}
}
