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

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Adds the current timestamp to the project properties
 *
 * @author Latest modification by $Author: cagatay_civici $
 * @version $Revision: 350 $ $Date: 2007-06-08 12:23:20 +0300 (Fri, 08 Jun 2007) $
 *
 * @goal inject-timestamp
 */
public class TimestampMojo extends AbstractMojo{

	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	protected MavenProject project;

	/**
	 * @parameter default-value="dd/MM/yyyy HH:mm:ss"
	 */
	protected String pattern;

	public void execute() throws MojoExecutionException, MojoFailureException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String now = format.format(new Date());

		project.getProperties().put("timestamp", now);
	}
}
