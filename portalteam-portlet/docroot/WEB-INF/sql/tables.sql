
create table PORTALTEAM_Form (
	formId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	createUserId LONG,
	modUserId LONG,
	companyId LONG,
	title VARCHAR(75) null,
	name VARCHAR(75) null,
	email VARCHAR(75) null,
	letterSubject VARCHAR(75) null,
	letterTemplate TEXT null,
	sendButtonTitle VARCHAR(75) null,
	resetButtonTitle VARCHAR(75) null,
	showResetButton BOOLEAN,
	captcha BOOLEAN,
	structure TEXT null,
	renderTemplate TEXT null
);

create table PORTALTEAM_FormConfig (
	formConfigId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	createUserId LONG,
	modUserId LONG,
	companyId LONG,
	enabledRecaptcha BOOLEAN,
	recaptchaPublicKey VARCHAR(75) null,
	recaptchaPrivateKey VARCHAR(75) null
);

create table PORTALTEAM_FormData (
	formDataId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	createUserId LONG,
	modUserId LONG,
	formId LONG,
	data_ TEXT null
);

create table PORTALTEAM_FormFile (
	formFileId LONG not null primary key,
	createDate DATE null,
	modifiedDate DATE null,
	createUserId LONG,
	modUserId LONG,
	formDataId LONG,
	fieldName VARCHAR(75) null,
	filename VARCHAR(75) null,
	mimeType VARCHAR(75) null,
	data_ TEXT null
);