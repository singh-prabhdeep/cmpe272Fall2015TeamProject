package com.cmpe.traffic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import com.cmpe.loginDatabaseConnectivity.LoginDatabase;

@Path("/trafficservice")
public class TrafficService {
	@POST
	@Path("/getlocations")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLocations(String incomingdata)
	{
		JSONObject locdata = new JSONObject();
		try
		{
		System.out.println(incomingdata);
		JSONObject jsonobj = new JSONObject(incomingdata);
		int roadCondtion = Integer.parseInt(jsonobj.optString("road_condition"));
		int weatherCondtion = Integer.parseInt(jsonobj.optString("weather_condition"));
		int lightCondtion = Integer.parseInt(jsonobj.optString("light_condition"));
		int specialCondtion = Integer.parseInt(jsonobj.optString("special_condition"));
		String sql = "Select latitude,longitude from UKDATA where ";
		if(roadCondtion!=0)
		{
			sql = sql + "Road_Surface_Conditions = "+roadCondtion+" and ";
		}
		if(weatherCondtion!=0)
		{
			sql = sql + "Weather_Conditions = "+weatherCondtion+" and ";
		}
		if(lightCondtion!=0)
		{
			sql = sql + "Light_Conditions = "+lightCondtion+" and ";
		}
		if(specialCondtion!=0)
		{
			sql = sql + "Special_Conditions_at_Site = "+specialCondtion;
		}
		if(sql.endsWith("and "))
		{
			sql = sql.substring(0, sql.length()-4);
		}
		sql = sql +" group by latitude,longitude order by count(*) desc limit 4000";
		System.out.println(sql);
		LoginDatabase db = new LoginDatabase();
		Connection conn = db.getConn();
		PreparedStatement prep = conn.prepareStatement(sql);
		ResultSet rs =  prep.executeQuery();
		JSONArray jarray = new JSONArray();
		while(rs.next())
		{
			JSONObject row = new JSONObject();
			row.put("latitude", rs.getString(1));
			row.put("longitude", rs.getString(2));
			jarray.put(row);
		}
		conn.close();
		locdata.put("locationData", jarray);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(200).entity(locdata.toString()).build();
	}

}
