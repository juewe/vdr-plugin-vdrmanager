package de.bjusystems.vdrmanager.data;

import java.util.Date;


import de.bjusystems.vdrmanager.StringUtils;
import de.bjusystems.vdrmanager.app.C;

import static de.bjusystems.vdrmanager.gui.Utils.mapSpecialChars;

/**
 * Class for EPG events
 * @author bju
 */
public class Epg extends Event implements Timerable {

	private Timer timer;

	public Epg(final String line) {
		final String[] words = StringUtils.splitPreserveAllTokens(line, C.DATA_SEPARATOR);
		channelNumber = words[0].substring(1);
		channelName = words[1];
		start = new Date(Long.parseLong(words[2])*1000);
		stop = new Date(Long.parseLong(words[3])*1000);
		title = mapSpecialChars(words[4]);
		description = words.length > 5 ? mapSpecialChars(words[5]): "";
		shortText = words.length > 6 ? mapSpecialChars(words[6]) : "";
		channelId = words.length > 7 ? mapSpecialChars(words[7]) : "";
	}
	

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(final Timer timer) {
		this.timer = timer;
	}

	public TimerState getTimerState() {
		if (timer == null) {
			return TimerState.None;
		} else {
			return timer.getTimerState();
		}
	}
	
	public Timer createTimer() {
		return new Timer(this);
	}
}
