package com.vietinterview.getbee.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.vietinterview.getbee.AccountManager;
import com.vietinterview.getbee.R;
import com.vietinterview.getbee.api.request.BaseRequest;
import com.vietinterview.getbee.api.request.GetCTVProfileRequest;
import com.vietinterview.getbee.api.request.GetCUSProfileRequest;
import com.vietinterview.getbee.api.request.SaveMyProfileRequest;
import com.vietinterview.getbee.api.request.SaveProfileAvaRequest;
import com.vietinterview.getbee.api.response.CareerResponse;
import com.vietinterview.getbee.api.response.CityResponse;
import com.vietinterview.getbee.api.response.CountryResponse;
import com.vietinterview.getbee.api.response.ErrorResponse;
import com.vietinterview.getbee.api.response.SaveProfileAvaResponse;
import com.vietinterview.getbee.api.response.ctvprofile.City;
import com.vietinterview.getbee.api.response.ctvprofile.Country;
import com.vietinterview.getbee.api.response.ctvprofile.DesideratedCareer;
import com.vietinterview.getbee.api.response.ctvprofile.MyProfileResponse;
import com.vietinterview.getbee.api.response.customerprofile.ProfileCustomerResponse;
import com.vietinterview.getbee.callback.ApiObjectCallBack;
import com.vietinterview.getbee.constant.AppConstant;
import com.vietinterview.getbee.constant.GlobalDefine;
import com.vietinterview.getbee.customview.RobotoEditText;
import com.vietinterview.getbee.customview.RobotoMediumEditText;
import com.vietinterview.getbee.customview.RobotoTextInputEditText;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class CollaboratorProfileFragment extends BaseFragment {
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
    private String mCarrerName = "";
    MyProfileResponse mMyProfileResponse;
    SaveMyProfileRequest saveMyProfileRequest;
    GetCTVProfileRequest getMyProfileRequest;
    private boolean isEditPhone = false;
    private boolean isEditBirthday = false;
    private boolean isEditAdd = false;
    private boolean isEditCompany = false;
    private boolean isEditContract = false;
    private boolean isEditFullname = false;
    BottomSheetDialog mBottomDialogNotificationAction;

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
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM);
                    }
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
            saveProfile();
            isEditPhone = false;
            imgEditPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtPhone.requestFocus();
            isEditPhone = true;
            imgEditPhone.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditBirthday)
    public void onEditBirthdayClick() {
        if (isEditBirthday) {
            edtBirthDay.clearFocus();
            saveProfile();
            isEditBirthday = false;
            imgEditBirthday.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtBirthDay.requestFocus();
            isEditBirthday = true;
            imgEditBirthday.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgFullName)
    public void onEditFullnameClick() {
        if (isEditFullname) {
            edtFullName.clearFocus();
            saveProfile();
            isEditFullname = false;
            imgFullName.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtFullName.requestFocus();
            isEditFullname = true;
            imgFullName.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditAdd)
    public void onEditAddClick() {
        if (isEditAdd) {
            edtAddress.clearFocus();
            saveProfile();
            isEditAdd = false;
            imgEditAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtAddress.requestFocus();
            isEditAdd = true;
            imgEditAdd.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditCompany)
    public void onEditCompanyClick() {
        if (isEditCompany) {
            edtCompanyName.clearFocus();
            saveProfile();
            isEditCompany = false;
            imgEditCompany.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtCompanyName.requestFocus();
            isEditCompany = true;
            imgEditCompany.setImageDrawable(getResources().getDrawable(R.drawable.ic_saveok));
        }
    }

    @OnClick(R.id.imgEditContract)
    public void onEditContractClick() {
        if (isEditContract) {
            edtDateContract.clearFocus();
            saveProfile();
            isEditContract = false;
            imgEditContract.setImageDrawable(getResources().getDrawable(R.drawable.ic_pencil_edit));
        } else {
            edtDateContract.requestFocus();
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
        saveMyProfileRequest = new SaveMyProfileRequest(edtFullName.getText().toString().trim(), edtAddress.getText().toString().trim(), mMyProfileResponse.getCareerColl(), carrers, inputCities, inputCountries, 19910812, edtPhone.getText().toString(), edtCompanyName.getText().toString().trim(), mMyProfileResponse.getContractDate() != null ? mMyProfileResponse.getContractDate() : 20180101);
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
                    tvEmail.setText(mMyProfileResponse.getEmailColl());
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

    ArrayList<CareerResponse> careerResponses = new ArrayList<>();
    ArrayList<CityResponse> citiesResponses = new ArrayList<>();
    ArrayList<CountryResponse> countryResponses = new ArrayList<>();
    private Bitmap mBitmap;
    private String mCountryId = "0";
    private String mCountryName = "";
    private String mCityId = "0";
    private String mCityName = "";

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
                mBitmap = ImageHelper.loadSizeLimitedBitmapFromUri(imageUri, getActivity().getContentResolver());
                if (mBitmap != null) {
                    imgAva.setImageBitmap(mBitmap);
                    Log.d("RecognizeActivity", "Image: " + imageUri + " resized to " + mBitmap.getWidth() + "x" + mBitmap.getHeight());
                    new SaveProfileAvaRequest(FileUtils.getPath(getActivity(), imageUri)).callRequest(getActivity(), new ApiObjectCallBack<SaveProfileAvaResponse, SaveProfileAvaResponse>() {
                        @Override
                        public void onSuccess(int status, SaveProfileAvaResponse dataSuccess, List<SaveProfileAvaResponse> listDataSuccess, String message) {
                            if (isAdded()) {
                                DebugLog.showLogCat(status + "\n" + dataSuccess);
                            }
                        }

                        @Override
                        public void onFail(int status, SaveProfileAvaResponse dataFail, List<SaveProfileAvaResponse> listDataFail, String message) {
                            if (isAdded()) {
                                DebugLog.showLogCat(status + "");
                            }
                        }
                    });
                }
            }
        } else if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM) {
            if (resultCode == RESULT_OK) {
                Uri imageUri;
                if (data == null || data.getData() == null) {
                    imageUri = mUriPhotoTaken;
                } else {
                    imageUri = data.getData();
                }
                mBitmap = ImageHelper.loadSizeLimitedBitmapFromUri(imageUri, getActivity().getContentResolver());
                if (mBitmap != null) {
                    imgAva.setImageBitmap(mBitmap);
                    new SaveProfileAvaRequest(FileUtils.getPath(getActivity(), imageUri)).callRequest(getActivity(), new ApiObjectCallBack<SaveProfileAvaResponse, SaveProfileAvaResponse>() {
                        @Override
                        public void onSuccess(int status, SaveProfileAvaResponse dataSuccess, List<SaveProfileAvaResponse> listDataSuccess, String message) {
                            if (isAdded()) {
                                DebugLog.showLogCat(status + "\n" + dataSuccess.getAddress());
                            }
                        }

                        @Override
                        public void onFail(int status, SaveProfileAvaResponse dataFail, List<SaveProfileAvaResponse> listDataFail, String message) {
                            if (isAdded()) {
                                DebugLog.showLogCat(status + "");
                            }
                        }
                    });
                    Log.d("RecognizeActivity", "Image: " + imageUri + " resized to " + mBitmap.getWidth() + "x" + mBitmap.getHeight());

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
                            citiesResponses.set(0, new CityResponse(data.getIntExtra("cityId", 0), data.getStringExtra("cityName")));
                        else
                            citiesResponses.add(new CityResponse(data.getIntExtra("cityId", 0), data.getStringExtra("cityName")));
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
