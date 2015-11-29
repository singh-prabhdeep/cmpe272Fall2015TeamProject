package com.cmpe.recomender;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

/**
 * Servlet implementation class RecommenderServlet
 */
@WebServlet("/RecommenderServlet")
public class RecommenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommenderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
	         //Creating data model
			String username = request.getParameter("form-hour");
			System.out.println();
	         DataModel datamodel = new FileDataModel(new File(getServletContext().getRealPath("/")+"/WEB-INF/classes/dataset1.csv")); //data
	      
	         //Creating UserSimilarity object.
	         UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(datamodel);
	      
	         //Creating UserNeighbourHHood object.
	         UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(0.1, usersimilarity, datamodel);
	      
	         //Create UserRecomender
	         UserBasedRecommender recommender = new GenericUserBasedRecommender(datamodel, userneighborhood, usersimilarity);
	         List<RecommendedItem> recommendations = recommender.recommend(18, 3);
				
	         for (RecommendedItem recommendation : recommendations) {
	            System.out.println(recommendation);
	            System.out.println(recommendation.getItemID());
	         }
	      
	      }catch(Exception e){e.printStackTrace();}

	}

}
