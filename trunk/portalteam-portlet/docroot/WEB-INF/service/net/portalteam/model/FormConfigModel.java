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
 * <a href="FormConfigModel.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface FormConfigModel extends BaseModel<FormConfig> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getFormConfigId();

	public void setFormConfigId(long formConfigId);

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

	public boolean getEnabledRecaptcha();

	public boolean isEnabledRecaptcha();

	public void setEnabledRecaptcha(boolean enabledRecaptcha);

	public String getRecaptchaPublicKey();

	public void setRecaptchaPublicKey(String recaptchaPublicKey);

	public String getRecaptchaPrivateKey();

	public void setRecaptchaPrivateKey(String recaptchaPrivateKey);

	public FormConfig toEscapedModel();
}