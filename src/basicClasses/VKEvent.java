package basicClasses;

public class VKEvent {
	private String name;
	
	public VKEvent(String xml){
		if (xml.contains("<name>")){
			name = xml.split("<name>")[1].split("</name>")[0];		
		} else {
			name = "No such group";
		}
	}

	public String getName() {
		return name;
	}
}
