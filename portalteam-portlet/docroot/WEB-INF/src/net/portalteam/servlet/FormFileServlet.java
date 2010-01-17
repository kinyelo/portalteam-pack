/**
 * Copyright (c) 2010 PortalTeam.net All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.portalteam.servlet;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.portalteam.model.FormFile;
import net.portalteam.service.FormFileLocalServiceUtil;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Servlet used for streaming uploaded files.
 * 
 * @author Alexander Oleynik
 */
public class FormFileServlet extends HttpServlet {

	private static final long serialVersionUID = -4565332785746278717L;

	private static final Log logger = LogFactoryUtil.getLog(FormFileServlet.class);
	
	public FormFileServlet() {
		super();
	}
	
	public void init() throws ServletException {
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		FormFile entry;
		try {
			entry = FormFileLocalServiceUtil.getFormFile(id);
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition","attachment;filename=" 
					+ entry.getFilename());
			response.setContentLength(entry.getDataObj().length);
			OutputStream stream = response.getOutputStream();
			stream.write(entry.getDataObj());
			stream.flush();
			stream.close();
		} catch (PortalException e) {
			logger.error(e.getMessage());
		} catch (SystemException e) {
			logger.error(e.getMessage());
		}
	}
	
}
