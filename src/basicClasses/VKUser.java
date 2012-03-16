package basicClasses;

import com.google.gson.annotations.SerializedName;

public class VKUser {
	
	@SerializedName("uid")
    private String userId;
	
	@SerializedName("first_name")
	private String firstName;
	
	@SerializedName("last_name")
    private String lastName;
	
	@SerializedName("sex")
    private String userSex;
	
	@SerializedName("bdate")
    private String birthDate;
	
	@SerializedName("photo_rec")
    private String photo50URL;
	
	@SerializedName("mobile_phone")
	private String mobilePhone;
	
	@SerializedName("home_phone")
	private String homePhone;
	
	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getFirstName() {
		return firstName;
	}
    
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserSex() {
		switch (Integer.valueOf(userSex)){
			case 1: return "Female";
			case 2: return "Male";
			default: return "Not specified"; 
		}
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPhoto50URL() {
		return photo50URL;
	}

	public void setPhoto50URL(String photo50url) {
		photo50URL = photo50url;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
}
