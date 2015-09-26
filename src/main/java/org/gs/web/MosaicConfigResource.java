package org.gs.web;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import static org.gs.stream.MosaicMediaStreamer.getConfFileName;

import org.apache.commons.io.FileUtils;
import org.gs.auth.User;
import org.gs.stream.MosaicMediaStreamer;
import org.gs.stream.PlayerInstance;
import org.gs.web.auth.BasicAuth;

import com.fasterxml.jackson.annotation.JsonProperty;

@Path("/mosaic")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MosaicConfigResource {
	private static final String MOSAIC_CONF_BACKUP = "mosaic.conf.backup";
	@Inject
	private MosaicMediaStreamer streamer;
	
	public MosaicConfigResource() {
	}
	
	@Path("/get")
	@GET
	public TextData getCurrent(@BasicAuth User user) {
		try {
			String fileToString = FileUtils.readFileToString(new File(getConfFileName(PlayerInstance.LIVE)));
			return new TextData(fileToString);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
	}
	
	@Path("/get/default")
	@GET
	public TextData getDefault(@BasicAuth User user) {
		try {
			String fileToString = FileUtils.readFileToString(new File(MOSAIC_CONF_BACKUP));
			return new TextData(fileToString);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
	}
	
	@Path("/update")
	@POST
	public Response updateConf(@BasicAuth User user, TextData data) {
		if (data == null || data.text == null) {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		
		try {
			FileUtils.writeStringToFile(new File(getConfFileName(PlayerInstance.LIVE)), data.text);
			this.streamer.restart(PlayerInstance.LIVE);
			FileUtils.writeStringToFile(new File(getConfFileName(PlayerInstance.TEST)), data.text);
			this.streamer.restart(PlayerInstance.TEST);
			
			return JsonResponse.ok();
		}
		catch (IOException e) {
			e.printStackTrace();
			throw new WebApplicationException(e);
		}
	}
}

class TextData {
	public TextData() {
	}
	
	public TextData(String text) {
		this.text = text;
	}
	
	@JsonProperty
	String text;
	
	@Override
	public String toString() {
		return text;
	}
}
