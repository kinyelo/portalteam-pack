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

import java.util.List;
import java.util.Map;

import net.portalteam.model.Form;
import net.portalteam.model.FormConfig;
import net.portalteam.portlets.ServiceResponse;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public interface FormService {

	Form getById(long id) throws PortalException, SystemException;
	
	ServiceResponse save(Map<String, String> vo) 
		throws SystemException, PortalException;
	
	List<Form> select() throws SystemException;
	
	ServiceResponse remove(List<String> ids) 
		throws NumberFormatException, PortalException, SystemException;
	
	String getDefaultLetterTemplate();

	String getDefaultRenderTemplate();
	
	List<FieldVO> getFields(long formId);
	
	String renderForm(Form form) throws SystemException;
	
	FormConfig getConfig() throws SystemException;
	
	ServiceResponse saveConfig(Map<String, String> vo) throws SystemException;
	
	FormDataVO selectFormData(long formId) throws PortalException, SystemException;
	
	List<FormFileVO> getFormDataFiles(long formDataId) throws SystemException;
}
