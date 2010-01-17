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

package net.portalteam.portlets;

import java.util.ArrayList;
import java.util.List;

public class ServiceResponse {

	private boolean success;
	private List<String> messages;
	
	public static ServiceResponse createErrorResponse(final String msg) {
		return new ServiceResponse(false, msg);
	}
	
	public static ServiceResponse createErrorsResponse(final List<String> msgs) {
		return new ServiceResponse(false, msgs);
	}

	public static ServiceResponse createSuccessResponse(final String msg) {
		return new ServiceResponse(true, msg);
	}

	public ServiceResponse() {
		messages = new ArrayList<String>();
	}
	
	public ServiceResponse(boolean aSuccess, String message) {
		this();
		success = aSuccess;
		messages.add(message);
	}
	
	public ServiceResponse(boolean aSuccess, List<String> msgs) {
		this();
		success = aSuccess;
		messages = msgs;
	}

	public String getMessage() {
		return messages.size() > 0 ? messages.get(0) : "";
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setMessage(String message) {
		messages.add(message);
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}
