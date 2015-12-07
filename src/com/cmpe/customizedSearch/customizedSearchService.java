package com.cmpe.customizedSearch;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.cmpe.loginDatabaseConnectivity.LoginDatabase;


@Path("/customizedsearch")
public class customizedSearchService {
	//@Context ServletContext context;
	@GET
	@Path("/getyears")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getYears() throws JSONException
	{
		JSONObject obj = new JSONObject();
		try{
			LoginDatabase loginDb = new LoginDatabase();
			ArrayList<String> years = loginDb.getDistinctYears();
			int val = 0;
			for (String element : years){
				val++;
				obj.put(Integer.toString(val), element);
			}
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		return Response.status(200).entity(obj.toString()).build();
	}
	
	
	//@Context ServletContext context;
	@GET
	@Path("/getmonths")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMonths() throws JSONException
	{
      HashMap hm = new HashMap();
      // Put elements to the map
      hm.put("01", "January");
      hm.put("02", "February");
      hm.put("03", "March");
      hm.put("04", "April");
      hm.put("05", "May");
      hm.put("06", "June");
      hm.put("07", "July");
      hm.put("08", "August");
      hm.put("09", "September");
      hm.put("10", "October");
      hm.put("11", "November");
      hm.put("12", "December");
	      
	      
		JSONObject obj = new JSONObject();
		try{
			LoginDatabase loginDb = new LoginDatabase();
			ArrayList<String> months = loginDb.getDistinctMonths();
			int val = 0;
			for (String element : months){
				val++;
				obj.put(Integer.toString(val), hm.get(element));
			}
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		return Response.status(200).entity(obj.toString()).build();
	}
	
	
	//@Context ServletContext context;
	@GET
	@Path("/getcategories")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategories() throws JSONException
	{
		JSONObject obj = new JSONObject();
		try{
			LoginDatabase loginDb = new LoginDatabase();
			ArrayList<String> categories = loginDb.getDistinctCategories();
			int val = 0;
			for (String element : categories){
				val++;
				obj.put(Integer.toString(val), element);
			}
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		return Response.status(200).entity(obj.toString()).build();
	}
	
	//@Context ServletContext context;
	@POST
	@Path("/getlocations")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getLocations(String incomingdata) throws JSONException
	{
		String locations = "";
		System.out.println(incomingdata);
		JSONObject jsonobj = new JSONObject(incomingdata);
		System.out.println(jsonobj);
		
		String year = jsonobj.optString("yearSelect");
		String month = jsonobj.optString("monthSelect");
		String category = jsonobj.optString("categorySelect");
		String monthConverted = "";
		
	      HashMap hm = new HashMap();
	      // Put elements to the map
	      hm.put("January", "01");
	      hm.put("February", "02");
	      hm.put("March", "03");
	      hm.put("April", "04");
	      hm.put("May", "05");
	      hm.put("June", "06");
	      hm.put("July", "07");
	      hm.put("August", "08");
	      hm.put("September", "09");
	      hm.put("October", "10");
	      hm.put("November", "11");
	      hm.put("December", "12");
	      
		
		try{
			LoginDatabase loginDb = new LoginDatabase();
			JSONObject obj = new JSONObject();
			monthConverted = (String) hm.get(month);
			locations = loginDb.getLongLatOfLocations(year, monthConverted, category);
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		return Response.status(200).entity(locations).build();
	}
	@POST
	@Path("/getNearbyIncidents")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getNearbyIncidents(String incomingdata) throws JSONException
	{
		JSONObject locdata = new JSONObject();
		try
		{
		System.out.println(incomingdata);
		//String x = "37.7564864109309";
		//String y = "-122.406539115148";
		JSONObject jsonobj = new JSONObject(incomingdata);
		String x = jsonobj.optString("x");
		String y = jsonobj.optString("y");
		String sql = "SELECT X,Y,COUNT,CATEGORY FROM CUSTOMIZEDSEARCHCRIMEDATA a WHERE (acos(sin(cast(`X` as decimal(16,13)) * 0.0175) * sin("+x+" * 0.0175)+ cos(cast(`X` as decimal(16,13)) * 0.0175) * cos("+x+" * 0.0175) * cos(("+y+" * 0.0175) - (cast(`Y` as decimal(16,13)) * 0.0175)) ) * 3959 <= 0.5)";
		System.out.println(sql);
		LoginDatabase db = new LoginDatabase();
		Connection conn = db.getConn();
		PreparedStatement prep = conn.prepareStatement(sql);
		//prep.setFloat(1, (float) 37.7564864109309);
		//prep.setFloat(2, (float) 37.7564864109309);
		//prep.setFloat(3, (float) -122.406539115148);
		ResultSet rs =  prep.executeQuery();
		JSONArray jarray = new JSONArray();
		while(rs.next())
		{
			JSONObject row = new JSONObject();
			row.put("latitude", rs.getString(1));
			row.put("longitude", rs.getString(2));
			row.put("count", rs.getString(3));
			row.put("category", rs.getString(4));
			jarray.put(row);
		}
		conn.close();
		locdata.put("locationData", jarray);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Response.status(200).entity(locdata).build();
		
	}
}
