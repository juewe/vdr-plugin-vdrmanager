package de.bjusystems.vdrmanager.data;

import java.util.Date;

import android.text.TextUtils;

/**
 * Basisc class for all Events 
 * 
 * @author bju,lado
 *
 */
public abstract class Event {

	protected String channelNumber;
	protected String channelId;
	protected String channelName;

	protected String title;
	protected String shortText;
	protected String description;
	protected Date start;
	protected Date stop;
	
	
	public long getDuration(){
		long millis = getStop().getTime() - getStart().getTime();
		return millis;
	}
	
	public Event(){
		
	}
	
	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setShortText(String shortText) {
		this.shortText = shortText;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setStop(Date stop) {
		this.stop = stop;
	}

	public Event(Event event) {
		channelNumber = event.getChannelNumber();
		channelName = event.getChannelName();
		title = event.getTitle();
		shortText = event.getShortText();
		description = event.getDescription();
		start = event.getStart();
		stop = event.getStop();
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public String getChannelName() {
		return channelName;
	}

	public String getTitle() {
		return title;
	}

	public String getChannelId() {
		return channelId;
	}


	public String getShortText() {
		if (TextUtils.isEmpty(shortText) == false) {
			return shortText;
		}
		if (TextUtils.isEmpty(description) == false) {
			if (description.length() < 30) {
				return description;
			}
			return TextUtils.substring(description, 0, 30) + "…";
		}
		return shortText;
	}

	public String getDescription() {
		return description;
	}

	public Date getStart() {
		return start;
	}

	public Date getStop() {
		return stop;
	}

	public String getStreamId(){
		if(channelId  != null){
			return channelId;
		}
		return channelNumber;
	}
	
}
