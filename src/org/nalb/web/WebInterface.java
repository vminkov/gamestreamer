package org.nalb.web;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.nalb.auth.User;
import org.nalb.stream.MosaicMediaStreamer;
import org.nalb.web.auth.BasicAuth;
import org.nalb.web.auth.UserController;

import com.codahale.metrics.annotation.Timed;

@Path("/stream")
@Produces(MediaType.APPLICATION_JSON)
public class WebInterface {
	private MosaicMediaStreamer streamer;
	private UserController users;
	
	private State state = new State();
	
	@Inject
	public WebInterface(MosaicMediaStreamer streamer, UserController users) {
		this.streamer = streamer;
		this.users = users;
	}
	
	private static class State {
		private static boolean recordingVideo;
		private static boolean localAudio;
		private static boolean remoteAudio;
		private static boolean mixingAudio;
	}
	
	@Path("/record")
	public Response onRecordingChange(@BasicAuth User user) {
		this.state.recordingVideo = true;
		
		return Response.ok().build();
	}
	
	@Path("/start")
	@POST
	@Timed
	public Response onPlay(@BasicAuth User user) {
		System.out.println(user);
		
		this.streamer.play();
		
		return Response.ok().build();
	}
}
