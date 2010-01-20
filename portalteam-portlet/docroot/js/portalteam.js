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

// Global PortalTeam namespace.

var PortalTeam = {

	javaList : function(array) {
		return {javaClass: 'java.util.ArrayList', list: array};
	},

	javaMap : function(aMap) {
		return {javaClass: 'java.util.HashMap', map: aMap};
	},

	urlFromTitle : function(title) {
    	return title.toLowerCase().replace(/\W/g, '-');
	},

	getParameter : function(param) {
    	var result =  window.location.search.match(
    			new RegExp("(\\?|&)" + param + "(\\[\\])?=([^&]*)")
    	);
    	return result ? result[3] : null;
	},

	identifier_regex : /^[a-zA-Z_][a-zA-Z0-9_]*$/,

	isValidIdentifier : function(s) {
		return PortalTeam.identifier_regex.test(s);
	},

	strip : function(s) {
		var i = 0;
		while (i < s.length && s[i] == ' ') i++;
		var s1 = s.substring(i);
		i = s1.length - 1;
		while (i >= 0 && s1[i] == ' ') i--;
		return s1.slice(0, i + 1);
	},

	//*********************** JSON-RPC ************************************

	/**
	 * Global JSON-RPC entry point.
	 */

	jsonrpc : '',
	jsonrpcListeners : [],
	jsonrpcInitialized : false,

	/**
	 * Don't call this function directly.
	 */
	createJSONRpc : function() {
		PortalTeam.jsonrpc = new JSONRpcClient(function(result, e) {
			if (e) {
				alert("Error during initializing JSON-RPC." + e);
			}
			else {
				while (PortalTeam.jsonrpcListeners.length > 0) {
					var func = PortalTeam.jsonrpcListeners.pop();
					func();
				}
			}
			PortalTeam.jsonrpcInitialized = true;
		}, '/portalteam-portlet/JSON-RPC/');
	},

	/**
	 * Global JSON-RPC entry point initialization.
	 * @param func - optional callback to run after successful initialization.
	 * @return
	 */
	initJSONRpc : function(func) {
		if (func == undefined) {
			return;
		}
		if (PortalTeam.jsonrpcInitialized) {
			func();
		} else {
			PortalTeam.jsonrpcListeners.push(func);
		}
	},

	serviceFailed : function(e) {
		if (e != null) {
			alert('Can\'t connect to server. ' + e + ' '+ e.message);
			return true;
		}
		return false;
	},

	/************************ UI ***************************/ 

	initTabs : function(tab) {
		jQuery(tab + ' > ul.tabs > li').click(function() {
			jQuery(this).siblings().removeClass('current');
			jQuery(this).addClass('current');
			jQuery(tab + '> div').hide();
			var n = jQuery(tab + ' > ul.tabs > li').index(this);
			jQuery(tab + '> div').slice(n, n+1).fadeIn();
		});
	},

	/************************ Messages ***************************/ 

	showErrors : function(id, msgs) {
		var h = '<ul>';
		jQuery.each(msgs, function(i,value) {
			h += '<li class="errorMessage">' + value + '</li>';
		});
		jQuery(id).html(h + '</ul>');
		jQuery(id).show();
		setTimeout(function() {
			jQuery(id).fadeOut();
		}, 30000);
	},

	showError : function(id, msg) {
		jQuery(id).html('<ul><li class="errorMessage">' + msg + '</li></ul>');
		jQuery(id).show();
		setTimeout(function() {
			jQuery(id).fadeOut();
		}, 30000);
	},

	showInfo : function(id, msg) {
		jQuery(id).html('<ul><li class="infoMessage">' + msg + '</li></ul>');
		jQuery(id).show();
		setTimeout(function() {
			jQuery(id).fadeOut();
		}, 10000);
	},

	showServiceMessages : function(id, r) {
		if (r.success) {
			PortalTeam.showInfo(id, r.message);
		}
		else {
			PortalTeam.showErrors(id, r.messages.list);
		}
	}

};

PortalTeam.createJSONRpc();
