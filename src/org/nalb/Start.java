package org.nalb;

import org.nalb.stream.MosaicMediaStreamer;
import org.nalb.web.GuiceModule;
import org.nalb.web.WebApplication;

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
			ms.play();
			
			Thread.currentThread().join();
		}
	}
}
