package com.authorizationserver.contants;

public class Permissions {
    public static final String ADMIN_CMS_USER_SEARCH = "admin.cms_user:search";
    public static final String ADMIN_CMS_USER_DETAIL = "admin.cms_user:detail";
    public static final String ADMIN_CMS_USER_KYC_DOC = "admin.cms_user:kyc_doc";
    public static final String ADMIN_CMS_USER_LOGIN_USER = "admin.cms_user:login_user";
    public static final String ADMIN_CMS_USER_ADD_LOGIN_USER_POST = "admin.cms_user:add_login_user.post";
    public static final String ADMIN_CMS_USER_RESEND_PW = "admin.cms_user:resend.pw";
    public static final String ADMIN_CMS_USER_RULE_INFO = "admin.cms_user:rule_info";
    public static final String ADMIN_CMS_USER_TRANSACTION_HISTORY_GET = "admin.cms_user:trans_history.get";
    public static final String ADMIN_CMS_USER_TRANSACTION_HISTORY_POST = "admin.cms_user:trans_history.post";

    public static final String ADMIN_CMS_USER_ECOM_SETTING = "admin.cms_user:ecom_setting";
    public static final String ADMIN_CMS_USER_UPDATE_ECOM = "admin.cms_user:update_ecom";

    public static final String ADMIN_GL_TRANSA_VIEW = "admin.gl_trans:view";


    public static final String ADMIN_ECOM_ORDER_VIEW = "admin.ecom_order:view";
    public static final String ADMIN_ECOM_ORDER_CREATE_PD_GET = "admin.ecom_order:create_pd.get";
    public static final String ADMIN_ECOM_ORDER_SHOW_CAPTURE = "admin.ecom_order:show_capture";
    public static final String ADMIN_ECOM_ORDER_EDIT_PARTNER_PROC_GET = "admin.ecom_order:edit_partner_proc.get";

    public static final String ADMIN_REPORT_VIEW = "admin.report:view";
    public static final String ADMIN_REPORT_CREATE_POST = "admin.report:create.post";
    public static final String ADMIN_REPORT_EDIT_GET = "admin.report:edit.get";
    public static final String ADMIN_REPORT_EDIT_POST = "admin.report:edit.post";
    public static final String ADMIN_REPORT_SEARCH = "admin.report:search";
    public static final String ADMIN_REPORT_DELETE = "admin.report:delete";

    public static final String ADMIN_DAILY_SUMMARY_VIEW = "admin.daily_summary:view";

    public static final String ADMIN_RECONCILE_EXCEPTION_VIEW = "admin.reconcile:exception.view";
    public static final String ADMIN_RECONCILE_EXCEPTION_SEARCH = "admin.reconcile:exception.search";
    public static final String ADMIN_RECONCILE_EXCEPTION_CREATE_POST = "admin.reconcile:exception.create.post";
    public static final String ADMIN_RECONCILE_EXCEPTION_EDIT_POST = "admin.reconcile:exception.edit.post";
    public static final String ADMIN_RECONCILE_EXCEPTION_DELETE = "admin.reconcile:exception.delete";

    public static final String ADMIN_PAYMENT_PARTNER_VIEW = "admin.partner_partner:view";

    public static final String ADMIN_PAYMENT_METHOD_EDIT = "admin.partner_method:edit.post";
    public static final String ADMIN_PARTNER_METHOD_DELETE = "admin.partner_method:delete";

    public static final String ADMIN_PAYMENT_OPTION_VIEW = "admin.partner_option:view";
    public static final String ADMIN_PAYMENT_OPTION_SEARCH = "admin.partner_option:search";
    public static final String ADMIN_PAYMENT_OPTION_DETAIL = "admin.partner_option:detail";
    public static final String ADMIN_PAYMENT_OPTION_CREATE_POST = "admin.partner_option:create.post";

    public static final String ADMIN_PAYMENT_METHOD_VIEW = "admin.partner_method:view";

    public static final String ADMIN_VIRTUAL_CARD_CREATE_FUND_POST = "admin.virtual_card:create_fund.post";

    public static final String ADMIN_MENU_VIEW = "admin.menu:view";
}