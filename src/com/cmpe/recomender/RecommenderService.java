package com.cmpe.recomender;

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

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
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


@Path("/recommendservice")

public class RecommenderService {
	@Context ServletContext context;
	@POST
	@Path("/getlocations")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getlocations(String incomingdata) throws JSONException
	{
		System.out.println(incomingdata);
		JSONObject jsonobj = new JSONObject(incomingdata);
		System.out.println(jsonobj);
		JSONArray locationJson =  new JSONArray();
		int hour = Integer.parseInt(jsonobj.optString("hour_day"));
		try{
	         //Creating data model
			System.out.println(hour);
			GenDataModel newDataModel = new GenDataModel();
	         DataModel datamodel = newDataModel.returnDataModel(context); //data
	      
	         //Creating UserSimilarity object.
	         UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(datamodel);
	      
	         //Creating UserNeighbourHHood object.
	         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(0.1, usersimilarity, datamodel);
	      
	         //Create UserRecomender
	         UserBasedRecommender recommender = new GenericUserBasedRecommender(datamodel, userneighborhood, usersimilarity);
	         List<RecommendedItem> recommendations = recommender.recommend(hour, 3);
	         ArrayList<String> listloc = newDataModel.createMetaData(context,hour+"");
	         Map<String,String> keyMap = newDataModel.getLocationKeys(context);
	         
	         System.out.println(listloc);
	         int i = 0;
	         for(String location: listloc)
	         {
	        	 locationJson.put(keyMap.get(location));
	        	 System.out.println(keyMap.get(location));
	        	 i++;
	        	 if(i>=3)
	        	 {
	        		 break;
	        	 }
	         }
	         for (RecommendedItem recommendation : recommendations) {
	            System.out.println(recommendation);
	            System.out.println(recommendation.getItemID());
	            locationJson.put(keyMap.get(recommendation.getItemID()+""));
	           
	         }
	         System.out.println(locationJson.toString());
	      
	      }catch(Exception e){e.printStackTrace();
	      return Response.serverError().build();
	      }
		return Response.status(200).entity(locationJson.toString()).build();
	}

}
