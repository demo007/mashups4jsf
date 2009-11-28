/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.googlecode.mashups4jsf.component.youtube;

import java.io.IOException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.Renderer;

import com.googlecode.mashups4jsf.common.util.ComponentConstants;
import com.googlecode.mashups4jsf.common.util.FileUtil;

/**
 * @author Hazem Saleh
 * @date Nov. 28, 2009
 * The (YouTubePlayerRenderer) renders youTube player.
 */
public class YouTubePlayerRenderer extends Renderer {
    private static final String TEMPLATE_FILE_NAME = "template.youTubePlayer";

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        if (component.isRendered()) {
            ResponseWriter  writer        = context.getResponseWriter();        
            YouTubePlayer   youTubePlayer = (YouTubePlayer) component;
            
            encodeHTMLModel(context, writer, youTubePlayer);
            encodeScript(context, writer, youTubePlayer);
        }
    }

    private void encodeHTMLModel(FacesContext context, ResponseWriter writer, YouTubePlayer youTubePlayer)
                                 throws IOException {

        // encode the HTML model.
        writer.startElement  (ComponentConstants.DIV_ELEMENT, youTubePlayer);
        writer.writeAttribute(ComponentConstants.ID_ATTRIBUTE, getPlayerDivID(context, youTubePlayer),
                              ComponentConstants.ID_ATTRIBUTE);
        writer.endElement    (ComponentConstants.DIV_ELEMENT);
    }
    

    private void encodeScript(FacesContext context, ResponseWriter writer, YouTubePlayer youTubePlayer)
                              throws IOException {
        
        // encode the script.
        String playerScript = FileUtil.getTemplateContent(TEMPLATE_FILE_NAME); 
        playerScript        = playerScript.replaceAll("#ID", getPlayerID(context, youTubePlayer))
                                          .replaceAll("#BG_COLOR", youTubePlayer.getBgcolor())
                                          .replaceAll("#VIDEO_ID", youTubePlayer.getVideoID())
                                          .replaceAll("#DIV_ID", getPlayerDivID(context, youTubePlayer))
                                          .replaceAll("#WIDTH", youTubePlayer.getWidth())
                                          .replaceAll("#HEIGHT", youTubePlayer.getHeight());
        
        //System.out.println("playerScript: " + playerScript);
        
        writer.write(playerScript);
    }    
   
    private String getPlayerDivID(FacesContext context, YouTubePlayer youTubePlayer) {
        return (youTubePlayer.getClientId(context) + "_div").replace(':', '_');
    }    
    
    private String getPlayerID(FacesContext context, YouTubePlayer youTubePlayer) {
        return (youTubePlayer.getClientId(context)).replace(':', '_');
    }        
}