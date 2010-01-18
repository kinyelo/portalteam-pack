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

<%@ page import="net.portalteam.portlets.form.FormService" %>
<%@ page import="net.portalteam.portlets.form.FormServiceImpl" %>

<portlet:defineObjects />
<liferay-theme:defineObjects />

<portlet:renderURL var="formsUrl" windowState="maximized">
    <portlet:param name="jspPage" value="/portlets/form/forms.jsp"/>
</portlet:renderURL>

<jsp:useBean id="JSONRPCBridge" scope="session"
   class="org.jabsorb.JSONRPCBridge" />

<%
	FormService formService = new FormServiceImpl(themeDisplay); 
	JSONRPCBridge.registerObject("formService", formService);
%>

<script type="text/javascript">

var formId = null;
var editMode = false;
var form = null;
var templateEditorSave = null;
var structureChanged = false;
var field = null;
var fieldIndex = null;
var fields = [];

jQuery(function() {
    formId = PortalTeam.getParameter('formId');
    PortalTeam.initJSONRpc(loadData);

    PortalTeam.initTabs('#tab');
    jQuery('#template-editor').dialog({width:600, height:460, autoOpen:false});
    jQuery("#field-dialog").dialog({ width :500, height: 'auto', autoOpen :false });

    jQuery('#cancel').click(onCancel);
    jQuery('#save').click(onSave);
    jQuery('#saveFields').click(onSave);
    jQuery('#editLetterTemplate').click(onEditLetterTemplate);
    jQuery('#editRenderTemplate').click(onEditRenderTemplate);
    jQuery('#editStructureXML').click(onEditStructureXML);
    jQuery('#restoreLetterTemplate').click(onRestoreLetterTemplate);
    jQuery('#restoreRenderTemplate').click(onRestoreRenderTemplate);

    jQuery('#templateSave').click(onTemplateEditorSave);
    jQuery('#templateCancel').click(onTemplateEditorCancel);

    jQuery('#addField').click(onAddField);   
    jQuery('#fieldType').change(onFieldTypeChange);
    jQuery('#saveAndAddButton').click(onSaveAndAdd);
    jQuery('#fieldSaveButton').click(function() { onFieldSave(true); });
    jQuery('#fieldCancelButton').click(onFieldCancel);
    jQuery('input[name=field.title]').change(onFieldTitleChange);
    jQuery('#title').change(onTitleChange);
});

function loadData() {
	loadForm();
	if (formId != null) {
		loadFields();
	}
}

function loadForm() {
    editMode = formId != null;
	if (formId != null) {
		PortalTeam.jsonrpc.formService.getById(function(r,e) {
	        if (PortalTeam.serviceFailed(e)) return;
		    form = r;
		    showForm();
	    }, Number(formId));
	}
	else {
		showForm();
	}
}

function showForm() {
	if (formId != null) {
		jQuery('#title').val(form.title);
        jQuery('#name').val(form.name);
        jQuery('#email').val(form.email);
        jQuery('#letterSubject').val(form.letterSubject);
        jQuery('#sendButtonTitle').val(form.sendButtonTitle);
        jQuery('#resetButtonTitle').val(form.resetButtonTitle);
        jQuery('#showResetButton').each(function() {this.checked = form.showResetButton});
        jQuery('#captcha').each(function() {this.checked = form.captcha});
	}
	else {
        jQuery('#title').val('');
        jQuery('#name').val('');
        jQuery('#email').val('');
        jQuery('#letterSubject').val('');
        jQuery('#sendButtonTitle').val('');
        jQuery('#resetButtonTitle').val('');
        jQuery('#showResetButton').each(function() {this.checked = false});
        jQuery('#captcha').each(function() {this.checked = false});
	}
	if (editMode) {
		jQuery('#fieldsTab, #templatesTab').show();
	}
	else {
        jQuery('#fieldsTab, #templatesTab').hide();
	}
}

function onCancel() {
	location.href = '<%= formsUrl %>';
}

function validateForm(form) {
	var errors = [];
	if (form.title == '') {
		errors.push('Title is empty');
	}
    if (form.name == '') {
        errors.push('Identifier is empty');
    }
    if (!PortalTeam.isValidIdentifier(form.name)) {
        errors.push('Identifier is not valid. Must have alpha numeric characters only.');
    }
	return errors;
}

