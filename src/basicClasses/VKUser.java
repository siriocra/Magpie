package basicClasses;

import com.google.gson.annotations.SerializedName;

public class VKUser {
	
	@SerializedName("uid")
    private String userId;
	
	@SerializedName("first_name")
	private String firstName;
	
	@SerializedName("last_name")
    private String lastName;
	
	@SerializedName("nickname")
    private String nickname;
	
	@SerializedName("screen_name")
    private String screenName;
	
	@SerializedName("sex")
    private String userSex;
	
	@SerializedName("bdate")
    private String birthDate;
	
	@SerializedName("city")
    private String city;
	
	@SerializedName("country")
    private String country;
	
	@SerializedName("photo")
    private String photoURL;
	
	@SerializedName("photo_medium")
    private String photo100URL;
	
	@SerializedName("photo_big")
    private String photo200URL;
	
	@SerializedName("photo_rec")
    private String photo50URL;
	
	@SerializedName("online")
	private String online;
	
	@SerializedName("has_mobile")
	private String hasMobile;	
	
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
	
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getPhoto100URL() {
		return photo100URL;
	}

	public void setPhoto100URL(String photo100url) {
		photo100URL = photo100url;
	}

	public String getPhoto200URL() {
		return photo200URL;
	}

	public void setPhoto200URL(String photo200url) {
		photo200URL = photo200url;
	}

	public String getOnline() {
		if (online == "1") 
			return "online";
		else
			return "offline";
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public String getHasMobile() {
		return hasMobile;
	}

	public void setHasMobile(String hasMobile) {
		this.hasMobile = hasMobile;
	}

}
