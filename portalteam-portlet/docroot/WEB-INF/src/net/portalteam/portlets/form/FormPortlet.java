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

package net.portalteam.portlets.form;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import net.portalteam.model.Form;
import net.portalteam.service.FormLocalServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.jsp.JSPPortlet;

public class FormPortlet extends JSPPortlet {

	private static Log logger = LogFactoryUtil.getLog(FormPortlet.class); 
	
	public void onFormSave(ActionRequest actionRequest, 
			ActionResponse actionResponse) {
		String formId = actionRequest.getParameter("forms");
		try {
			Form form = FormLocalServiceUtil.getForm(Long.valueOf(formId));
			if (form != null) {
				actionRequest.getPreferences().setValue("formId", formId);
				actionRequest.getPreferences().store();
			}
		}
		catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
}