function onTitleChange() {
    if (editMode) {
        return;
    }
    var name = jQuery("#name").val();
    var title = jQuery("#title").val();
    if (name == '') {
        jQuery("#name").val(PortalTeam.urlFromTitle(title));
    }
}

function onSave() {
    var structure = '', letterTemplate = '', renderTemplate = '';
    if (editMode) {
        structure = form.structure;
        letterTemplate = form.letterTemplate;
        renderTemplate = form.renderTemplate;
    }
    var vo = {
    	id : formId != null ? formId : '',
    	title : jQuery('#title').val(),
        name : jQuery('#name').val(),
    	email : jQuery('#email').val(),
    	letterSubject : jQuery('#letterSubject').val(),
        sendButtonTitle : jQuery('#sendButtonTitle').val(),
        resetButtonTitle : jQuery('#resetButtonTitle').val(),
        showResetButton : String(jQuery('#showResetButton:checked').size() > 0),
        captcha : String(jQuery('#captcha:checked').size() > 0),
        structure : structure,
        letterTemplate : letterTemplate,
        renderTemplate : renderTemplate,
    };
    var errors = validateForm(vo);
    if (errors.length == 0) {
    	PortalTeam.jsonrpc.formService.save(function(r,e) {
            if (PortalTeam.serviceFailed(e)) return;
            if (r.success) {
            	PortalTeam.showInfo('#messages', 'Form was successfully saved.');
                if (editMode) {
                    location.href = '<%= formsUrl %>';
                }
                else {
                    formId = r.message;
                    loadForm();
                }    
            }
            else {
            	PortalTeam.showErrors('#messages', r.messages);
            }
        }, PortalTeam.javaMap(vo));
    }
    else {
    	PortalTeam.showErrors('#messages', errors); 
    }    	
}

function onEditStructureXML() {
	jQuery('#template').val(form.structure);
	jQuery('#template-editor').show().dialog("open");
	templateEditorSave = function(value) {
		form.structure = value;
		structureChanged = true;
	}
}

function onEditLetterTemplate() {
    jQuery('#template').val(form.letterTemplate);
    jQuery('#template-editor').show().dialog("open");
    templateEditorSave = function(value) {form.letterTemplate = value}
}

function onEditRenderTemplate() {
    jQuery('#template').val(form.renderTemplate);
    jQuery('#template-editor').show().dialog("open");
    templateEditorSave = function(value) {form.renderTemplate = value}
}

function onTemplateEditorSave() {
	templateEditorSave(jQuery('#template').val());
    jQuery('#template-editor').hide().dialog('close');
}

function onTemplateEditorCancel() {
	jQuery('#template-editor').hide().dialog('close');
}

function onRestoreLetterTemplate() {
	onEditLetterTemplate();
	PortalTeam.jsonrpc.formService.getDefaultLetterTemplate(function(r,e) {
		if (PortalTeam.serviceFailed(e)) return;
	    jQuery('#template').val(r);
	});
}

function onRestoreRenderTemplate() {
    onEditRenderTemplate();
    PortalTeam.jsonrpc.formService.getDefaultRenderTemplate(function(r,e) {
        if (PortalTeam.serviceFailed(e)) return;
        jQuery('#template').val(r);
    });
}

// ************************* Field ********************************

function loadFields() {
    if (formId == null) {
        return;
    }
    PortalTeam.jsonrpc.formService.getFields(function(r, e) {
        if (PortalTeam.serviceFailed(e)) return;
        fields = r.list;
        showFields();
    }, formId);
}

