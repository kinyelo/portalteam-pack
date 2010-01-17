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
<%@ include file="/portlets/init.jsp" %>
<%@ include file="bridge.jsp" %>

<portlet:renderURL var="formUrl" windowState="maximized">
    <portlet:param name="jspPage" value="/portlets/form/form.jsp"/>
</portlet:renderURL>

<script type="text/javascript">

var forms = null;

jQuery(function() {
	jQuery('ul.tabs li:nth-child(2) ').addClass('current first');
    PortalTeam.initJSONRpc(loadForms);
	jQuery('#addForm').click(onAddForm);
    jQuery('#deleteForm').click(onDeleteForm);
});

function onAddForm() {
    location.href = '<%= formUrl %>';	
}

function loadForms() {
	PortalTeam.jsonrpc.formService.select(function(r,e) {
		if (PortalTeam.serviceFailed(e)) return;
	    forms = r.list;
	    showForms();		
	});
}

function showForms() {
    var h = '<table class="taglib-search-iterator">\
        <tr class="portlet-section-header results-header">\
          <th></th><th>Title</th><th>Email</th></tr>';
	jQuery.each(forms, function(i,value) {
		h += '<tr class="results-row"><td><input type="checkbox" value="' 
		  + value.formId + '"/></td>'
		  + '<td><a href="#" onclick="onFormEdit(\'' + value.formId + '\')">' 
		  + value.title + '</a></td>'
          + '<td>' + value.email + '</td></tr>';
	});
	jQuery('#forms').html(h + '</table>');
	jQuery('#forms tr:even').addClass('alt');
}

function onFormEdit(id) {
    location.href = '<%= formUrl %>&formId=' + id;   
}

function onDeleteForm() {
    var ids = new Array();
    jQuery('#forms input:checked').each(function () {
        ids.push(this.value);
    });
    if (ids.length == 0) {
    	PortalTeam.showInfo('#messages', 'Nothing selected.');
        return;
    }
    if (confirm('Are you sure?')) {
    	PortalTeam.jsonrpc.formService.remove(function(r,e) {
            if (PortalTeam.serviceFailed(e)) return;
            PortalTeam.showServiceMessages('#messages', r);
            loadForms();
        }, PortalTeam.javaList(ids));
    }
}

</script>


<div id="tab">

<%@ include file="tab.jsp" %>

<div>
    <div id="forms"></div>
    <div class="buttons">
        <input id="addForm" type="button" value="Add form" />
        <input id="deleteForm" type="button" value="Delete" />
    </div>
</div>

</div>

<div id="messages"> </div>
