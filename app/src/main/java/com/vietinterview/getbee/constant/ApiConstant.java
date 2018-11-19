package com.vietinterview.getbee.constant;


import com.vietinterview.getbee.BuildConfig;

/**
 * Created by Envy 15T on 6/4/2015.
 */
public class ApiConstant {


    public static final boolean IS_DEBUG_BUILD_TYPE = BuildConfig.BUILD_TYPE.equals("debug");

    public static final int REQUEST_TIMEOUT = 20;
    public static final int LIMIT = 30;

    //Server KH
    public final static String REAL_URL = "https://api.getbee.vn/";
    public final static String IMG_URL_REAL = "https://getbee.vn/";
    public final static String DEV_URL = "https://api.dev.getbee.vn/";
    public final static String IMG_URL_DEV = "https://dev.getbee.vn/";
//    public final static String DEV_URL = "https://api.getbee.vn/";
//    public final static String IMG_URL_DEV = "https://getbee.vn/";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public final static String BASE_URL = IS_DEBUG_BUILD_TYPE ? DEV_URL : REAL_URL;
    public final static String IMG_URL = IS_DEBUG_BUILD_TYPE ? IMG_URL_DEV : IMG_URL_REAL;
    public final static int POST = 1;
    public final static int GET = 0;
    public final static int DELETE = 2;
    public final static String LOGIN = BASE_URL + "api/authenticate";
    public final static String REGIST = BASE_URL + "api/register";
    public final static String GET_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchJob";
    public final static String GET_SAVED_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchMySaveJobs";
    public final static String GET_APPLYED_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchJobsApplyCv";
    public final static String GET_LIST_CARRER = BASE_URL + "svccollaborator/api/mstCareer";
    public final static String GET_LIST_CITY = BASE_URL + "svccollaborator/api/mstCity";
    public final static String SAVE_UNSAVE_JOB = BASE_URL + "svccollaborator/api/jobs/addRemoveMyJob";
    public final static String GET_DETAIL_JOB = BASE_URL + "svccollaborator/api/jobs/getJobDetail";
    public final static String SEARCH_CV_SAVE = BASE_URL + "svccollaborator/api/cvs/searchCvSave";
    public final static String SEARCH_CV_SUBMIT = BASE_URL + "svccollaborator/api/cvs/searchCvSubmit";
    public final static String SEARCH_MY_CV = BASE_URL + "svccollaborator/api/cvs/searchMyCv";
    public final static String DELETE_MY_CV = BASE_URL + "svccollaborator/api/cvs/delete";
    public final static String GET_DETAIL_CV = BASE_URL + "svccollaborator/api/cvs/getCvById";
    public final static String SUBMIT_CV = BASE_URL + "svccollaborator/api/cvs/submitCvVesionMobile";
    public final static String GET_MY_PROFILE = BASE_URL + "svccollaborator/api/getProfiles";
    public final static String UPDATE_MY_PROFILE = BASE_URL + "svccollaborator/api/saveProfile";
    public final static String CHANGE_PASSWORD = BASE_URL + "api/account/change-password";
}
