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

var config = null;

jQuery(function() {
	jQuery('ul.tabs li:nth-child(4) ').addClass('current first');
    PortalTeam.initJSONRpc(loadConfig);
    jQuery('#save').click(onSave);
});

function loadConfig() {
    PortalTeam.jsonrpc.formService.getConfig(function(r,e) {
        if (PortalTeam.serviceFailed(e)) return;
        config = r;
        showConfig();        
    });
}

function showConfig() {
	jQuery('#enabledRecaptcha')[0].checked = config.enabledRecaptcha;
    jQuery('#recaptchaPublicKey').val(config.recaptchaPublicKey);
    jQuery('#recaptchaPrivateKey').val(config.recaptchaPrivateKey);
}

function onSave() {
	var vo = {
		enabledRecaptcha : String(jQuery('#enabledRecaptcha:checked').size() > 0),
		recaptchaPublicKey : jQuery('#recaptchaPublicKey').val(),
        recaptchaPrivateKey : jQuery('#recaptchaPrivateKey').val(),
	};
	PortalTeam.jsonrpc.formService.saveConfig(function(r,e) {
		if (PortalTeam.serviceFailed(e)) return;
		PortalTeam.showServiceMessages('#messages', r);
	}, PortalTeam.javaMap(vo));
}

</script>

<div id="tab">

<%@ include file="tab.jsp" %>

<div>

<div class="form">
    <div>
        <label>Enable reCaptcha</label>
        <input id="enabledRecaptcha" type="checkbox" />
    </div>
    <div>
        <label>reCaptcha public key</label>
        <input id="recaptchaPublicKey" type="text" />
    </div>
    <div>
        <label>reCaptcha private key</label>
        <input id="recaptchaPrivateKey" type="text" />
    </div>
    <div class="buttons">
        <input id ="save" type="button" value="Save" />
    </div>
</div>

</div>

</div>

<div id="messages"></div>