function showFields() {
    var h = '<table class="taglib-search-iterator">\
        <tr class="portlet-section-header results-header">\
        <th>Title</th><th>Name</th><th>Type</th><th></th></tr>';
    jQuery.each(fields, function(i, field) {
        h += 
            '<tr class="results-row">\
            <td><a href="#" onclick="onFieldEdit(\'' + i + '\')">'   + field.title + '</a></td>\
            <td>' + field.name + '</td>\
            <td>'   + fieldTypeString(field.type) + '</td>\
            <td><a href="#" onclick="onFieldUp(' + i+ ')">\
                <img src="/portalteam-portlet/images/02_up.png"/></a>\
                <a href="#" onclick="onFieldDown(' + i + ')">\
                <img src="/portalteam-portlet/images/02_down.png"/></a>\
                <a href="#" onclick="onFieldDelete(' + i + ')">\
                <img src="/portalteam-portlet/images/02_x.png"/></a>\
            </td>\
            </tr>';
    });
    jQuery('#fields').html(h + '</table>');
    jQuery('#fields tr:even').addClass('alt');
}

function fieldTypeString(v) {
    if (v == 'TEXT') return 'Text';
    if (v == 'CHECKBOX') return 'Checkbox';
    if (v == 'RADIO') return 'Radiobox';
    if (v == 'PASSWORD') return 'Password';
    if (v == 'LISTBOX') return 'Listbox';
    if (v == 'FILE') return 'File upload';
    return 'undefined';
}

function onAddField() {
    field = null;
    fieldIndex = null;
    fieldDialogInit();
    jQuery("#field-dialog").show().dialog("open");
}

function onFieldCancel() {
	jQuery("#field-dialog").hide().dialog("close");
}

function onFieldSave(closeFlag) {
    var fieldVO = createFieldVO();
    var errors = validateField(fieldVO);
    if (errors.length == 0) {
        if (field == null) {
            fields.push(fieldVO);
            field = fieldVO;
            fieldIndex = fields.length - 1;
        }
        else {
            fields[fieldIndex] = fieldVO;
        }        
        if (closeFlag) {
            jQuery('#field-dialog').hide().dialog('close');
        }
        showFields();
        createStructure();
    } else {
        fieldErrorMessages(errors);
    }
}

function onFieldTypeChange() {
    fieldDialogShowInputs();
}

function fieldDialogShowInputs() {
    var fieldType = jQuery('select[name=field.fieldType]').val();
    if (fieldType == 'TEXT') {
    	jQuery('#field-values').hide();
    	jQuery('#field-height').show();
    	jQuery('#field-width').show();
    	jQuery('#field-defaultValue').show();
        jQuery('#field-width-label').show();
        jQuery('#field-size-label').hide();
    }
    if (fieldType == 'LISTBOX') {
    	jQuery('#field-values').show();
    	jQuery('#field-height').hide();
    	jQuery('#field-width').hide();
    	jQuery('#field-defaultValue').hide();
        jQuery('#field-width-label').show();
        jQuery('#field-size-label').hide();
    }
    if (fieldType == 'CHECKBOX') {
    	jQuery('#field-values').show();
    	jQuery('#field-height').hide();
    	jQuery('#field-width').hide();
    	jQuery('#field-defaultValue').hide();
        jQuery('#field-width-label').show();
        jQuery('#field-size-label').hide();
    }
    if (fieldType == 'RADIO') {
    	jQuery('#field-values').show();
    	jQuery('#field-height').hide();
    	jQuery('#field-width').hide();
    	jQuery('#field-defaultValue').hide();
        jQuery('#field-width-label').show();
        jQuery('#field-size-label').hide();
    }
    if (fieldType == 'PASSWORD') {
    	jQuery('#field-values').hide();
    	jQuery('#field-height').hide();
    	jQuery('#field-width').show();
    	jQuery('#field-defaultValue').hide();
        jQuery('#field-width-label').show();
        jQuery('#field-size-label').hide();
    }
    if (fieldType == 'FILE') {
    	jQuery('#field-values').hide();
    	jQuery('#field-height').hide();
    	jQuery('#field-width').show();
    	jQuery('#field-defaultValue').hide();
        jQuery('#field-width-label').hide();
        jQuery('#field-size-label').show();
    }
}

