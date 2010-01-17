/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package net.portalteam.model;

import com.liferay.portal.model.BaseModel;

import java.util.Date;

/**
 * <a href="FormModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface FormModel extends BaseModel<Form> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getFormId();

	public void setFormId(long formId);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getCreateUserId();

	public void setCreateUserId(long createUserId);

	public long getModUserId();

	public void setModUserId(long modUserId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public String getTitle();

	public void setTitle(String title);

	public String getName();

	public void setName(String name);

	public String getEmail();

	public void setEmail(String email);

	public String getLetterSubject();

	public void setLetterSubject(String letterSubject);

	public String getLetterTemplate();

	public void setLetterTemplate(String letterTemplate);

	public String getSendButtonTitle();

	public void setSendButtonTitle(String sendButtonTitle);

	public String getResetButtonTitle();

	public void setResetButtonTitle(String resetButtonTitle);

	public boolean getShowResetButton();

	public boolean isShowResetButton();

	public void setShowResetButton(boolean showResetButton);

	public boolean getCaptcha();

	public boolean isCaptcha();

	public void setCaptcha(boolean captcha);

	public String getStructure();

	public void setStructure(String structure);

	public String getRenderTemplate();

	public void setRenderTemplate(String renderTemplate);

	public Form toEscapedModel();
}