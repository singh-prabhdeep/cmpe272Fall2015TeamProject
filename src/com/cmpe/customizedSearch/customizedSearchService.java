package com.cmpe.customizedSearch;

import java.io.File;
import java.util.ArrayList;
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
		JSONObject obj = new JSONObject();
		try{
			LoginDatabase loginDb = new LoginDatabase();
			ArrayList<String> months = loginDb.getDistinctMonths();
			int val = 0;
			for (String element : months){
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
		
		
		try{
			LoginDatabase loginDb = new LoginDatabase();
			JSONObject obj = new JSONObject();
			locations = loginDb.getLongLatOfLocations(year, month, category);
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
		return Response.status(200).entity(locations).build();
	}
}