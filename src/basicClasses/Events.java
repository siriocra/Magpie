package basicClasses;

import java.util.ArrayList;
import java.util.Collection;


public class Events {
	private Collection<VKEvent> events;
	
	public Collection<VKEvent> getEvents() {
		return events;
	}

	public Events(String eventsIDs) {
		events = new ArrayList<VKEvent>();
		String[] groupList = eventsIDs.split("<group>");
		for (String group : groupList){
			if (group.contains("</group>")){
				String s = group.split("</group>")[0];
				VKEvent ev = new VKEvent(s);
				events.add(ev);
			}
		}
	}

}
