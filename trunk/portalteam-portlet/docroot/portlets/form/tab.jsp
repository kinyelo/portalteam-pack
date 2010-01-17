<%
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
%>
<portlet:renderURL var="editUrl" windowState="maximized">
    <portlet:param name="jspPage" value="/portlets/form/edit.jsp"/>
</portlet:renderURL>
<portlet:renderURL var="formsDataUrl" windowState="maximized">
    <portlet:param name="jspPage" value="/portlets/form/formsData.jsp"/>
</portlet:renderURL>
<portlet:renderURL var="formsUrl" windowState="maximized">
    <portlet:param name="jspPage" value="/portlets/form/forms.jsp"/>
</portlet:renderURL>
<portlet:renderURL var="configUrl" windowState="maximized">
    <portlet:param name="jspPage" value="/portlets/form/config.jsp"/>
</portlet:renderURL>

<ul class="tabs ui-tabs">
    <li><a href="<%= editUrl %>">Form selection</a></li>
    <li><a href="<%= formsUrl %>">Forms</a></li>
    <li><a href="<%= formsDataUrl %>">Form data</a></li>
    <li><a href="<%= configUrl %>">Configuration</a></li>
</ul>
