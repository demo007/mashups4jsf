/*
 * Copyright 2007 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.mashups4jsf.jsfplugin.mojo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.googlecode.mashups4jsf.jsfplugin.digester.Component;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Generates faces-config.xml
 *
 * @author Latest modification by $Author: cagatay_civici $
 * @version $Revision: 373 $ $Date: 2007-06-11 11:53:19 +0300 (Mon, 11 Jun 2007) $
 *
 * @goal generate-faces-config
 */
public class FacesConfigMojo extends BaseFacesMojo{

	/**
	 * @parameter
	 */
	protected String standardFacesConfig;

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Generating faces-config.xml");

		try {
			writeFacesConfig(getComponents());
			getLog().info("faces-config.xml generated successfully");
		} catch (Exception e) {
			getLog().info("Error occured in generating faces-config.xml");
			getLog().info(e.toString());
		}
	}

	private void writeFacesConfig(List components) {
		FileWriter fileWriter;
		BufferedWriter writer;
		String outputPath = project.getBuild().getOutputDirectory() + File.separator + "META-INF";
		String outputFile =  "faces-config.xml";

		try {
			File tldDirectory = new File(outputPath);
			tldDirectory.mkdirs();

			fileWriter = new FileWriter(outputPath + File.separator + outputFile);
			writer = new BufferedWriter(fileWriter);

			writeFacesConfigBegin(writer, components);
			writeStandardConfig(writer);
			writeComponents(writer, components);
			writeRenderers(writer, components);
			writeFacesConfigEnd(writer, components);

			writer.close();
			fileWriter.close();
		}
		catch(Exception exception) {
			getLog().error( exception.getMessage() );
		}
	}

	private void writeFacesConfigBegin(BufferedWriter writer, List components) throws IOException {
		writer.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n\n");
		writer.write("<!DOCTYPE faces-config PUBLIC\n");
        writer.write("\t\t\"-//Sun Microsystems, Inc.//DTD JavaServer Faces Config 1.1//EN\"\n");
        writer.write("\t\t\"http://java.sun.com/dtd/web-facesconfig_1_1.dtd\">\n\n");

        writer.write("<faces-config xmlns=\"http://java.sun.com/JSF/Configuration\">\n\n");
	}

	private void writeStandardConfig(BufferedWriter writer) throws IOException{
		try {
			File template = new File(project.getBasedir() + File.separator + standardFacesConfig);
			FileReader fileReader = new FileReader(template);
			BufferedReader reader = new BufferedReader(fileReader);
			String line = null;

			while((line = reader.readLine()) != null) {
				writer.write(line);
				writer.write("\n\n");
			}
		}catch(FileNotFoundException fileNotFoundException) {
			return;
		}
	}

	private void writeFacesConfigEnd(BufferedWriter writer, List components) throws IOException {
		writer.write("</faces-config>");
	}

	private void writeComponents(BufferedWriter writer, List components) throws IOException {
		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();

			writer.write("\t<component>\n");
			writer.write("\t\t<component-type>" + component.getComponentType() + "</component-type>\n");
			writer.write("\t\t<component-class>" + component.getComponentClass() + "</component-class>\n");
			writer.write("\t</component>\n");
			writer.write("\n");
		}
	}

	private void writeRenderers(BufferedWriter writer, List components) throws IOException{
		writer.write("\t<render-kit>\n");

		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			if(component.getRendererType() == null)
				continue;

			writer.write("\t\t<renderer>\n");
			writer.write("\t\t\t<component-family>" + component.getComponentFamily() + "</component-family>\n");
			writer.write("\t\t\t<renderer-type>" + component.getRendererType() + "</renderer-type>\n");
			writer.write("\t\t\t<renderer-class>" + component.getRendererClass() + "</renderer-class>\n");
			writer.write("\t\t</renderer>\n");
		}

		writer.write("\t</render-kit>\n\n");
	}
}