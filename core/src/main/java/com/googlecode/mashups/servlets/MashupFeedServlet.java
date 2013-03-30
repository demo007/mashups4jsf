package com.googlecode.mashups.servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.googlecode.mashups.services.factory.GenericServicesFactory;
import com.googlecode.mashups.services.generic.api.FeedType;
import com.googlecode.mashups4jsf.common.util.ExceptionMessages;
import com.googlecode.mashups4jsf.common.util.Mashups4JSFConstants;

/**
 * <p>
 * <code>MashupFeedServlet</code> servlet is used for generating feed (RSS or ATOM) 
 * from the application class annotated with @Feed.
 * </p>
 * <p>
 * The <code>MashupFeedServlet</code> servlet has the following parameters:<br/>
 * 1. feedClass: The application class annotated with @Feed.
 * 2. output: The output of the feed (rss or atom). 
 * It can be passed as a servlet GET parameter or as a servlet init parameter. <br/>
 * </p>
 * @author hazems
 *
 */
public class MashupFeedServlet extends HttpServlet {
	private static final long serialVersionUID = -7608649162418733939L;
	private ServletConfig servletConfig = null;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Read Servlet parameters.
        String feedClassParameter = request.getParameter(Mashups4JSFConstants.FEED_CLASS);
        String feedIDParameter = request.getParameter(Mashups4JSFConstants.FEED_ID);        
        
        // Generate Feed.
        String   outputType        = request.getParameter(Mashups4JSFConstants.OUTPUT_TYPE);
        String   responseType      = "";
        Object   feedClassInstance = null;
        Class    feedClass         = null;
        FeedType feedType          = null;
        
        try {
        	
        	if (feedClassParameter != null) {
        		// If the feedClass parameter is already specified, DO NOTHING.
        	} else if (feedIDParameter != null) {
        		
        		// If the feedClass parameter is not specified, then get the feedID to resolve the feedClass from the <context-param>.
        		feedClassParameter = (String) servletConfig.getServletContext().getInitParameter(feedIDParameter);
        		
        		if (feedClassParameter == null) {
        			throw new ServletException("Unable to find the feed class whose id is: " + feedIDParameter);
        		}
        	} else {
        		throw new ServletException("You have to specify either the feedClass or feedID parameters ...");
        	}
        	
    		feedClass = Class.forName(feedClassParameter);
    		feedClassInstance = feedClass.newInstance();        	
            
            if (outputType.equalsIgnoreCase(Mashups4JSFConstants.RSS_VALUE)) {
                feedType = FeedType.RSS;
                responseType = Mashups4JSFConstants.RSS_OUTPUT_TYPE;
            } else {
                feedType = FeedType.ATOM;
                responseType = Mashups4JSFConstants.ATOM_OUTPUT_TYPE;                
            }
        } catch (Exception exception) {
        	exception.printStackTrace();
            throw new ServletException(Mashups4JSFConstants.FEED_ERROR_MESSAGE_CANNOT_INSTANCE_CLASS);            
        }
        
        // Set the content type.
        response.setContentType(responseType);
        
        try {
            
            // Use the Mashups4JSF Generic Service Feed Producer to produce the feed.
            GenericServicesFactory.getFeedProducerService().produceFeed(feedType, feedClassInstance, response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();        	
            System.err.println(ExceptionMessages.FEED_PRODUCING_ERROR + e.getMessage());
        }        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); 
    }
	
    @Override
	public void init(ServletConfig servletConfig) throws ServletException {
		this.servletConfig = servletConfig;
	}
	
    public ServletConfig getServletConfig() {   
    	return (this.servletConfig);
    }
}
