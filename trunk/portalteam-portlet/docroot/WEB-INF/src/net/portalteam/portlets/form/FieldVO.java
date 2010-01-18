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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * Value object to be returned from services.
 * 
 * @author Alexander Oleynik
 */
public class FieldVO {

	private String title;
	private String name;
	private String comment;
	private String type;
	private boolean mandatory;
	private String values;
	private String defaultValue;
	private int height;
	private int width;
	
	public FieldVO(Element e) {
		title = e.elementText("title");
		name = e.elementText("name");
		comment = e.elementText("comment");
		type = e.elementText("type");
		mandatory = Boolean.valueOf(e.elementText("mandatory"));
		values = e.elementText("values");
		defaultValue = e.elementText("defaultValue");
		if (!StringUtils.isEmpty(e.elementText("height"))) {
			height = Integer.valueOf(e.elementText("height"));
		}
		if (!StringUtils.isEmpty(e.elementText("width"))) {
			width = Integer.valueOf(e.elementText("width"));
		}
	}

	public String getType() {
		return type;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public String getValues() {
		return values;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public String getComment() {
		return comment;
	}
	
	public boolean isHasComment() {
		return !StringUtils.isEmpty(comment);
	}

	public List<Option> getOptions() {
		if (StringUtils.isEmpty(getValues())) {
			return Collections.EMPTY_LIST;
		}
		try {
			List<Option> result = new ArrayList<Option>();
			String[] opts = getValues().split("\n");
			for (String opt : opts) {
				if (!StringUtils.isEmpty(opt)) {
					String val = opt.replace("*", "");
					boolean selected = opt.charAt(0) == '*';
					result.add(new Option(val, selected));
				}
			}
			return result;
		}
		catch (Exception e) {
			logger.error("getOptions error " + e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}


	private static Log logger = LogFactoryUtil.getLog(FieldVO.class);
	
	public static List<FieldVO> create(String xml) {
		List<FieldVO> result = new ArrayList<FieldVO>();
		Document doc;
		try {
			doc = DocumentHelper.parseText(xml);
			Iterator<Element> iter = doc.getRootElement().elementIterator();
			while (iter.hasNext()) {
				Element e = iter.next();
				if (e.getName().equals("field")) {
					result.add(new FieldVO(e));
				}
			}
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}
	
	public static class Option {
		private String value;
		private boolean selected;
		
		public Option(String value, boolean selected) {
			super();
			this.value = value;
			this.selected = selected;
		}

		public String getValue() {
			return value;
		}

		public boolean isSelected() {
			return selected;
		}
	}
	
	
}
