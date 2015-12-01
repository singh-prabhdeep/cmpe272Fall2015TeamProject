package com.cmpe.data;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@Path("/topincidents")
public class TopIncidents {
	
	@GET
	@Path("/getTopCategory/{count}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopCategory(@PathParam("count") String count) throws JSONException {
		GetData data = new GetData();
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray1 = new JSONArray();
		JSONArray jarray2 = new JSONArray();
		int counter = 0;
		
		
		try {
			ResultSet rs = data.getTopCategory();
			while (rs.next() && counter < Integer.parseInt(count)) {
				jarray1.put(rs.getString(1));
				jarray2.put(rs.getDouble(2));
				counter++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonobj.put("val1", jarray1);
		jsonobj.put("val2", jarray2);
		//System.out.println(jsonobj);
		return Response.status(200).entity(jsonobj).build();
	}
	
	@GET
	@Path("/getTopCategory")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTopCategory() throws JSONException {
		GetData data = new GetData();	
		JSONArray jarray1 = new JSONArray();
		
		try {
			ResultSet rs = data.getTopCategory();
			while (rs.next()) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("category", rs.getString(1));
				jsonobj.put("numbers", rs.getDouble(2));
				jarray1.put(jsonobj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(jarray1).build();
	}
	
	@GET
	@Path("/hourlyincidents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHourlyData() throws JSONException {
		GetData data = new GetData();
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		
		try {
			ResultSet rs = data.getHourlyIncidents();
			while (rs.next()) {
				jarray.put(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonobj.put("returndata", jarray);
		return Response.status(200).entity(jsonobj).build();
	}
	
	@GET
	@Path("/gettopdays")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gettopdays() throws JSONException {
		GetData data = new GetData();
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray1 = new JSONArray();
		JSONArray jarray2 = new JSONArray();

		try {
			ResultSet rs = data.getTopDays();
			while (rs.next()) {
				jarray1.put(rs.getString(1));
				jarray2.put(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonobj.put("returnday", jarray1);
		jsonobj.put("returnvalue", jarray2);
		return Response.status(200).entity(jsonobj).build();
	}
		
	@GET
	@Path("/getmonthlyincidents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getmonthlyincidents() throws JSONException {
		GetData data = new GetData();
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray = new JSONArray();
		
		try {
			ResultSet rs = data.getMonthlyIncidents();
			while (rs.next()) {
				jarray.put(rs.getDouble(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonobj.put("returndata", jarray);
		return Response.status(200).entity(jsonobj).build();
	}
	
	@GET
	@Path("/totalincidents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response totalIncidents() {
		Double total_incidents = 0d;
		GetData data = new GetData();
		String num = "";
		try {
			ResultSet rs = data.getTotalIncidents();
			while (rs.next()) {
				total_incidents = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		num = String.format("%.3f", total_incidents/ 1000000.0);
		return Response.status(200).entity(num).build();
	}
	
	@GET
	@Path("/totaltrafficincidents")
	@Produces(MediaType.APPLICATION_JSON)
	public Response totalTrafficIncidents() {
		Double total_incidents = 0d;
		GetData data = new GetData();
		String num = "";
		try {
			ResultSet rs = data.getTotalTrafficIncidents();
			while (rs.next()) {
				total_incidents = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		num = String.format("%.3f", total_incidents/ 1000.0);
		return Response.status(200).entity(num).build();
	}
	
	@GET
	@Path("/drivinginfluence")
	@Produces(MediaType.APPLICATION_JSON)
	public Response drivingUnderInfluence() throws JSONException {
		JSONObject jsonobj = new JSONObject();
		String category = "";
		Double total_incidents = 0d;
		GetData data = new GetData();
		try {
			ResultSet rs = data.getDrivingInfluence();
			while (rs.next()) {
				category = rs.getString(1);
				total_incidents = rs.getDouble(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonobj.put("category", category);
		jsonobj.put("total", total_incidents);
		return Response.status(200).entity(jsonobj).build();
	}
	
	@GET
	@Path("/yearly")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getYearlyData() throws JSONException {
		Double total_incidents = 0d;
		GetData data = new GetData();
		JSONObject jsonobj = new JSONObject();
		JSONArray jarray1 = new JSONArray();
		JSONArray jarray2 = new JSONArray();
		try {
			ResultSet rs = data.getYearlyData();
			while (rs.next()) {
				jarray1.put(rs.getString(1));
				jarray2.put(rs.getDouble(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		jsonobj.put("val1", jarray1);
		jsonobj.put("val2", jarray2);
		return Response.status(200).entity(jsonobj).build();
	}

	
	@GET
	@Path("/yearly/{year}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response yearlyData(@PathParam("year") String stringYear) throws JSONException {
		int year = Integer.parseInt(stringYear);
		JSONObject jsonobj = new JSONObject();
		GetData data = new GetData();
		double prevYear = 0d;
		double currYear = 0d;
		try {
			ResultSet rs1 = data.getYearlyData(year);
			ResultSet rs2 = data.getYearlyData(year-1);
			while (rs1.next()) {
				currYear = rs1.getDouble(1);
			}
			while (rs2.next()) {
				prevYear = rs2.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (prevYear > currYear) {
			double diff = prevYear - currYear;
			double diff_per = (100 * diff) / prevYear;
			jsonobj.put("currYear", currYear);
			jsonobj.put("diff_per", new DecimalFormat("##.##").format(diff_per));
			jsonobj.put("diff", "desc");
		} else if (currYear > prevYear) {
			double diff = currYear - prevYear;
			double diff_per = (100 * diff) / prevYear;
			jsonobj.put("currYear", currYear);
			jsonobj.put("diff_per", new DecimalFormat("##.##").format(diff_per));
			jsonobj.put("diff", "asc");
		} else {
			jsonobj.put("currYear", currYear);
			jsonobj.put("diff_per", 0);
			jsonobj.put("diff", "na");
		}
		return Response.status(200).entity(jsonobj).build();
	}
	
	

}
