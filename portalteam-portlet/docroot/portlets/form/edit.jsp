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
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ include file="/portlets/init.jsp" %>
<%@ include file="bridge.jsp" %>

<%
	String formId = preferences.getValue("formId","");
%>

<portlet:actionURL var="submitFormUrl" portletMode="view" windowState="normal">
    <portlet:param name="<%= ActionRequest.ACTION_NAME %>" 
        value="onFormSave"/>
</portlet:actionURL>

<script type="text/javascript">

var forms = null;

jQuery(function() {
	jQuery('ul.tabs li:nth-child(1) ').addClass('current first');
	PortalTeam.initJSONRpc(loadForms);
});

function loadForms() {
	PortalTeam.jsonrpc.formService.select(function(r,e) {
        if (PortalTeam.serviceFailed(e)) return;
        forms = r.list;
        showForms();        
    });
}

function showForms() {
    var h = '';
    jQuery.each(forms, function(i,value) {
        var sel = '';
        if (value.formId == '<%= formId %>') {
            sel = 'selected="selected"';
        }
        h += '<option ' + sel + ' value="' + value.formId + '">' 
            + value.title + '</option>';
    });
    jQuery('#forms').html(h);
}

</script>

<div id="tab">

<%@ include file="tab.jsp" %>

<div>
<form method="POST" action="<%= submitFormUrl %>">
    <p>Please select form.</p>
    <select id="forms" name="forms"></select>
    <div class="buttons">
        <input id ="save" type="submit" value="Save" />
    </div>
</form>       
</div>    

</div>

<div id="messages"> </div>
