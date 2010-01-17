create index IX_5266448 on PORTALTEAM_Form (companyId);

create index IX_242FAFA6 on PORTALTEAM_FormConfig (companyId);

create index IX_9D9E6C71 on PORTALTEAM_FormData (companyId, formId);
create index IX_E140FADD on PORTALTEAM_FormData (formId);

create index IX_4E959DDF on PORTALTEAM_FormFile (companyId, formId);
create index IX_E01265D6 on PORTALTEAM_FormFile (companyId, formId, fieldName);
create index IX_55CCBFF9 on PORTALTEAM_FormFile (formDataId);
create index IX_607CCF7C on PORTALTEAM_FormFile (formDataId, fieldName);