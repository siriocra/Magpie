package ru.ncedu.magpie.basicClasses;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for storing events from VK
 * @author IrisM
 */
public class VKEvent {
	
	private String gid;
	private String name;
	private String screen_name;
	private String photo;
	private String start_date;
	private String end_date;
	
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm dd.MM.yyyy");
	
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getStart_date() {
		if (start_date != null){
			Date date = new Date();
			date.setTime((long)Integer.parseInt(start_date)*1000);
			return format.format(date);
		} else
			return "";
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		if (end_date != null){
			Date date = new Date();
			date.setTime((long)Integer.parseInt(end_date)*1000);
			return format.format(date);
		} else
			return "";
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	
}
