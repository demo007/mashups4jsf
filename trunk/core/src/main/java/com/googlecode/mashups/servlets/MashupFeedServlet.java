package com.googlecode.mashups.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.mashups.services.factory.GenericServicesFactory;
import com.googlecode.mashups.services.generic.api.Feed;
import com.googlecode.mashups.services.generic.api.Feed.FeedType;
import com.googlecode.mashups4jsf.common.util.Mashups4JSFConstants;

/**
 * <p>
 * <code>MashupFeedServlet</code> servlet is used for generating feed (RSS or ATOM) 
 * from the application class annotated with @Feed.
 * </p>
 * <p>
 * The <code>MashupFeedServlet</code> servlet has the following parameters:<br/>
 * 1. FEED_CLASS: The application class annotated with @Feed. 
 * It can be passed as a servlet GET parameter or as a servlet init parameter. <br/>
 * </p>
 * @author hazems
 *
 */
public class MashupFeedServlet extends HttpServlet {

	private static final long serialVersionUID = -7608649162418733939L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Read the servlet parameters.
        String feedClassParameter = getServletConfig().getInitParameter(Mashups4JSFConstants.FEED_CLASS);
        
        if (feedClassParameter == null) {
        	feedClassParameter = request.getParameter(Mashups4JSFConstants.FEED_CLASS);
        }
        
        if (feedClassParameter == null) {
            throw new ServletException(Mashups4JSFConstants.FEED_ERROR_MESSAGE_MISSING_FEED_CLASS);
        }
        
        // Generate the feed.
        Object feedClassInstance = null;
        Class  feedClass         = null;
        String outputType        = "";
        
        try {
        	feedClass = Class.forName(feedClassParameter);
            feedClassInstance = feedClass.newInstance();
            
            Feed feedAnnotation = (Feed) feedClass.getAnnotation(Feed.class);
            
            if (feedAnnotation.type().equals(FeedType.Rss)) {
                outputType = Mashups4JSFConstants.RSS_OUTPUT_TYPE;
            } else {
                outputType = Mashups4JSFConstants.ATOM_OUTPUT_TYPE;
            }
        } catch (Exception exception) {
        	exception.printStackTrace();
            throw new ServletException(Mashups4JSFConstants.FEED_ERROR_MESSAGE_CANNOT_INSTANCE_CLASS);            
        }
        
        System.out.println("OutputType = " + outputType);
        
        // Set the content type.
        response.setContentType(outputType);
        
        try {
            
            // Use the Mashups4JSF Generic Service Feed Producer to produce the feed.
            GenericServicesFactory.getFeedProducerService().produceFeed(feedClassInstance, response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();        	
            System.err.println("The following error occured while producing the feed: " + e.getMessage());
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
