package com.vietinterview.getbee.constant;


import com.vietinterview.getbee.BuildConfig;

/**
 * Created by Envy 15T on 6/4/2015.
 */
public class ApiConstant {

    public static final int REQUEST_TIMEOUT = 20;
    public static final int LIMIT = 30;
    public static final String REAL_URL = "https://api.getbee.vn/";
    public static final String IMG_URL_REAL = "https://getbee.vn/";
    public static final String DEV_URL = "https://api.dev.getbee.vn/";
    public static final String IMG_URL_DEV = "https://dev.getbee.vn/";
    public String BASE_URL;
    public String IMG_URL;
    public final static int POST = 1;
    public final static int GET = 0;
    public final static int DELETE = 2;
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    public String LOGIN = "api/authenticate";
    public String REGIST = "api/register";
    public String GET_SEARCH_JOBS = "svccollaborator/api/jobs/searchJob";
    public String GET_SAVED_SEARCH_JOBS = "svccollaborator/api/jobs/searchMySaveJobs";
    public String GET_APPLYED_SEARCH_JOBS = "svccollaborator/api/jobs/searchJobsApplyCv";
    public String GET_LIST_CARRER = "svccollaborator/api/mstCareer";
    public String GET_LIST_CITY = "svccollaborator/api/mstCity";
    public String GET_LIST_COUNTRY = "svccollaborator/api/mstCountry";
    public String SAVE_UNSAVE_JOB = "svccollaborator/api/jobs/addRemoveMyJob";
    public String GET_DETAIL_JOB = "svccollaborator/api/jobs/getJobDetail";
    public String SEARCH_CV_SAVE = "svccollaborator/api/cvs/searchCvSave";
    public String SEARCH_CV_SUBMIT = "svccollaborator/api/cvs/searchCvSubmit";
    public String SEARCH_MY_CV = "svccollaborator/api/cvs/searchMyCv";
    public String DELETE_MY_CV = "svccollaborator/api/cvs/delete";
    public String GET_DETAIL_CV = "svccollaborator/api/cvs/getCvById";
    public String SUBMIT_CV = "svccollaborator/api/cvs/submitCvVesionMobile";
    public String GET_CTV_PROFILE = "svccollaborator/api/getProfiles";
    public String GET_CUS_PROFILE = "svccustomer/api/getProfiles";
    public String UPDATE_MY_PROFILE = "svccollaborator/api/saveProfile";
    public String CHANGE_PASSWORD = "api/account/change-password";
    public String GET_MY_ACCOUNT = "api/account";
    public String GET_JOB_CUSTOMER = "svccustomer/api/searchCusHome";
    public String GET_DETAIL_JOB_CUSTOMER = "svccustomer/api/cusjobs/getJobById";
    public String GET_CV_BY_JOB_CUSTOMER = "svccustomer/api/searchCusHomeCvByJob";
    public String GET_DETAIL_CV_BY_JOB_CUSTOMER = "svccollaborator/api/cvs/getViewCvById";
    public String DETAIL_PROCESS_RESUME = "svccustomer/api/cvProcess/detail";
    public String LIST_REJECT_REASON = "svccollaborator/api/getListRejectReason";
    public String REJECT = "svccustomer/api/cvProcess/reject";
    public String INVITE_INTERVIEW = "svccustomer/api/cvProcess/inviteInterview";
    public String SEND_INTERVIEW = "svccustomer/api/cvProcess/sendInterview";
    public String UPDATE_INTERVIEW_STATUS = "svccustomer/api/cvProcess/updateInterviewStatus";
    public String OFFER_STATUS = "svccustomer/api/cvProcess/offerStatus";
    public String SEND_OFFER = "svccustomer/api/cvProcess/sendOffer";
    public String UPDATE_OFFER_STATUS = "svccustomer/api/cvProcess/updateOfferStatus";
    public String GO_TO_WORK_STATUS = "svccustomer/api/cvProcess/gotoworkStatus";
    public String GO_TO_WORK_UPDATE = "svccustomer/api/cvProcess/gotoWorkUpdate";
    public String CONTRACT_STATUS = "svccustomer/api/cvProcess/contractStatus";
    public String VIEW_EMAIL_INTERVIEW = "svccustomer/api/cvProcess/viewEmailInterview";
    public String VIEW_EMAIL_OFFER = "svccustomer/api/cvProcess/viewEmailOffer";
    public String UPDATE_OS = "api/updateOs";
    public String RESET_PASSWORD = "api/account/reset-password/init";
    public String SAVE_PROFILE_AVATAR = "svccollaborator/api/saveProfileAvatar";

    public int getPOST() {
        return POST;
    }

    public int getGET() {
        return GET;
    }

