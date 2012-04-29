package ru.ncedu.magpie.connect;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;
/**
 * Template class for parsing responses from VK
 * @author IrisM
 *
 * @param <T>
 */
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
