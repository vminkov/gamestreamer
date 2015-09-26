package org.gs.web;

import javax.ws.rs.core.Response;

public class JsonResponse {
	
	public static Response ok() {
		return Response.ok("{}").build();
	}
	
	public static Response ok(String message) {
		return Response.ok("{'message':'" + message + "'}").build();
	}
}
