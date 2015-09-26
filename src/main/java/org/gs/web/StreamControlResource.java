package org.gs.web;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.gs.auth.User;
import org.gs.stream.MosaicMediaStreamer;
import org.gs.web.auth.BasicAuth;

import com.codahale.metrics.annotation.Timed;

@Path("/stream")
@Produces(MediaType.APPLICATION_JSON)
public class StreamControlResource {
	private MosaicMediaStreamer streamer;
	
	@Inject
	public StreamControlResource(MosaicMediaStreamer streamer) {
		this.streamer = streamer;
	}
	
	@Path("/pause")
	@POST
	public Response onPause(@BasicAuth User user) {
		this.streamer.pause();
		
		return JsonResponse.ok();
	}
	
	@Path("/start")
	@POST
	@Timed
	public Response onPlay(@BasicAuth User user) {
		this.streamer.play();
		
		return JsonResponse.ok();
	}
	
	@Path("/status")
	@GET
	public Map<String, String> getStatus() {
		Map<String, String> result = new HashMap<>();
		result.put("isPlaying", new Boolean(this.streamer.status()).toString());
		
		return result;
	}
}
