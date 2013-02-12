package de.bjusystems.vdrmanager.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import de.bjusystems.vdrmanager.StringUtils;
import de.bjusystems.vdrmanager.app.C;

@DatabaseTable
public class Channel implements Parcelable {

	
	@DatabaseField(id=true)
	String id;
	private int number;
	
	@DatabaseField
	private String name;
	
	@DatabaseField(index=true)
	private String provider;

	@DatabaseField
	private String rawAudio;
	
	@DatabaseField(index = true)
	private String group;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getRawAudio() {
		return rawAudio;
	}

	public Channel(final String channelData) {
		String[] words = StringUtils.splitPreserveAllTokens(channelData, C.DATA_SEPARATOR);
		this.number = Integer.valueOf(words[0].substring(1));
		if (words.length > 2) {
			this.name = words[1];
			this.provider = words[2];
			this.id = words[3];
			this.rawAudio = words[4];
		} else {
			this.name = words[1];
			this.id = "-1";
			this.provider = "Unknown";
			this.rawAudio = "";
		}
	}

	public Channel() {
		this.number = 0;
		this.name = "Unknown";
		this.provider = "Unknown";
		this.id = "Uknwon";
		this.rawAudio = "";
	}

	public Channel(Parcel in) {
		this.number = in.readInt();
		this.name = in.readString();
		this.provider = in.readString();
		this.id = in.readString();
		this.rawAudio = in.readString();
	}

	public boolean isGroupSeparator() {
		return number == 0;
	}

	public int getNumber() {
		return number;
	}

	public String getName() {
		return name;
	}

	public String getProvider() {
		return provider;
	}

	public String getId(){
		return id;
	}
	
	@Override
	public String toString() {
		final StringBuilder text = new StringBuilder();
		text.append(number);
		text.append(" - ");
		text.append(name);
		// text.append(" : ");
		// text.append(provider);
		return text.toString();
	}

	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(number);
		dest.writeString(name);
		dest.writeString(provider);
		dest.writeString(id);
		dest.writeString(rawAudio);

	}

	public static final Parcelable.Creator<Channel> CREATOR = new Parcelable.Creator<Channel>() {
		public Channel createFromParcel(Parcel in) {
			return new Channel(in);
		}

		public Channel[] newArray(int size) {
			return new Channel[size];
		}
	};
	
	public boolean equals(Object o) {
		if(o instanceof Channel == false){
			return false;
		}
		if(o == this){
			return true;
		}

		return number == ((Channel)o).getNumber();
	};
	
	@Override
	public int hashCode() {
		return number;
	}
	
}
