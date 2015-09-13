package org.nalb.web;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.nalb.auth.User;
import org.nalb.auth.UsersController;

@Path("/controller")
@Produces(MediaType.APPLICATION_JSON)
public class WebInterface {
	@Inject
	private UsersController users;
	private State state = new State();
	
	private static class State {
		private static boolean recordingVideo;
		private static boolean localAudio;
		private static boolean remoteAudio;
		private static boolean mixingAudio;
	}
	
	@Path("/recording")
	public Response onRecordingChange(String sessionKey) {
		User user = this.users.getUser(sessionKey);
		
		if (user == null) {
			return Response.status(403).build();
		}
		
		this.state.recordingVideo = true;
		
		return Response.ok().build();
	}
}