function fieldDialogInit() {
    if (field == null) {
    	jQuery('input[name=field.name]').val('');
    	jQuery('input[name=field.title]').val('');
        jQuery('input[name=field.comment]').val('');
    	jQuery('select[name=field.fieldType]').val('TEXT');
    	jQuery('textarea[name=field.values]').val('');
    	jQuery('input[name=field.defaultValue]').val('');
    	jQuery('input[name=field.height]').val('1');
    	jQuery('input[name=field.width]').val('20');
    	jQuery('input[name=field.mandatory]')[0].checked = false;
    } else {
    	jQuery('input[name=field.name]').val(field.name);
    	jQuery('input[name=field.title]').val(field.title);
        jQuery('input[name=field.comment]').val(field.comment);
    	jQuery('select[name=field.fieldType]').val(field.type);
    	jQuery('textarea[name=field.values]').val(field.values);
    	jQuery('input[name=field.defaultValue]').val(field.defaultValue);
    	jQuery('input[name=field.height]').val(field.height);
    	jQuery('input[name=field.width]').val(field.width);
    	jQuery('input[name=field.mandatory]')[0].checked = field.mandatory;
    }
    fieldDialogShowInputs();
}

function createFieldVO() {
    return {
        name : jQuery('input[name=field.name]').val(),
        title : jQuery('input[name=field.title]').val(),
        comment : jQuery('input[name=field.comment]').val(),
        type : jQuery('select[name=field.fieldType]').val(),
        values : jQuery('textarea[name=field.values]').val(),
        defaultValue : jQuery('input[name=field.defaultValue]').val(),
        height : Number(jQuery('input[name=field.height]').val()),
        width : Number(jQuery('input[name=field.width]').val()),
        mandatory : jQuery('input[name=field.mandatory]:checked').size() > 0
    };
}

function validateField(vo) {
    var errors = new Array();
    if (vo.name == '') {
        errors.push('Name is empty');
    }
    if (vo.title == '') {
        errors.push('Title is empty');
    }
    if (vo.type == 'TEXT' && vo.height <= 0) {
        errors.push('Height can\'t be less or zero');
    }
    if (vo.fieldType == 'TEXT' && vo.width <= 0) {
        errors.push('Width can\'t be less or zero');
    }
    return errors;
}

function fieldInfoMessage(message) {
	PortalTeam.showInfo('#field-messages', message);
}

function fieldErrorMessages(messages) {
	PortalTeam.showErrors('#field-messages', messages);
}

function fieldErrorMessage(message) {
	PortalTeam.showError('#field-messages', message);
}

function clearFieldMessage() {
	jQuery('#field-messages').html('');
}

function onFieldEdit(i) {
    clearFieldMessage();
    field = fields[i];
    fieldIndex = i;
    fieldDialogInit();
    jQuery("#field-dialog").show().dialog("open");
}

function onFieldDelete(i) {
	if (confirm('Are you shure?')) {
		fields.splice(i, 1);
		showFields();
		createStructure();
	}
}

function onSaveAndAdd() {
    onFieldSave(false);
    onAddField();
}

function onFieldTitleChange() {
    if (field != null) {
        return;
    }
    var name = jQuery('input[name=field.name]').val();
    var title = jQuery('input[name=field.title]').val();
    if (name == '') {
    	jQuery('input[name=field.name]').val(PortalTeam.urlFromTitle(title));
    }
}

function onFieldUp(i) {
    if (i - 1 >= 0) {
        swapFields(i, i - 1);
        showFields();
        createStructure();
    }
}

function onFieldDown(i) {
    if (i + 1 < fields.length) {
        swapFields(i, i + 1);
        showFields();
        createStructure();
    }
}

function swapFields(i, j) {
    var tmp = fields[j];
    fields[j] = fields[i];
    fields[i] = tmp;
}

function createStructure() {
	var xml = '<fields>\n';
	jQuery.each(fields, function(i,field) {
		xml += '    <field>\n'
			+ '        <title>' + field.title + '</title>\n'
			+ '        <name>' + field.name + '</name>\n'
            + '        <comment>' + field.comment + '</comment>\n'
            + '        <type>' + field.type + '</type>\n'
            + '        <mandatory>' + String(field.mandatory) + '</mandatory>\n'
            + '        <values>' + field.values + '</values>\n'
            + '        <defaultValue>' + field.defaultValue + '</defaultValue>\n'
            + '        <height>' + field.height + '</height>\n'
            + '        <width>' + field.width + '</width>\n'
            + '    </field>\n';
	});
	form.structure = xml + '</fields>\n';
}

