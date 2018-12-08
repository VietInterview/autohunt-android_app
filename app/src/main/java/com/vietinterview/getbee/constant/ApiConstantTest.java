package com.vietinterview.getbee.constant;


import com.vietinterview.getbee.BuildConfig;

/**
 * Created by Envy 15T on 6/4/2015.
 */
public class ApiConstantTest {


    public boolean IS_DEBUG_BUILD_TYPE = BuildConfig.BUILD_TYPE.equals("debug");

    public static final int REQUEST_TIMEOUT = 20;
    public static final int LIMIT = 30;

    //Server KH
    public final String REAL_URL = "https://api.getbee.vn/";
    public final String IMG_URL_REAL = "https://getbee.vn/";
    public final String DEV_URL = "https://api.dev.getbee.vn/";
    public final String IMG_URL_DEV = "https://dev.getbee.vn/";
    //    public final static String DEV_URL = "https://api.getbee.vn/";
//    public final static String IMG_URL_DEV = "https://getbee.vn/";

    public String BASE_URL;
    public String IMG_URL;
    public final int POST = 1;
    public final int GET = 0;
    public final int DELETE = 2;
    public String LOGIN = getBASE_URL() + "api/authenticate";
    public String ACCESS_TOKEN = "ACCESS_TOKEN";
    public String REGIST = BASE_URL + "api/register";
    public String GET_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchJob";
    public String GET_SAVED_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchMySaveJobs";
    public String GET_APPLYED_SEARCH_JOBS = BASE_URL + "svccollaborator/api/jobs/searchJobsApplyCv";
    public String GET_LIST_CARRER = BASE_URL + "svccollaborator/api/mstCareer";
    public String GET_LIST_CITY = BASE_URL + "svccollaborator/api/mstCity";
    public String SAVE_UNSAVE_JOB = BASE_URL + "svccollaborator/api/jobs/addRemoveMyJob";
    public String GET_DETAIL_JOB = BASE_URL + "svccollaborator/api/jobs/getJobDetail";
    public String SEARCH_CV_SAVE = BASE_URL + "svccollaborator/api/cvs/searchCvSave";
    public String SEARCH_CV_SUBMIT = BASE_URL + "svccollaborator/api/cvs/searchCvSubmit";
    public String SEARCH_MY_CV = BASE_URL + "svccollaborator/api/cvs/searchMyCv";
    public String DELETE_MY_CV = BASE_URL + "svccollaborator/api/cvs/delete";
    public String GET_DETAIL_CV = BASE_URL + "svccollaborator/api/cvs/getCvById";
    public String SUBMIT_CV = BASE_URL + "svccollaborator/api/cvs/submitCvVesionMobile";
    public String GET_CTV_PROFILE = BASE_URL + "svccollaborator/api/getProfiles";
    public String GET_CUS_PROFILE = BASE_URL + "svccustomer/api/getProfiles";
    public String UPDATE_MY_PROFILE = BASE_URL + "svccollaborator/api/saveProfile";
    public String CHANGE_PASSWORD = BASE_URL + "api/account/change-password";
    public String GET_MY_ACCOUNT = BASE_URL + "api/account";
    public String GET_JOB_CUSTOMER = BASE_URL + "svccustomer/api/searchCusHome";
    public String GET_DETAIL_JOB_CUSTOMER = BASE_URL + "svccustomer/api/cusjobs/getJobById";
    public String GET_CV_BY_JOB_CUSTOMER = BASE_URL + "svccustomer/api/searchCusHomeCvByJob";
    public String GET_DETAIL_CV_BY_JOB_CUSTOMER = BASE_URL + "svccollaborator/api/cvs/getViewCvById";


    public void setBASE_URL(String BASE_URL) {
        this.BASE_URL = BASE_URL;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

    public void setIMG_URL(String IMG_URL) {
        this.IMG_URL = IMG_URL;
    }

    public String getIMG_URL() {
        return IMG_URL;
    }

    public String getLOGIN() {
        return getBASE_URL() + "api/authenticate";
    }

    public String getREGIST() {
        return getBASE_URL() + "api/register";
    }

    public String getGET_SEARCH_JOBS() {
        return getBASE_URL() + "svccollaborator/api/jobs/searchJob";
    }

    public String getGET_SAVED_SEARCH_JOBS() {
        return getBASE_URL() + "svccollaborator/api/jobs/searchMySaveJobs";
    }

    public String getGET_APPLYED_SEARCH_JOBS() {
        return getBASE_URL() + "svccollaborator/api/jobs/searchJobsApplyCv";
    }

    public String getGET_LIST_CARRER() {
        return getBASE_URL() + "svccollaborator/api/mstCareer";
    }

    public String getGET_LIST_CITY() {
        return getBASE_URL() + "svccollaborator/api/mstCity";
    }

    public String getSAVE_UNSAVE_JOB() {
        return getBASE_URL() + "svccollaborator/api/jobs/addRemoveMyJob";
    }

    public String getGET_DETAIL_JOB() {
        return getBASE_URL() + "svccollaborator/api/jobs/getJobDetail";
    }

    public String getSEARCH_CV_SAVE() {
        return getBASE_URL() + "svccollaborator/api/cvs/searchCvSave";
    }

    public String getSEARCH_CV_SUBMIT() {
        return getBASE_URL() + "svccollaborator/api/cvs/searchCvSubmit";
    }

    public String getSEARCH_MY_CV() {
        return getBASE_URL() + "svccollaborator/api/cvs/searchMyCv";
    }

    public String getDELETE_MY_CV() {
        return getBASE_URL() + "svccollaborator/api/cvs/delete";
    }

    public String getGET_DETAIL_CV() {
        return getBASE_URL() + "svccollaborator/api/cvs/getCvById";
    }

    public String getSUBMIT_CV() {
        return getBASE_URL() + "svccollaborator/api/cvs/submitCvVesionMobile";
    }

    public String getGET_CTV_PROFILE() {
        return getBASE_URL() + "svccollaborator/api/getProfiles";
    }

    public String getGET_CUS_PROFILE() {
        return getBASE_URL() + "svccustomer/api/getProfiles";
    }

    public String getUPDATE_MY_PROFILE() {
        return getBASE_URL() + "svccollaborator/api/saveProfile";
    }

    public String getCHANGE_PASSWORD() {
        return getBASE_URL() + "api/account/change-password";
    }

    public String getGET_MY_ACCOUNT() {
        return getBASE_URL() + "api/account";
    }

    public String getGET_JOB_CUSTOMER() {
        return getBASE_URL() + "svccustomer/api/searchCusHome";
    }

    public String getGET_DETAIL_JOB_CUSTOMER() {
        return getBASE_URL() + "svccustomer/api/cusjobs/getJobById";
    }

    public String getGET_CV_BY_JOB_CUSTOMER() {
        return getBASE_URL() + "svccustomer/api/searchCusHomeCvByJob";
    }

    public String getGET_DETAIL_CV_BY_JOB_CUSTOMER() {
        return getBASE_URL() + "svccollaborator/api/cvs/getViewCvById";
    }
}
