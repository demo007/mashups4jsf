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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;

import com.googlecode.mashups4jsf.jsfplugin.digester.Attribute;
import com.googlecode.mashups4jsf.jsfplugin.digester.Component;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Generates tld
 *
 * @author Latest modification by $Author: cagatay_civici $
 * @version $Revision: 150 $ $Date: 2007-05-29 10:15:20 +0300 (Tue, 29 May 2007) $
 *
 * @goal generate-tld
 */
public class TLDMojo extends BaseFacesMojo {

	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info("Generating TLD");

		writeTLD(getComponents());

		getLog().info("TLD Generated successfully");
	}

	private void writeTLD(List components) {
		FileWriter fileWriter;
		BufferedWriter writer;
		String outputPath = project.getBuild().getOutputDirectory() + File.separator + "META-INF";
		String outputFile =  "mashups4jsf.tld";

		try {
			File tldDirectory = new File(outputPath);
			tldDirectory.mkdirs();

			fileWriter = new FileWriter(outputPath + File.separator + outputFile);
			writer = new BufferedWriter(fileWriter);

			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<!DOCTYPE taglib PUBLIC \"-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.2//EN\"\n");
			writer.write("\t\t\t\t\"http://java.sun.com/dtd/web-jsptaglibrary_1_2.dtd\">\n");
			writer.write("<taglib>\n");
			writer.write("\t<tlib-version>1.0</tlib-version>\n");
			writer.write("\t<jsp-version>1.2</jsp-version>\n");
			writer.write("\t<short-name>yui</short-name>\n");
			writer.write("\t<uri>http://code.google.com/p/mashups4jsf/</uri>\n");

			for (Iterator iterator = components.iterator(); iterator.hasNext();) {
				Component component = (Component) iterator.next();
				writer.write("\t<tag>\n");
				writer.write("\t\t<name>" + component.getTag() + "</name>\n");
				writer.write("\t\t<tag-class>" + component.getTagClass() + "</tag-class>\n");
				writer.write("\t\t<body-content>JSP</body-content>\n");

				for (Iterator iterator2 = component.getAttributes().iterator(); iterator2.hasNext();) {
					Attribute attribute = (Attribute) iterator2.next();

					writer.write("\t\t<attribute>\n");
					writer.write("\t\t\t<name>" + attribute.getName() + "</name>\n");
					writer.write("\t\t\t<required>" + attribute.getRequired() + "</required>\n");
					writer.write("\t\t\t<rtexprvalue>false</rtexprvalue>\n");
					writer.write("\t\t\t<type>java.lang.String</type>\n");
					writer.write("\t\t\t<description>" + attribute.getDescription() + "</description>\n");
					writer.write("\t\t</attribute>\n");
				}
				writer.write("\t</tag>\n");
			}
			writer.write("</taglib>");
			writer.close();
		}catch(Exception exception) {
			getLog().error( exception.getMessage() );
		}
	}
}