    public int getDELETE() {
        return DELETE;
    }

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
        return getBASE_URL() + LOGIN;
    }

    public String getREGIST() {
        return getBASE_URL() + REGIST;
    }

    public String getGET_SEARCH_JOBS() {
        return getBASE_URL() + GET_SEARCH_JOBS;
    }

    public String getGET_SAVED_SEARCH_JOBS() {
        return getBASE_URL() + GET_SAVED_SEARCH_JOBS;
    }

    public String getGET_APPLYED_SEARCH_JOBS() {
        return getBASE_URL() + GET_APPLYED_SEARCH_JOBS;
    }

    public String getGET_LIST_CARRER() {
        return getBASE_URL() + GET_LIST_CARRER;
    }

    public String getGET_LIST_CITY() {
        return getBASE_URL() + GET_LIST_CITY;
    }

    public String getSAVE_UNSAVE_JOB() {
        return getBASE_URL() + SAVE_UNSAVE_JOB;
    }

    public String getGET_DETAIL_JOB() {
        return getBASE_URL() + GET_DETAIL_JOB;
    }

    public String getSEARCH_CV_SAVE() {
        return getBASE_URL() + SEARCH_CV_SAVE;
    }

    public String getSEARCH_CV_SUBMIT() {
        return getBASE_URL() + SEARCH_CV_SUBMIT;
    }

    public String getSEARCH_MY_CV() {
        return getBASE_URL() + SEARCH_MY_CV;
    }

    public String getDELETE_MY_CV() {
        return getBASE_URL() + DELETE_MY_CV;
    }

    public String getGET_DETAIL_CV() {
        return getBASE_URL() + GET_DETAIL_CV;
    }

    public String getSUBMIT_CV() {
        return getBASE_URL() + SUBMIT_CV;
    }

    public String getGET_CTV_PROFILE() {
        return getBASE_URL() + GET_CTV_PROFILE;
    }

    public String getGET_CUS_PROFILE() {
        return getBASE_URL() + GET_CUS_PROFILE;
    }

    public String getUPDATE_MY_PROFILE() {
        return getBASE_URL() + UPDATE_MY_PROFILE;
    }

    public String getCHANGE_PASSWORD() {
        return getBASE_URL() + CHANGE_PASSWORD;
    }

    public String getGET_MY_ACCOUNT() {
        return getBASE_URL() + GET_MY_ACCOUNT;
    }

    public String getGET_JOB_CUSTOMER() {
        return getBASE_URL() + GET_JOB_CUSTOMER;
    }

    public String getGET_DETAIL_JOB_CUSTOMER() {
        return getBASE_URL() + GET_DETAIL_JOB_CUSTOMER;
    }

    public String getGET_CV_BY_JOB_CUSTOMER() {
        return getBASE_URL() + GET_CV_BY_JOB_CUSTOMER;
    }

    public String getGET_DETAIL_CV_BY_JOB_CUSTOMER() {
        return getBASE_URL() + GET_DETAIL_CV_BY_JOB_CUSTOMER;
    }

    public String getDETAIL_PROCESS_RESUME() {
        return getBASE_URL() + DETAIL_PROCESS_RESUME;
    }

    public String getLIST_REJECT_REASON() {
        return getBASE_URL() + LIST_REJECT_REASON;
    }

    public String getREJECT() {
        return getBASE_URL() + REJECT;
    }

    public String getINVITE_INTERVIEW() {
        return getBASE_URL() + INVITE_INTERVIEW;
    }

    public String getVIEW_EMAIL_INTERVIEW() {
        return getBASE_URL() + VIEW_EMAIL_INTERVIEW;
    }

    public String getSEND_INTERVIEW() {
        return getBASE_URL() + SEND_INTERVIEW;
    }

    public String getUPDATE_INTERVIEW_STATUS() {
        return getBASE_URL() + UPDATE_INTERVIEW_STATUS;
    }

    public String getOFFER_STATUS() {
        return getBASE_URL() + OFFER_STATUS;
    }

    public String getSEND_OFFER() {
        return getBASE_URL() + SEND_OFFER;
    }

    public String getUPDATE_OFFER_STATUS() {
        return getBASE_URL() + UPDATE_OFFER_STATUS;
    }

    public String getGO_TO_WORK_STATUS() {
        return getBASE_URL() + GO_TO_WORK_STATUS;
    }

    public String getGO_TO_WORK_UPDATE() {
        return getBASE_URL() + GO_TO_WORK_UPDATE;
    }

    public String getCONTRACT_STATUS() {
        return getBASE_URL() + CONTRACT_STATUS;
    }

    public String getVIEW_EMAIL_OFFER() {
        return getBASE_URL() + VIEW_EMAIL_OFFER;
    }

    public String getUPDATE_OS() {
        return getBASE_URL() + UPDATE_OS;
    }

    public String getRESET_PASSWORD() {
        return getBASE_URL() + RESET_PASSWORD;
    }

    public String getGET_LIST_COUNTRY() {
        return getBASE_URL() + GET_LIST_COUNTRY;
    }

    public String getSAVE_PROFILE_AVATAR() {
        return getBASE_URL() + SAVE_PROFILE_AVATAR;
    }
}
