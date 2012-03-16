package connect;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class VKResponse<T> {
	
	@SerializedName("response")
	ArrayList<T> response;

	public ArrayList<T> getResponse() {
		return response;
	}

	public void setResponse(ArrayList<T> response) {
		this.response = response;
	}
}
