package com.vietinterview.getbee.fragments;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.ApiClient;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetCTVProfileRequest;
import com.vietinterview.getbee.api.request.SaveMyProfileRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.api.response.CountryResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.ctvprofile.City;
import com.vietinterview.getbee.api.response.ctvprofile.Country;
import com.vietinterview.getbee.api.response.ctvprofile.DesideratedCareer;
import com.vietinterview.getbee.api.response.ctvprofile.MyProfileResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.callback.RetrofitAPI;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.RobotoEditText;
import com.vietinterview.getbee.customview.RobotoMediumEditText;
import com.vietinterview.getbee.customview.RobotoTextView;
import com.vietinterview.getbee.model.Carrer;
import com.vietinterview.getbee.utils.DateUtil;
import com.vietinterview.getbee.utils.DebugLog;
import com.vietinterview.getbee.utils.DialogUtil;
import com.vietinterview.getbee.utils.FileUtils;
import com.vietinterview.getbee.utils.FragmentUtil;
import com.vietinterview.getbee.utils.ImageHelper;
import com.vietinterview.getbee.utils.ShowImageUtils;
import com.vietinterview.getbee.utils.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;


public class CollaboratorProfileFragment extends BaseFragment implements DatePickerDialog.OnDateSetListener {
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.edtFullName)
    RobotoMediumEditText edtFullName;
    @BindView(R.id.edtPhone)
    RobotoEditText edtPhone;
    @BindView(R.id.tvEmail)
    RobotoTextView tvEmail;
    @BindView(R.id.edtAddress)
    RobotoEditText edtAddress;
    @BindView(R.id.tvCarrer)
    RobotoTextView tvCarrer;
    @BindView(R.id.tvCode)
    TextView tvCode;
    @BindView(R.id.edtDateContract)
    EditText edtDateContract;
    @BindView(R.id.edtCompanyName)
    EditText edtCompanyName;
    @BindView(R.id.tvCountry)
    TextView tvCountry;
    @BindView(R.id.tvCity)
    TextView tvCity;
    @BindView(R.id.edtBirthDay)
    EditText edtBirthDay;
    @BindView(R.id.imgEditPhone)
    ImageView imgEditPhone;
    @BindView(R.id.imgEditBirthday)
    ImageView imgEditBirthday;
    @BindView(R.id.imgEditAdd)
    ImageView imgEditAdd;
    @BindView(R.id.imgEditCompany)
    ImageView imgEditCompany;
    @BindView(R.id.imgEditContract)
    ImageView imgEditContract;
    @BindView(R.id.imgFullName)
    ImageView imgFullName;
    @BindView(R.id.imgAva)
    ImageView imgAva;
    @BindView(R.id.rlCode)
    RelativeLayout rlCode;
    private String mCarrerName = "";
    ArrayList<CareerResponse> careerResponses = new ArrayList<>();
    ArrayList<CityResponse> citiesResponses = new ArrayList<>();
    ArrayList<CountryResponse> countryResponses = new ArrayList<>();
    private Bitmap mBitmap;
    private String mCountryId = "0";
    private String mCountryName = "";
    private String mCityId = "0";
    private String mCityName = "";
    MyProfileResponse mMyProfileResponse;
    SaveMyProfileRequest saveMyProfileRequest;
    GetCTVProfileRequest getMyProfileRequest;
    private boolean isEditPhone = false;
    private boolean isEditBirthday = false;
    private boolean isEditAdd = false;
    private boolean isEditCompany = false;
    private boolean isEditContract = false;
    private boolean isEditFullname = false;
    private Calendar calendar;
    BottomSheetDialog mBottomDialogNotificationAction;
    protected RetrofitAPI mService;

    public static CollaboratorProfileFragment newInstance(MyProfileResponse myProfileResponse) {
        CollaboratorProfileFragment fm = new CollaboratorProfileFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("myProfileResponse", myProfileResponse);
        fm.setArguments(bundle);
        return fm;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_collaborator_profile;
    }

    @Override
    protected void initView(View root, LayoutInflater inflater, ViewGroup container) {
        setCustomToolbar(true);
        GlobalDefine.currentFragment = this;
        calendar = Calendar.getInstance();
        setCustomToolbarVisible(true);
        imgEditPhone.setImageDrawable(isEditPhone ? getResources().getDrawable(R.drawable.ic_saveok) : getResources().getDrawable(R.drawable.ic_pencil_edit));
        imgEditBirthday.setImageDrawable(isEditBirthday ? getResources().getDrawable(R.drawable.ic_saveok) : getResources().getDrawable(R.drawable.ic_pencil_edit));
        imgEditAdd.setImageDrawable(isEditAdd ? getResources().getDrawable(R.drawable.ic_saveok) : getResources().getDrawable(R.drawable.ic_pencil_edit));
        imgEditCompany.setImageDrawable(isEditCompany ? getResources().getDrawable(R.drawable.ic_saveok) : getResources().getDrawable(R.drawable.ic_pencil_edit));
        imgEditContract.setImageDrawable(isEditContract ? getResources().getDrawable(R.drawable.ic_saveok) : getResources().getDrawable(R.drawable.ic_pencil_edit));
        imgFullName.setImageDrawable(isEditFullname ? getResources().getDrawable(R.drawable.ic_saveok) : getResources().getDrawable(R.drawable.ic_pencil_edit));
    }

    @Override
    protected void getArgument(Bundle bundle) {
        mMyProfileResponse = bundle.getParcelable("myProfileResponse");
    }

    @Override
    protected void initData() {
        if (mMyProfileResponse == null)
            getMyProfile();
        else {
            ShowImageUtils.showImage(getActivity(), mMyProfileResponse.getImageUrl(), R.drawable.ic_ava_null, imgAva);
            edtFullName.setText(mMyProfileResponse.getFullNameColl());
            edtPhone.setText(mMyProfileResponse.getPhoneColl());
            tvEmail.setText(mMyProfileResponse.getEmailColl());
            edtAddress.setText(mMyProfileResponse.getAddressColl());
            tvCarrer.setText(mMyProfileResponse.getCareerColl());
            tvCode.setText(mMyProfileResponse.getCode());
            edtCompanyName.setText(mMyProfileResponse.getCompanyName());
            edtBirthDay.setText(DateUtil.convertToMyFormatyyyyMMdd(mMyProfileResponse.getBirthday() + ""));
            birthday = mMyProfileResponse.getBirthday() == null ? 0 : mMyProfileResponse.getBirthday();
            contractDate = mMyProfileResponse.getContractDate() == null ? 0 : mMyProfileResponse.getContractDate();
            if (mMyProfileResponse.getCities() != null && mMyProfileResponse.getCities().size() > 0) {
                tvCity.setText(mMyProfileResponse.getCities().get(0).getName());
                mCityId = mMyProfileResponse.getCities().get(0).getId() + "";
                mCityName = mMyProfileResponse.getCities().get(0).getName();
            }
            if (mMyProfileResponse.getCountries() != null && mMyProfileResponse.getCountries().size() > 0) {
                tvCountry.setText(mMyProfileResponse.getCountries().get(0).getName());
                mCountryId = mMyProfileResponse.getCountries().get(0).getId() + "";
                mCountryName = mMyProfileResponse.getCountries().get(0).getName();
            }
            edtDateContract.setText(mMyProfileResponse.getContractDate() != null ? DateUtil.convertToMyFormatyyyyMMdd(mMyProfileResponse.getContractDate() + "") : "");
            if (mMyProfileResponse.getDesideratedCareer() != null) {
                if (mMyProfileResponse.getDesideratedCareer().size() > 0) {
                    StringBuilder s0 = new StringBuilder("");
                    for (int i = 0; i < mMyProfileResponse.getDesideratedCareer().size(); i++) {
                        if (i != mMyProfileResponse.getDesideratedCareer().size() - 1) {
                            s0.append(mMyProfileResponse.getDesideratedCareer().get(i).getName() + ", ");
                        } else {
                            s0.append(mMyProfileResponse.getDesideratedCareer().get(i).getName() + "");
                        }
                    }
                    mCarrerName = s0.toString();
//                    mCarrerId = String.valueOf(mMyProfileResponse.getDesideratedCareer().get(0).getId());
                    tvCarrer.setText(mCarrerName);
                }
            } else {
                tvCarrer.setText(mCarrerName);
            }
        }
    }

    private static final int REQUEST_TAKE_PHOTO = 0;
    private static final int REQUEST_SELECT_IMAGE_IN_ALBUM = 1; // The URI of photo taken with camera
    private Uri mUriPhotoTaken;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void showDialogNotificationAction() {
        try {
            View sheetView = getActivity().getLayoutInflater().inflate(R.layout.dialog_bottom_notification_action, null);
            mBottomDialogNotificationAction = new BottomSheetDialog(getActivity());
            mBottomDialogNotificationAction.setContentView(sheetView);
            mBottomDialogNotificationAction.show();

            LinearLayout llTakePhoto = sheetView.findViewById(R.id.llTakePhoto);
            llTakePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                        try {
                            File file = File.createTempFile("IMG_", ".jpg", storageDir);
                            mUriPhotoTaken = Uri.fromFile(file);
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, mUriPhotoTaken);
                            startActivityForResult(intent, REQUEST_TAKE_PHOTO);
                        } catch (IOException e) {
                            DebugLog.showLogCat(e.getMessage());
                        }
                    }
                    mBottomDialogNotificationAction.dismiss();
                }
            });
            LinearLayout llUpload = sheetView.findViewById(R.id.llUpload);
            llUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, REQUEST_SELECT_IMAGE_IN_ALBUM);
                    mBottomDialogNotificationAction.dismiss();
                }
            });
            LinearLayout llClose = sheetView.findViewById(R.id.llClose);
            llClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBottomDialogNotificationAction.dismiss();
                }
            });
            FrameLayout bottomSheet = (FrameLayout) mBottomDialogNotificationAction.findViewById(android.support.design.R.id.design_bottom_sheet);
            bottomSheet.setBackground(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.imgAva)
    public void onAvaClick() {
        showDialogNotificationAction();
    }

    @OnClick(R.id.imgEditPhone)
    public void onEditPhoneClick() {
        if (isEditPhone) {
            edtPhone.clearFocus();
            edtPhone.setEnabled(false);
            saveProfile();
            isEditPhone = false;
            imgEditPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtPhone.setEnabled(true);
            edtPhone.requestFocus();
            isEditPhone = true;
            imgEditPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditBirthday)
    public void onEditBirthdayClick() {
        if (isEditBirthday) {
//            edtBirthDay.clearFocus();
            saveProfile();
            isEditBirthday = false;
            imgEditBirthday.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
//            edtBirthDay.requestFocus();
            isEditBirthday = true;
            imgEditBirthday.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgFullName)
    public void onEditFullnameClick() {
        if (isEditFullname) {
            edtFullName.setEnabled(false);
            edtFullName.clearFocus();
            saveProfile();
            isEditFullname = false;
            imgFullName.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtFullName.setEnabled(true);
            edtFullName.requestFocus();
            isEditFullname = true;
            imgFullName.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditAdd)
    public void onEditAddClick() {
        if (isEditAdd) {
            edtAddress.clearFocus();
            edtAddress.setEnabled(false);
            saveProfile();
            isEditAdd = false;
            imgEditAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtAddress.setEnabled(true);
            edtAddress.requestFocus();
            isEditAdd = true;
            imgEditAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditCompany)
    public void onEditCompanyClick() {
        if (isEditCompany) {
            edtCompanyName.setEnabled(false);
            edtCompanyName.clearFocus();
            saveProfile();
            isEditCompany = false;
            imgEditCompany.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtCompanyName.setEnabled(true);
            edtCompanyName.requestFocus();
            isEditCompany = true;
            imgEditCompany.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditContract)
    public void onEditContractClick() {
        if (isEditContract) {
            saveProfile();
            isEditContract = false;
            imgEditContract.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            new DatePickerDialog(getActivity(), this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            isEditContract = true;
            imgEditContract.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.rlChangePass)
    public void onChangePassClick() {
        FragmentUtil.pushFragment(getActivity(), CollaboratorProfileFragment.this, new ChangePasswordFragment(), null);
    }

    @OnClick(R.id.llCountry)
    public void onCountryClick() {
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(false, true, mCountryId, mCountryName, true), null);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        update();
    }

    int birthday = 0;
    int contractDate = 0;

    private void update() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfInt = new SimpleDateFormat("yyyyMMdd");
        if (isEditBirthday) {
            birthday = Integer.parseInt(sdfInt.format(calendar.getTime()));
            edtBirthDay.setText(sdf.format(calendar.getTime()));
        } else if (isEditContract) {
            contractDate = Integer.parseInt(sdfInt.format(calendar.getTime()));
            edtDateContract.setText(sdf.format(calendar.getTime()));
        }
    }

    @OnClick(R.id.llCity)
    public void onCityPassClick() {
        FragmentUtil.pushFragment(getActivity(), this, new CarrerOrCityFragment().newInstance(true, mCityId, mCityName, true), null);
    }

    public void getMyProfile() {
        showCoverNetworkLoading();
        getMyProfileRequest = new GetCTVProfileRequest();
        getMyProfileRequest.callRequest(getActivity(), new ApiObjectCallBack<MyProfileResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, MyProfileResponse dataSuccess, List<MyProfileResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    mMyProfileResponse = dataSuccess;
                    ShowImageUtils.showImage(getActivity(), mMyProfileResponse.getImageUrl(), R.drawable.ic_ava_null, imgAva);
                    edtBirthDay.setText(DateUtil.convertToMyFormatyyyyMMdd(mMyProfileResponse.getBirthday() + ""));
                    edtFullName.setText(mMyProfileResponse.getFullNameColl());
                    edtPhone.setText(mMyProfileResponse.getPhoneColl());
                    tvEmail.setText(mMyProfileResponse.getEmailColl());
                    edtAddress.setText(mMyProfileResponse.getAddressColl());
                    tvCarrer.setText(mMyProfileResponse.getCareerColl());
                    tvCode.setText(mMyProfileResponse.getCode());
                    birthday = mMyProfileResponse.getBirthday() == null ? 0 : mMyProfileResponse.getBirthday();
                    contractDate = mMyProfileResponse.getContractDate() == null ? 0 : mMyProfileResponse.getContractDate();
                    if (mMyProfileResponse.getCities() != null && mMyProfileResponse.getCities().size() > 0) {
                        tvCity.setText(mMyProfileResponse.getCities().get(0).getName());
                        mCityId = mMyProfileResponse.getCities().get(0).getId() + "";
                        mCityName = mMyProfileResponse.getCities().get(0).getName();
                    }
                    if (mMyProfileResponse.getCountries() != null && mMyProfileResponse.getCountries().size() > 0) {
                        tvCountry.setText(mMyProfileResponse.getCountries().get(0).getName());
                        mCountryId = mMyProfileResponse.getCountries().get(0).getId() + "";
                        mCountryName = mMyProfileResponse.getCountries().get(0).getName();
                    }
                    edtCompanyName.setText(mMyProfileResponse.getCompanyName());
                    edtDateContract.setText(mMyProfileResponse.getContractDate() != null ? DateUtil.convertToMyFormatyyyyMMdd(mMyProfileResponse.getContractDate() + "") : "");
                    if (mMyProfileResponse.getDesideratedCareer() != null) {
                        if (mMyProfileResponse.getDesideratedCareer().size() > 0) {
                            StringBuilder s0 = new StringBuilder("");
                            for (int i = 0; i < mMyProfileResponse.getDesideratedCareer().size(); i++) {
                                if (i != mMyProfileResponse.getDesideratedCareer().size() - 1) {
                                    s0.append(mMyProfileResponse.getDesideratedCareer().get(i).getName() + ", ");
                                } else {
                                    s0.append(mMyProfileResponse.getDesideratedCareer().get(i).getName() + "");
                                }
                            }
                            mCarrerName = s0.toString();
                            tvCarrer.setText(mCarrerName);
                        }
                    } else {
                        tvCarrer.setText(mCarrerName);
                    }
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), getResources().getString(R.string.error_please_try));
                }
            }
        });

    }

    public void saveProfile() {
        showCoverNetworkLoading();
        ArrayList<Carrer> carrers = new ArrayList<>();
        if (careerResponses != null && careerResponses.size() > 0) {
            for (int i = 0; i < careerResponses.size(); i++) {
                carrers.add(new Carrer(careerResponses.get(i).getId(), careerResponses.get(i).getName()));
            }
        } else {
            for (int i = 0; i < mMyProfileResponse.getDesideratedCareer().size(); i++) {
                carrers.add(new Carrer(mMyProfileResponse.getDesideratedCareer().get(i).getId(), mMyProfileResponse.getDesideratedCareer().get(i).getName()));
            }
        }
        ArrayList<City> inputCities = new ArrayList<>();
        if (citiesResponses != null && citiesResponses.size() > 0) {
            for (int i = 0; i < citiesResponses.size(); i++) {
                inputCities.add(new City(citiesResponses.get(i).getCountryId(), null, citiesResponses.get(i).getId(), citiesResponses.get(i).getName()));
            }
        } else {
            for (int i = 0; i < mMyProfileResponse.getCities().size(); i++) {
                inputCities.add(new City(mMyProfileResponse.getCities().get(i).getCountryId(), null, mMyProfileResponse.getCities().get(i).getId(), mMyProfileResponse.getCities().get(i).getName()));
            }
        }
        ArrayList<Country> inputCountries = new ArrayList<>();
        if (countryResponses != null && countryResponses.size() > 0) {
            for (int i = 0; i < countryResponses.size(); i++) {
                inputCountries.add(new Country(countryResponses.get(i).getId(), countryResponses.get(i).getName()));
            }
        } else {
            for (int i = 0; i < mMyProfileResponse.getCountries().size(); i++) {
                inputCountries.add(new Country(mMyProfileResponse.getCountries().get(i).getId(), mMyProfileResponse.getCountries().get(i).getName()));
            }
        }
        saveMyProfileRequest = new SaveMyProfileRequest(edtFullName.getText().toString().trim(), edtAddress.getText().toString().trim(), mMyProfileResponse.getCareerColl(), carrers, inputCities, inputCountries, birthday, edtPhone.getText().toString(), edtCompanyName.getText().toString().trim(), contractDate);
        saveMyProfileRequest.callRequest(getActivity(), new ApiObjectCallBack<MyProfileResponse, ErrorResponse>() {
            @Override
            public void onSuccess(int status, MyProfileResponse dataSuccess, List<MyProfileResponse> listDataSuccess, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    mMyProfileResponse.setFullNameColl(dataSuccess.getFullNameColl());
                    mMyProfileResponse.setAddressColl(dataSuccess.getAddressColl());
                    mMyProfileResponse.setPhoneColl(dataSuccess.getPhoneColl());
                    mMyProfileResponse.setCareerColl(dataSuccess.getCareerColl());
                    mMyProfileResponse.setDesideratedCareer(dataSuccess.getDesideratedCareer());
                    mMyProfileResponse.setEmailColl(dataSuccess.getEmailColl());
                    edtFullName.setText(mMyProfileResponse.getFullNameColl());
                    edtPhone.setText(mMyProfileResponse.getPhoneColl());
                    edtAddress.setText(mMyProfileResponse.getAddressColl());
                    tvCarrer.setText(mMyProfileResponse.getCareerColl());
                    if (mMyProfileResponse.getDesideratedCareer() != null) {
                        if (mMyProfileResponse.getDesideratedCareer().size() > 0) {
                            StringBuilder s0 = new StringBuilder("");
                            for (int i = 0; i < mMyProfileResponse.getDesideratedCareer().size(); i++) {
                                if (i != mMyProfileResponse.getDesideratedCareer().size() - 1) {
                                    s0.append(mMyProfileResponse.getDesideratedCareer().get(i).getName() + ", ");
                                } else {
                                    s0.append(mMyProfileResponse.getDesideratedCareer().get(i).getName() + "");
                                }
                            }
                            mCarrerName = s0.toString();
//                            mCarrerId = String.valueOf(mMyProfileResponse.getDesideratedCareer().get(0).getId());
                            tvCarrer.setText(mCarrerName);
                        }
                    } else {
                        tvCarrer.setText(mCarrerName);
                    }
                    if (dataSuccess.getFullNameColl() != null) {
                        AccountManager.getUserInfoBean().name = dataSuccess.getFullNameColl();
                        getEventBaseFragment().setTextGreeting(AccountManager.getUserInfoBean().name);
                    } else getEventBaseFragment().setTextGreeting("");
                    Toast.makeText(getActivity(), getResources().getString(R.string.save_profile_success), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFail(int status, ErrorResponse dataFail, List<ErrorResponse> listDataFail, String message) {
                if (isAdded()) {
                    hideCoverNetworkLoading();
                    Toast.makeText(getActivity(), getResources().getString(R.string.save_profile_fail), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onRestore() {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.info_acc_tit));
    }

    @Override
    protected void initialize() {
        getEventBaseFragment().doFillBackground(getResources().getString(R.string.info_acc_tit));
    }

    @Override
    protected void onSaveState(Bundle bundle) {
    }

    @Override
    protected void onRestoreState(Bundle bundle) {
    }

    @OnClick(R.id.rlChooseJob)
    public void onChooseJobClick() {
        FragmentUtil.pushFragment(getActivity(), CollaboratorProfileFragment.this, new CarrerOrCityFragment().newInstance(false, "0", mCarrerName, false), null);
    }

    @Override
    public ArrayList<BaseRequest> getArrayBaseRequest() {
        ArrayList<BaseRequest> baseRequests = new ArrayList<>();
        return baseRequests;
    }

    @Override
    protected void processOnBackPress() {
    }

    @Override
    protected Drawable getIconLeft() {
        return getResources().getDrawable(R.drawable.ic_menu);
    }

    @Override
    protected void processCustomToolbar() {
        loadMenuLeft();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.save) {
            if (StringUtils.isEmpty(tvCarrer.getText().toString().trim())) {
                Toast.makeText(getActivity(), getResources().getString(R.string.choose_least_one), Toast.LENGTH_SHORT).show();
            } else
                saveProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public File saveBitmapToFile(File file) {
        try {

            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            selectedBitmap = RotateBitmap(selectedBitmap, 90);
            inputStream.close();

            // here i override the original image file
            file.createNewFile();
            FileOutputStream outputStream = new FileOutputStream(file);

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

            return file;
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap RotateBitmap(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO) {
            if (resultCode == RESULT_OK) {
                Uri imageUri;
                if (data == null || data.getData() == null) {
                    imageUri = mUriPhotoTaken;
                } else {
                    imageUri = data.getData();
                }

                File mFile = null;
                try {
                    mFile = FileUtils.from(getActivity(), imageUri);
                    mFile = saveBitmapToFile(mFile);
                    if (mFile.exists()) {
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
                        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", mFile.getName(), requestFile);
                        long fileSizeInBytes = mFile.length();
                        long fileSizeInKB = fileSizeInBytes / 1024;
                        long fileSizeInMB = fileSizeInKB / 1024;
                        if (fileSizeInMB > 1) {
                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Kích thước ảnh quá lớn để upload");
                        } else {
                            try {
                                mService = new ApiClient(getActivity()).createService();
                            } catch (NoSuchAlgorithmException e) {
                            } catch (KeyManagementException e) {
                            }
                            showCoverNetworkLoading();
                            Call<ResponseBody> responseBodyCall = mService.addRecord(multipartBody);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    hideCoverNetworkLoading();
                                    if (response.code() == 200) {
                                        Toast.makeText(getActivity(), "Upload Thành công", Toast.LENGTH_SHORT).show();
                                        mBitmap = ImageHelper.loadSizeLimitedBitmapFromUri(imageUri, getActivity().getContentResolver());
                                        if (mBitmap != null) {
                                            imgAva.setImageBitmap(mBitmap);
                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "Upload không thành công", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    hideCoverNetworkLoading();
                                    Toast.makeText(getActivity(), "Upload không thành công", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            if (resultCode == RESULT_OK && null != data) {
                Uri imageUri = data.getData();
                File mFile = null;
                try {
                    mFile = FileUtils.from(getActivity(), imageUri);
                    mFile = saveBitmapToFile(mFile);
                    if (mFile.exists()) {
                        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
                        MultipartBody.Part multipartBody = MultipartBody.Part.createFormData("file", mFile.getName(), requestFile);
                        long fileSizeInBytes = mFile.length();
                        long fileSizeInKB = fileSizeInBytes / 1024;
                        long fileSizeInMB = fileSizeInKB / 1024;
                        if (fileSizeInMB > 1) {
                            DialogUtil.showDialog(getActivity(), getResources().getString(R.string.noti_title), "Kích thước ảnh quá lớn để upload");
                        } else {
                            try {
                                mService = new ApiClient(getActivity()).createService();
                            } catch (NoSuchAlgorithmException e) {
                            } catch (KeyManagementException e) {
                            }
                            showCoverNetworkLoading();
                            Call<ResponseBody> responseBodyCall = mService.addRecord(multipartBody);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    hideCoverNetworkLoading();
                                    hideCoverNetworkLoading();
                                    if (response.code() == 200) {
                                        Toast.makeText(getActivity(), "Upload Thành công", Toast.LENGTH_SHORT).show();
                                        imgAva.setImageURI(imageUri);
                                    } else {
                                        Toast.makeText(getActivity(), "Upload không thành công", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    hideCoverNetworkLoading();
                                    Toast.makeText(getActivity(), "Upload không thành công", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (resultCode == RESULT_OK) {
                if (requestCode == AppConstant.FRAGMENT_CODE) {
                    careerResponses = data.getParcelableArrayListExtra("arrListCarrer");
                    if (careerResponses != null) {
                        if (mMyProfileResponse.getDesideratedCareer() != null) {
                            List<DesideratedCareer> desideratedCareers = new ArrayList<>();
                            for (int i = 0; i < careerResponses.size(); i++) {
                                desideratedCareers.add(new DesideratedCareer(careerResponses.get(i).getId(), careerResponses.get(i).getName()));
                            }
                            mMyProfileResponse.setDesideratedCareer(desideratedCareers);
                        } else {
                            List<DesideratedCareer> desideratedCareers = new ArrayList<>();
                            for (int i = 0; i < careerResponses.size(); i++) {
                                desideratedCareers.add(new DesideratedCareer(careerResponses.get(i).getId(), careerResponses.get(i).getName()));
                            }
                            mMyProfileResponse.setDesideratedCareer(desideratedCareers);
                        }
                        saveProfile();
                        tvCarrer.setText(mCarrerName);
                    }
                    mCountryId = String.valueOf(data.getIntExtra("countryId", 0));
                    mCountryName = data.getStringExtra("countryName");
                    if (mCountryName != null) {
                        if (countryResponses.size() > 0)
                            countryResponses.set(0, new CountryResponse(data.getIntExtra("countryId", 0), data.getStringExtra("countryName")));
                        else
                            countryResponses.add(new CountryResponse(data.getIntExtra("countryId", 0), data.getStringExtra("countryName")));
                        List<Country> countries = new ArrayList<>();
                        countries.add(new Country(data.getIntExtra("countryId", 0), data.getStringExtra("countryName")));
                        mMyProfileResponse.setCountries(countries);
                        saveProfile();
                        tvCountry.setText(mCountryName);
                    }
                    mCityId = String.valueOf(data.getIntExtra("cityId", 0));
                    mCityName = data.getStringExtra("cityName");
                    if (mCityName != null) {
                        if (citiesResponses.size() > 0)
                            citiesResponses.set(0, new CityResponse(data.getIntExtra("countryId", 0), null, data.getIntExtra("cityId", 0), data.getStringExtra("cityName")));
                        else
                            citiesResponses.add(new CityResponse(data.getIntExtra("countryId", 0), null, data.getIntExtra("cityId", 0), data.getStringExtra("cityName")));
                        List<City> cities = new ArrayList<>();
                        cities.add(new City(data.getIntExtra("countryId", 0), null, data.getIntExtra("cityId", 0), data.getStringExtra("cityName")));
                        mMyProfileResponse.setCities(cities);
                        saveProfile();
                        tvCity.setText(mCityName);
                    }
                }
            }
        }
    }
}
