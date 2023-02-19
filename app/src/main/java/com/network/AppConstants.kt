package com.network

object AppConstants {

    init {
        System.loadLibrary("keys")
    }

    external fun getDevBaseUrl(): String
    external fun getDevApiKey(): String
    external fun getDevBearerAuthenticationToken(): String

    external fun getProdBaseUrl(): String
    external fun getProdApiKey(): String
    external fun getProdBearerAuthenticationToken(): String

    object AppConfig {
        const val APP_NAME      = "ViewBindingMVVMUsingKotlin"
        const val PACKAGE_NAME  = BuildConfig.APPLICATION_ID
        const val VERSION_CODE  = BuildConfig.VERSION_CODE
        const val VERSION_NAME  = BuildConfig.VERSION_NAME
        const val IS_DEBUG      = BuildConfig.BUILD_TYPE == "debug"
    }

    object AppSupport {
        const val LOCATION              = "Indore"
        const val MAIL                  = "support@gmail.com"
        const val MOBILE                = "917898680304"
        const val TERMS_AND_CONDITIONS  = "terms_and_conditions"
        const val PRIVACY_POLICY        = "privacy_policy"
    }

    class ApiInfo {
        object Development {
            val BASE_URL                    = getDevBaseUrl()
            val API_KEY                     = getDevApiKey()
            val BEARER_AUTHENTICATION_TOKEN = getDevBearerAuthenticationToken()
        }

        object Production {
            val BASE_URL                    = getProdBaseUrl()
            val API_KEY                     = getProdApiKey()
            val BEARER_AUTHENTICATION_TOKEN = getProdBearerAuthenticationToken()
        }
    }

    object Delay {
        const val SPLASH    = 1000 * 2
        const val API_CALL  = 1000 * 5 /* api call time, every 5 seconds api call*/
        const val SHIMMER   = 1000 * 2
    }

    object RequestCode {
        const val CAPTURE_IMAGE = 0x001
        const val CAPTURE_VIDEO = 0x002
    }

    object Extras {
        const val LATITUDE          = "LATITUDE"
        const val LONGITUDE         = "LONGITUDE"
        const val LAT_LON_ADDRESS   = "LAT_LON_ADDRESS"
    }

    class Screen {
        object Key {
            const val NAVIGATE_TO   = "navigate_to"
            const val TITLE         = "title"
            const val MESSAGE       = "message"
            const val ORDER_TYPE    = "order_type"
            const val ORDER_ID      = "order_id"
            const val DATA_REQUIRED = "data_required"
        }

        object Extras {
            const val NAVIGATE_TO   = "NAVIGATE_TO"
            const val TITLE         = "TITLE"
            const val MESSAGE       = "MESSAGE"
            const val ORDER_TYPE    = "ORDER_TYPE"
            const val ORDER_ID      = "ORDER_ID"
            const val DATA_REQUIRED = "DATA_REQUIRED"
        }
    }

    object SharedPreferences {
        const val SHARED_PREFERENCES_FILE_NAME  = AppConfig.APP_NAME + "SharedPrefs"

        const val CURRENT_THEME                 = "current_theme"

        const val IS_LANGUAGE_SELECT            = "is_language_select"
        const val CURRENT_LANGUAGE              = "current_language"

        const val IS_SHOW_APP_INTRO             = "is_show_app_intro"

        const val REMEMBER_ME                   = "remember_me"

        const val USER_ID                       = "id"
        const val OS                            = "os"
        const val PICTURE                       = "picture"
        const val NAME                          = "name"
        const val EMAIL                         = "email"
        const val PHONE_NUMBER                  = "phone_number"
        const val PASSWORD                      = "password"
        const val LATITUDE                      = "latitude"
        const val LONGITUDE                     = "longitude"
        const val LAT_LON_ADDRESS               = "lat_lon_address"
        const val CATEGORY_ID                   = "category_id"
        const val CATEGORY_NAME                 = "category_name"
        const val SUB_CATEGORY_ID               = "sub_category_id"
        const val SUB_CATEGORY_NAME             = "sub_category_name"
        const val ABOUT_YOU                     = "about_you"
        const val GALLERY                       = "gallery"
        const val AADHAAR_CARD_FRONT            = "aadhaar_card_front"
        const val AADHAAR_CARD_BACK             = "aadhaar_card_back"
        const val PAN_CARD                      = "pan_card"
        const val IS_ACTIVE                     = "is_active"
        const val IS_ONLINE                     = "is_online"
        const val CREDIT                        = "credit"
        const val FCM_TOKEN                     = "fcm_token"
        const val CREATED_AT                    = "created_at"
        const val UPDATED_AT                    = "updated_at"
    }

    object Database {
        const val SQLITE_DATABASE_FILE_NAME = AppConfig.APP_NAME + "SQLite.db"
        const val SQLITE_VERSION            = 1

        const val ROOM_DATABASE_FILE_NAME   = AppConfig.APP_NAME + "Room.db"
        const val ROOM_VERSION              = 1
    }

    object Firebase {
        /**
         * FCM Endpoint for sending messages.
         */
        const val FCM                       = "https://fcm.googleapis.com/fcm/send"
        const val AUTHORIZATION_KEY         = "Authorization"
        const val FIREBASE_SERVER_KEY       = BuildConfig.FIREBASE_SERVER_KEY
        const val AUTHORIZATION_KEY_VALUE   = "key=" + FIREBASE_SERVER_KEY
        const val CONTENT_TYPE_KEY          = "Content-Type"
        const val CONTENT_TYPE_VALUE_JSON   = "application/json"

        fun getFCMHeaders(): HashMap<String, String> {
            val headers = HashMap<String, String>()
            headers[AUTHORIZATION_KEY] = AUTHORIZATION_KEY_VALUE
            headers[CONTENT_TYPE_KEY] = CONTENT_TYPE_VALUE_JSON
            return headers
        }

        const val KYE_TO                    = "to"
        const val KYE_REGISTRATION_IDS      = "registration_ids"
        const val KYE_NOTIFICATION_PAYLOAD  = "notification"
        const val KYE_DATA_PAYLOAD          = "data"
    }

    object Chat {
        object OneToOne {
            const val SENDER_ID    = "sender_id"
            const val RECEIVER_ID  = "receiver_id"
            const val MESSAGE      = "message"
            const val MESSAGE_TYPE = "message_type"
            const val DATE         = "date"
            const val TIME         = "time"
            const val SEEN         = "seen"
        }

        object Group {
            const val SENDER_ID              = "sender_id"
            const val SENDER_NAME            = "sender_name"
            const val SENDER_PROFILE_PICTURE = "sender_profile_picture"
            const val MESSAGE                = "message"
            const val MESSAGE_TYPE           = "message_type"
            const val DATE                   = "date"
            const val TIME                   = "time"
            const val SEEN                   = "seen"
        }
    }

    object Call {
        object Voice {
            const val TOKEN    = "91bHHE7wL2_uyYmGdlke_1jazgVWVU0wlcOToqPMVTYDE_DJ5isTIbPnXm"
        }

        object Video {
            const val TOKEN    = "91bHHE7wL2_uyYmGdlke_1jazgVWVU0wlcOToqPMVTYDE_DJ5isTIbPnXm"
        }
    }

    object AssetsPath {
        const val LOTTIE_PATH  = "assets/lottie/"
    }
}
