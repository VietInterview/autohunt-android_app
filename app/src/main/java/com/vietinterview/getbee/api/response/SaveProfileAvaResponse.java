
package com.vietinterview.getbee.api.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SaveProfileAvaResponse {

    @SerializedName("activated")
    @Expose
    private Integer activated;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("birthday")
    @Expose
    private Integer birthday;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("collCareer")
    @Expose
    private String collCareer;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("contractDate")
    @Expose
    private Integer contractDate;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("langKey")
    @Expose
    private String langKey;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("parentId")
    @Expose
    private Integer parentId;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("registerOs")
    @Expose
    private String registerOs;
    @SerializedName("resetDate")
    @Expose
    private String resetDate;
    @SerializedName("type")
    @Expose
    private Integer type;

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCollCareer() {
        return collCareer;
    }

    public void setCollCareer(String collCareer) {
        this.collCareer = collCareer;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getContractDate() {
        return contractDate;
    }

    public void setContractDate(Integer contractDate) {
        this.contractDate = contractDate;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRegisterOs() {
        return registerOs;
    }

    public void setRegisterOs(String registerOs) {
        this.registerOs = registerOs;
    }

    public String getResetDate() {
        return resetDate;
    }

    public void setResetDate(String resetDate) {
        this.resetDate = resetDate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}