</script>


<div id="tab">

<ul class="tabs ui-tabs">
    <li class="current first"><a href="#">Form</a></li>
    <li id="fieldsTab"><a href="#">Fields</a></li>
</ul>

<div class="form">
    <div>
        <label>Form title</label>
        <input id="title" type="text" />
    </div>
    <div>
        <label>Form unique identifier</label>
        <input id="name" type="text" />
    </div>
    <div>
        <label>Email</label>
        <input id="email" type="text" />
    </div>
    <div>
        <label>Letter subject</label>
        <input id="letterSubject" type="text" size="60"/>
    </div>
    <div>
        <label>"Send" button title</label>
        <input id="sendButtonTitle" type="text" />
    </div>
    <div>
        <label>"Reset" button title</label>
        <input id="resetButtonTitle" type="text" />
    </div>
    <div>
        <label>Show "Reset" button</label>
        <input id="showResetButton" type="checkbox" />
    </div>
    <div>
        <label>Enable captcha</label>
        <input id="captcha" type="checkbox" />
    </div>
    <div>
        <label>Letter template</label>
        <a id="editLetterTemplate" href="#">Edit</a> 
        <a id="restoreLetterTemplate" href="#">Restore</a>
    </div>
    <div>
        <label>Render template</label>
        <a id="editRenderTemplate" href="#">Edit</a> 
        <a id="restoreRenderTemplate" href="#">Restore</a>
    </div>
    <div>
        <label>Structure XML</label>
        <a id="editStructureXML" href="#">Edit</a>
    </div>
    <div class="buttons">
        <input id ="save" type="button" value="Save" />
        <input id ="cancel" type="button" value="Cancel" />
    </div>
</div>

<div style="display:none">
    <div id="fields"></div>
    <div class="buttons">
        <input id ="addField" type="button" value="Add field" />
        <input id ="saveFields" type="button" value="Save" />
    </div>
</div>

</div>

<div id="messages"> </div>

<div id="template-editor" style="display:none" title="Template editor">
    <textarea id="template" cols="80" rows="25"></textarea>
    <div class="buttons">
        <input id ="templateSave" type="button" value="Change" />
        <input id ="templateCancel" type="button" value="Cancel" />
    </div>
</div>

<div id="field-dialog" style="display:none" title="Field details">
    <div id="field-messages"> </div>
    <div class="form">
    <div>
        <label>Title</label>
        <input name="field.title" type="text" />
    </div>
    <div>
        <label>Unique name</label>
        <input name="field.name" type="text" />
    </div>
    <div>
        <label>Comment</label>
        <input name="field.comment" type="text" size="20" />
    </div>
    <div>
        <label>Field type</label>
        <select id="fieldType" name="field.fieldType">
            <option value="TEXT">Text</option>
            <option value="CHECKBOX">Checkbox</option>
            <option value="RADIO">Radiobox</option>
            <option value="PASSWORD">Password</option>
            <option value="LISTBOX">Listbox</option>
            <option value="FILE">File upload</option>
        </select>
    </div>
    <div id="field-width">
        <label id="field-width-label">Width in chars</label>
        <label id="field-size-label">Maximum allowed size in MB</label>
        <input type="text" name="field.width" />
    </div>
    <div id="field-height">
        <label>Height in chars</label>
        <input type="text" name="field.height" />
    </div>
    <div>
        <label>Mandatory</label>
        <input type="checkbox" name="field.mandatory" />
    </div>
    <div id="field-values">
        <label>Values (one item per line with * for selection) example:<br/>
            Toyota<br/>*Nissan<br/>Subaru</label>
        <textarea name="field.values" cols="30" rows="5"></textarea>
    </div>
    <div id="field-defaultValue">
        <label>Default value</label>
        <input type="text" name="field.defaultValue"/>
    </div>
    <div class="buttons">
        <input id="saveAndAddButton" type="button" value="Save and Add" />
        <input id="fieldSaveButton" type="button" value="Save" />
        <input id="fieldCancelButton" type="button" value="Cancel" />
    </div>
    </div>
</div>

