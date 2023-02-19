package com.network.data.remote.config

import com.network.AppConstants

object ApiConfiguration {

    /*
    * Note :  We can set timeouts settings on the underlying HTTP client.
    * If we don’t specify a client, Retrofit will create one with default connect and read timeouts.
    * By default, Retrofit uses the following timeouts :
    *                                                      Connection timeout: 10 seconds
    *                                                      Read timeout: 10 seconds
    *                                                      Write timeout: 10 seconds
    */
    const val CUSTOM_HTTP_CONNECT_TIMEOUT_IN_SECONDS            = 60 /* 60 seconds */
    const val CUSTOM_HTTP_WRITE_TIMEOUT_IN_SECONDS              = 30 /* 30 seconds */
    const val CUSTOM_HTTP_READ_TIMEOUT_IN_SECONDS               = 15 /* 15 seconds */

    const val CUSTOM_OK_HTTP_CACHE_DIR_NAME                     = "customOkHttpClientCache"
    const val CUSTOM_OK_HTTP_CACHE_SIZE                         = (10 * 1024 * 1024 /* 10 MB Cache size */).toLong()

    const val CUSTOM_CACHE_DURATION_WITH_NETWORK_IN_SECONDS     = 10
    const val CUSTOM_CACHE_DURATION_WITHOUT_NETWORK_IN_SECONDS  = 14 * 24 * 60 * 60 /* Expired in two week. */

    val BASE_URL                    = if (AppConstants.AppConfig.IS_DEBUG) AppConstants.ApiInfo.Development.BASE_URL else AppConstants.ApiInfo.Production.BASE_URL
    val API_KEY                     = if (AppConstants.AppConfig.IS_DEBUG) AppConstants.ApiInfo.Development.API_KEY else AppConstants.ApiInfo.Production.API_KEY
    val BEARER_AUTHENTICATION_TOKEN = if (AppConstants.AppConfig.IS_DEBUG) AppConstants.ApiInfo.Development.BEARER_AUTHENTICATION_TOKEN else AppConstants.ApiInfo.Production.BEARER_AUTHENTICATION_TOKEN

    /*
     * End points
     */
    const val EMPLOYEE              = "Json/employee.json"
    const val EMPLOYEE_LIST         = "Json/employeeList.json"

    const val UPLOAD                = "UploadFile/upload_multipart_file.php"

    const val SIGN_UP               = "RestApi/middleware/AppSignUp.php"
    const val SIGN_IN               = "RestApi/middleware/SignIn.php"
    const val SOCIAL_SIGN_IN        = "RestApi/middleware/SocialSignIn.php"
    const val GET_USERS             = "RestApi/middleware/GetUsers.php"
}