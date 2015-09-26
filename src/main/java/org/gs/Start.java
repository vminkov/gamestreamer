package org.gs;

import org.gs.stream.MosaicMediaStreamer;
import org.gs.stream.PlayerInstance;
import org.gs.web.GuiceModule;
import org.gs.web.WebApplication;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Start {
	public static void main(String[] args) throws InterruptedException {
		if (args.length > 0 && "web".equals(args[0])) {
			Injector injector = Guice.createInjector(new GuiceModule());
			WebApplication webApp = injector.getInstance(WebApplication.class);
			
			try {
				webApp.run(new String[] {"server"});
			}
			catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		else {
			MosaicMediaStreamer ms = new MosaicMediaStreamer();
			ms.play(PlayerInstance.LIVE);
			
			Thread.currentThread().join();
		}
	}
}
