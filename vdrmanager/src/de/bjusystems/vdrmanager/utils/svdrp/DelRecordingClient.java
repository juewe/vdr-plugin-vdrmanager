package de.bjusystems.vdrmanager.utils.svdrp;

import de.bjusystems.vdrmanager.R;
import de.bjusystems.vdrmanager.data.Recording;

/**
 * Class for deleting a record
 * @author lado
 *
 */
public class DelRecordingClient extends SvdrpClient<Recording> {

	/** current recording */
	Recording recording;

	/**
	 * Constructor
	 * Recording	
	 */
	public DelRecordingClient(final Recording recording) {
		super();
		this.recording = recording;
	}

	/**
	 * Starts the request
	 */
	@Override
	public void run() throws SvdrpException {

		final StringBuilder command = new StringBuilder();

		command.append("drecording ");
		command.append(recording.toCommandLine());
		runCommand(command.toString());
	}


	@Override
	public int getProgressTextId() {
		return R.string.progress_timer_save;
	}

	@Override
	protected Recording parseAnswer(String line) {
		return null;
	}
}
