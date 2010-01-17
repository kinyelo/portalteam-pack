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

package net.portalteam.service.impl;

import java.io.StringWriter;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class VelocityService {
	
	private static Log logger = LogFactoryUtil.getLog(VelocityService.class);

	private static VelocityEngine engine;
	
	public static void initEngine() {
		try {
			logger.debug("velocity init...");
			engine = new VelocityEngine();
			engine.setProperty( RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
					"org.apache.velocity.runtime.log.Log4JLogChute" );
		    engine.setProperty("runtime.log.logsystem.log4j.logger",
		    		VelocityService.class.getName());
			engine.init();
			logger.debug("done.");
		}
		catch (Exception e) {
			logger.error("Error. " + e.getMessage());
		}
	}
	
	private static VelocityEngine getEngine() {
		if (engine == null) {
			initEngine();
		}
		return engine;
	}
	
	public static String render(String template, VelocityContext context) {
		StringWriter wr = new StringWriter();
		String log = "info";
		try {
			logger.debug("render template...");
			getEngine().evaluate(context, wr, log, template);
			logger.debug("done.");
			return wr.toString();
		}
		catch (Exception e) {
			return e.getMessage();
		}
	}
}
