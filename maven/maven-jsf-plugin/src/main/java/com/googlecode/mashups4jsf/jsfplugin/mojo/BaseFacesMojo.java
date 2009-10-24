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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.mashups4jsf.jsfplugin.digester.Attribute;
import com.googlecode.mashups4jsf.jsfplugin.digester.Component;

import org.apache.commons.digester.Digester;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

/**
 * Base class for all the jsf mojos, parses the component config files and generates output directories
 *
 * @author Latest modification by $Author: cagatay_civici $
 * @version $Revision: 748 $ $Date: 2007-07-04 14:55:12 +0300 (Wed, 04 Jul 2007) $
 */
public abstract class BaseFacesMojo extends AbstractMojo{

	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

	/**
	 * @parameter
	 * @required
	 */
	protected String componentConfigsDir;

	/**
	 * @parameter
	 */
	protected String templatesDir;

	protected String[] uicomponentAttributes = new String[]{"id","rendered","binding"};

	protected String[] specialAttributes = new String[]{"value","converter","validator","valueChangeListener","immediate","required","action","actionListener"};

	protected File[] getResources() {
		return new File(project.getBasedir() + File.separator + componentConfigsDir).listFiles();
	}

	protected Digester getDigester() {
		Digester digester = new Digester();
		digester.setValidating(false);

		digester.addObjectCreate("component", Component.class);
		digester.addBeanPropertySetter("component/tag", "tag");
		digester.addBeanPropertySetter("component/tagClass", "tagClass");
		digester.addBeanPropertySetter("component/componentClass", "componentClass");
		digester.addBeanPropertySetter("component/componentType", "componentType");
		digester.addBeanPropertySetter("component/componentFamily", "componentFamily");
		digester.addBeanPropertySetter("component/rendererType", "rendererType");
		digester.addBeanPropertySetter("component/rendererClass", "rendererClass");
		digester.addBeanPropertySetter("component/parent", "parent");

		digester.addObjectCreate("component/attributes/attribute", Attribute.class);
		digester.addBeanPropertySetter("component/attributes/attribute/name","name");
		digester.addBeanPropertySetter("component/attributes/attribute/required","required");
		digester.addBeanPropertySetter("component/attributes/attribute/type","type");
		digester.addBeanPropertySetter("component/attributes/attribute/description","description");
		digester.addBeanPropertySetter("component/attributes/attribute/defaultValue","defaultValue");
		digester.addBeanPropertySetter("component/attributes/attribute/ignoreInComponent","ignoreInComponent");

		digester.addSetNext("component/attributes/attribute", "addAttribute");

		return digester;
	}

	protected List getComponents() {
		File[] resources = getResources();
		Digester digester = getDigester();
		List components = new ArrayList();

		for (int i = 0; i < resources.length; i++) {
			try {

				File resource = resources[i];
				if(resource.getName().endsWith(".xml")) {
					components.add( digester.parse( resources[i]));
				}

			} catch (Exception e) {
				getLog().info(e.getMessage());
				getLog().info("Error in generation");
				return null;
			}
		}

		return components;
	}

	protected String getCreateOutputDirectory() {

		String outputPath = project.getBuild().getDirectory()
				+ File.separator + "maven-jsf-plugin" + File.separator + "main"
				+ File.separator + "java" + File.separator + "com"
				+ File.separator + "googlecode" + File.separator + "mashups4jsf"
				+ File.separator + "component";

		File componentsDirectory = new File(outputPath);
		if(!componentsDirectory.exists())
			componentsDirectory.mkdirs();

		return outputPath;
	}

	protected String createPackageDirectory(String outputPath, Component component) {
		String packagePath = outputPath + File.separator + component.getParentPackagePath();

		File packageDirectory = new File(packagePath);
		if(!packageDirectory.exists())
			packageDirectory.mkdirs();

		return packagePath;
	}

	protected String getLicense() {
		String license = "/*\n"+
						" * Copyright 2007 The Apache Software Foundation.\n" +
						" *\n" +
						" * Licensed under the Apache License, Version 2.0 (the \"License\");\n"+
						" * you may not use this file except in compliance with the License.\n" +
						" * You may obtain a copy of the License at\n" +
 						" *\n" +
 						" * http://www.apache.org/licenses/LICENSE-2.0\n" +
 						" *\n" +
 						" * Unless required by applicable law or agreed to in writing, software\n" +
 						" * distributed under the License is distributed on an \"AS IS\" BASIS,\n" +
 						" * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\n" +
 						" * See the License for the specific language governing permissions and\n" +
 						" * limitations under the License.\n" +
 						" */\n";
		return license;
	}

	protected void writeLicense(BufferedWriter writer) throws IOException{
		writer.write(getLicense());
	}
}