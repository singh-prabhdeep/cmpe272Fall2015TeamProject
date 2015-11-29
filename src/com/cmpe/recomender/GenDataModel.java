package com.cmpe.recomender;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

public class GenDataModel {
	
	public DataModel returnDataModel(ServletContext context)
	{
		DataModel datamodel=null;
		try {
			datamodel = new FileDataModel(new File(context.getRealPath("/")+"/WEB-INF/classes/dataset1.csv"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datamodel;
	}
	public ArrayList<String> createMetaData(ServletContext context,String hour){
		File file = new File(context.getRealPath("/")+"/WEB-INF/classes/dataset1.csv");
		ArrayList<String> list = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
		    for(String line; (line = br.readLine()) != null; ) {
		    	String data[] = line.split(",");
		    	
		    	if(data[0].equalsIgnoreCase(hour))
		    	{
		    		System.out.println(data[0]+"---"+hour);
		    		list.add(data[1]);
		    	}
		    	
		    }
		    
		    // line is not visible here.
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
		
	}
	public Map<String,String> getLocationKeys(ServletContext context)
	{
		File file = new File(context.getRealPath("/")+"/WEB-INF/classes/keydata.csv");
		Map<String, String> keyMap = new HashMap<String, String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)); 
		    for(String line; (line = br.readLine()) != null; ) {
		    	String lineData[] = line.split(",");
		    	keyMap.put(lineData[0], lineData[1]);
		    	
		    	
		    }
		    
		    // line is not visible here.
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return keyMap;
	}

}
