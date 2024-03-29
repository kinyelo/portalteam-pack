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

<script type="text/javascript">

var forms = null;
var form = null;
var formData = null;
var fields = null;
var fieldsMap = null;
var currentFormData = null;

jQuery(function() {
	jQuery('ul.tabs li:nth-child(3) ').addClass('current first');
   	PortalTeam.initJSONRpc(loadForms);
   	jQuery('#forms').change(onFormsChange);
   	jQuery('#back').click(onBack);
});

function loadForms() {
	PortalTeam.jsonrpc.formService.select(function(r,e) {
		if (PortalTeam.serviceFailed(e)) return;
		forms = r.list;
		showForms();
		if (forms.length > 0) {
			form = forms[0];
		}
		loadFormData();
	});
}

function showForms() {
	var h = '';
	jQuery.each(forms, function(i,form) {
		h += '<option value="' + i + '">' + form.title + '</option>';
	});
	jQuery('#forms').html(h);	
}

function loadFormData() {
	if (form == null) {
		formData = [];
		fields = [];
		showFormsData();
		return;
	}
	PortalTeam.jsonrpc.formService.selectFormData(function(r,e) {
		if (PortalTeam.serviceFailed(e)) return;
		formData = r.data.list;
		fields = r.fields.list;
		createFieldsMap();
		showFormsData();
	}, form.formId);
}

function createFieldsMap() {
	fieldsMap = {};
	jQuery.each(fields, function(i,value) {
		fieldsMap[value.name.toLowerCase()] = value;
	});
}

function showFormsData() {
	var h = '<table class="taglib-search-iterator">\
		<tr class="portlet-section-header results-header">';
	jQuery.each(fields, function(i, value) {
		if (value.type != 'FILE') {
			h += '<th>' + value.title + '</th>';		
		}
	});
	h += '<th></th></tr>';
	jQuery.each(formData, function(i, value) {
		h += '<tr class="results-row">';
		if (jQuery.browser.msie) {
            var data = new ActiveXObject('Microsoft.XMLDOM');
            data.async = false;
            data.loadXML(value.data);
            var formDataTag = data.getElementsByTagName('formData')[0];
            for (var j=0;j < formDataTag.childNodes.length; j++) {
                var node = formDataTag.childNodes[j];
                var field = fieldsMap[node.nodeName.toLowerCase()];
                if (field == undefined) {
                    return;
                }
                if (field.type != 'FILE') {
                    h += '<td><a href="#" onclick="onFormDataView(' + i +')">' 
                        + node.firstChild.nodeValue + '</a></td>';
                }
            }            
        } else {
            jQuery(value.data, 'formData').children().each(function() {
            	var field = fieldsMap[this.nodeName.toLowerCase()];
            	if (field == undefined) {
	                return;
	            }
		        if (field.type != 'FILE') {
				    h += '<td><a href="#" onclick="onFormDataView(' + i +')">' 
			    	    + jQuery(this).text() + '</a></td>';
		        }
		    });
        }
        h += '<td><a href="#" onclick="onFormDataRemove(' + i + ')">'
            + '<img src="/portalteam-portlet/images/02_x.png" /></a></td></tr>';
    });
	jQuery('#formsData').html(h + '</table>');
	jQuery('#formsData tr:even').addClass('alt');	
}

function onFormsChange() {
	form = forms[jQuery('#forms').val()];
	loadFormData();
}

function onFormDataView(i) {
	currentFormData = formData[i];
	var h = '<table width="50%"><tr><td width="40%"> </td><td> </td></tr>';
    if (jQuery.browser.msie) {
        var data = new ActiveXObject('Microsoft.XMLDOM');
        data.async = false;
        data.loadXML(currentFormData.data);
        var formDataTag = data.getElementsByTagName('formData')[0];
        for (var j=0;j < formDataTag.childNodes.length; j++) {
            var node = formDataTag.childNodes[j];
            var field = fieldsMap[node.nodeName.toLowerCase()];
            if (field == undefined) {
                return;
            }
            if (field.type != 'FILE') {
                h += '<tr><td>' + field.title + '</td><td>' 
                    + node.firstChild.nodeValue + '</td></tr>';
            }
        }
    }
    else { 
	    jQuery(currentFormData.data, 'formData').children().each(function() {
		    var field = fieldsMap[this.nodeName.toLowerCase()];
		    if (field == undefined) {
			    return;
		    }
		    if (field.type != 'FILE') {
			    h += '<tr><td>' + field.title + '</td><td>' 
				    + jQuery(this).text() + '</td></tr>';
		    }
     	});
    }
	jQuery('#fieldsData').html(h + '</table>');
	jQuery('#formsDataDiv').hide();
	jQuery('#formDiv').show();
	PortalTeam.jsonrpc.formService.getFormDataFiles(function(r,e) {
		var h = '<table width="50%"><tr><td width="40%"> </td><td> </td></tr>';
		jQuery.each(r.list, function(i, value) {
	        var field = fieldsMap[value.fieldName.toLowerCase()];
	        if (field == undefined) {
	            return;
	        }
			h += '<tr><td>' + field.title 
				+ '</td><td><a href="' + value.downloadUrl+ '">' + value.filename 
				+ '</a></td></tr>';
		});
		jQuery('#filesData').html(h + '</table>');
	}, currentFormData.formDataId);
}

function onBack() {
	jQuery('#formsDataDiv').show();
	jQuery('#formDiv').hide();
}

function onFormDataRemove(i) {
    if (confirm('Are you shure?')) {
        PortalTeam.jsonrpc.formService.removeFormData(function(r,e) {
            if (PortalTeam.serviceFailed(e)) return;
            PortalTeam.showServiceMessages('#messages', r);
            if (r.success) {
                formData.splice(i, 1);
                showFormsData();
            }
        }, formData[i].formDataId);
    }
}

</script>

<div id="tab">

<%@ include file="tab.jsp" %>

<div id="formsDataDiv">
    <p>Please select form</p>
    <select id="forms"></select>
    <div id="formsData" style="margin-top: 20px"></div>
</div>    

<div id="formDiv" style="display:none">
	<div id="fieldsData"></div>
	<div id="filesData"></div>
	<div class="buttons">
		<input id="back" type="button" value="Back to list"/>
	</div>
</div>

</div>

<div id="messages"> </div>
