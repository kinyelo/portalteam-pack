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

import java.util.List;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.SystemException;

import net.portalteam.model.FormConfig;
import net.portalteam.service.base.FormConfigLocalServiceBaseImpl;

/**
 * <a href="FormConfigLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 *
 */
public class FormConfigLocalServiceImpl extends FormConfigLocalServiceBaseImpl {
	
	public FormConfig getFormConfigByCompany(long companyId) 
			throws SystemException {
		List<FormConfig> forms = formConfigPersistence.findByCompanyId(
				companyId);
		if (forms.isEmpty()) {
			FormConfig config = createFormConfig(
					CounterLocalServiceUtil.increment());
			config.setCompanyId(companyId);
			config.setRecaptchaPrivateKey("");
			config.setRecaptchaPublicKey("");
			updateFormConfig(config);
			return config;
		}
		return forms.get(0);
	}
	
	
}