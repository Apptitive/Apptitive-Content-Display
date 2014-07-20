package com.apptitive.content_display.utilities;

/**
 * Created by Sharif on 5/28/2014.
 */
public final class Constants {
    public static final String DEFAULT_REGION = "Dhaka";
    public static final String PREF_KEY_SYNC_SETTINGS= "pref_key_sync_settings";
    public static final String PREF_KEY_LOCATION = "prep_key_location";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_HOUR_MINUTE_SECOND = "dd/MM/yyyy HH:mm:ss aa";
    public static final String DATE_FORMAT_12_HOUR = "dd-MM-yyyy hh:mm aa";
    public static final String IS_DB_CREATED = "key_db_creation";
    public static final String PREF_ALARM_DATE = "key_alarm_date";
    public static final String PREF_SWITCH_IFTAR="pref_switch_iftar";
    public static final String PREF_SWITCH_SEHRI="pref_switch_sehri";
    public static final int IFTAR_REQUEST_CODE=1;
    public static final int SEHRI_REQUEST_CODE=2;

    public static final String IFTAR_ROW_POSITION="iftar_row_position";
    public static final String SEHRI_ROW_POSITION="sehri_row_position";

    public static final int MENU_REQUEST_CODE=1;
    public static final int CONTENT_REQUEST_CODE =2;

    public static final String AUTHORITY = "com.apptitive.content_display.sync.StubProvider";
    // An account type, in the form of a domain name
    public static final String ACCOUNT_TYPE = "apptitive.com";
    // The account name
    public static final String ACCOUNT_NAME = "dummyaccount";

    public final class menu {
        public static final String EXTRA_MENU_ID = "_menuId";
        public static final String EXTRA_MENU_TITLE = "_menuTitle";
        public static final String EXTRA_ICON_ID = "_iconId";
    }

    public final class content {
        public static final String EXTRA_CONTENT = "_content";

        public final class view {
            public static final String TYPE_NATIVE = "native";
            public static final String TYPE_HTML = "html";
        }
    }

    public final class detail {
        public static final int VIEW_TYPE_TEXT_ONLY = 0;
        public static final int VIEW_TYPE_BULLET = 1;
        public static final int VIEW_TYPE_HEADER_ONLY = 2;
        public static final int VIEW_TYPE_TEXT_BULLET_ALIGN = 3;
        public static final int VIEW_TYPE_ARABIC = 4;
        public static final int VIEW_TYPE_ARABIC_BULLET_ALIGN = 5;
    }

    public final class ContentType{
        public static final String ADD = "add";
        public static final String DELETE = "delete";
        public static final String UPDATE = "update";
    }
}
