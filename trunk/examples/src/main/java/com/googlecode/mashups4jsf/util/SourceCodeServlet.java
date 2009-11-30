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
package com.googlecode.mashups4jsf.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SourceCodeServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

        String webPage = req.getServletPath();

        webPage = chopGivenSuffix(webPage, ".source");

        String realPath = getServletConfig().getServletContext().getRealPath(webPage);
        outputFile(res, realPath);
    }

    private String chopGivenSuffix(String webPage, String suffix) {
        int chopPoint = webPage.lastIndexOf(suffix);

        return webPage.substring(0, chopPoint);
    }

    private void outputFile(HttpServletResponse res, String realPath) throws IOException {

        res.setContentType("text/plain");
        ServletOutputStream out = res.getOutputStream();

        InputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(realPath));
            int ch;
            while ((ch = in.read()) != -1) {
                out.print((char) ch);
            }
        } finally {
            if (in != null)
                in.close(); // very important
        }
    }
}