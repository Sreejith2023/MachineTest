package app.com.whiterabbittest.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmployeeResponseModelMain {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("profile_image")
    @Expose
    private Object profileImage;
    @SerializedName("address")
    @Expose
    private EmployeeResponseAddressData address;
    @SerializedName("phone")
    @Expose
    private Object phone;
    @SerializedName("website")
    @Expose
    private Object website;
    @Nullable
    @SerializedName("company")
    @Expose
    private EmployeeResponseCompanyData company;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    public EmployeeResponseAddressData getAddress() {
        return address;
    }

    public void setAddress(EmployeeResponseAddressData address) {
        this.address = address;
    }

    public Object getPhone() {
        return phone;
    }

    public void setPhone(Object phone) {
        this.phone = phone;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

    public EmployeeResponseCompanyData getCompany() {
        return company;
    }

    public void setCompany(EmployeeResponseCompanyData company) {
        this.company = company;
    }

}
