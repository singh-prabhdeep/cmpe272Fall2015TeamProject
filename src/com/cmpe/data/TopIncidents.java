package com.cmpe.data;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/topincidents")
public class TopIncidents {
	@GET
	@Path("/gettopdays")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gettopdays() throws JSONException {
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		jarray.put(243549d);
		jarray.put(253868d);
		jarray.put(261942d);
		jarray.put(262114d);
		jarray.put(265187d);
		jarray.put(269839d);
		jarray.put(280190d);
		jsonobj.put("returndata", jarray);
		return Response.status(200).entity(jsonobj).build();
	}
	@GET
	@Path("/getmonthlyincidents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getmonthlyincidents() throws JSONException {
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		jarray.put(159264d);
		jarray.put(144226d);
		jarray.put(160601d);
		jarray.put(154102d);
		jarray.put(157901d);
		jarray.put(149817d);
		jarray.put(156216d);
		jarray.put(161697d);
		jarray.put(157893d);
		jarray.put(162890d);
		jarray.put(138291d);
		jarray.put(133791d);
		
		jsonobj.put("returndata", jarray);
		return Response.status(200).entity(jsonobj).build();
	}

}
