package com.vietinterview.getbee.constant;


import com.vietinterview.getbee.BuildConfig;

/**
 * Created by Envy 15T on 6/4/2015.
 */
public class ApiConstant {


    public static final boolean IS_DEBUG_BUILD_TYPE = BuildConfig.BUILD_TYPE.equals("debug");

    public static final int REQUEST_TIMEOUT = 10;

    //Server KH
    public final static String REAL_URL = "http://54.176.149.102:8081/";
    public final static String DEV_URL = "http://54.176.149.102:8081/";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public final static String BASE_URL = IS_DEBUG_BUILD_TYPE ? DEV_URL : REAL_URL;
    public final static int POST = 1;
    public final static int GET = 0;

    public final static String LOGIN = BASE_URL + "api/authenticate";
    public final static String REGIST = BASE_URL + "api/register";
    public final static String GET_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchJob";
}
