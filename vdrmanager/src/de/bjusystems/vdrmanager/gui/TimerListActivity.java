package de.bjusystems.vdrmanager.gui;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import de.bjusystems.vdrmanager.R;
import de.bjusystems.vdrmanager.app.VdrManagerApp;
import de.bjusystems.vdrmanager.data.EventFormatter;
import de.bjusystems.vdrmanager.data.EventListItem;
import de.bjusystems.vdrmanager.data.Timer;
import de.bjusystems.vdrmanager.tasks.DeleteTimerTask;
import de.bjusystems.vdrmanager.tasks.ToggleTimerTask;
import de.bjusystems.vdrmanager.utils.svdrp.SvdrpAsyncListener;
import de.bjusystems.vdrmanager.utils.svdrp.SvdrpAsyncTask;
import de.bjusystems.vdrmanager.utils.svdrp.SvdrpClient;
import de.bjusystems.vdrmanager.utils.svdrp.SvdrpEvent;
import de.bjusystems.vdrmanager.utils.svdrp.SvdrpException;
import de.bjusystems.vdrmanager.utils.svdrp.TimerClient;

/**
 * This class is used for showing what's current running on all channels
 * 
 * @author bju
 */
public class TimerListActivity extends BaseActivity implements
		OnItemClickListener, SvdrpAsyncListener<Timer> {

	
	private static final int REQUEST_CODE_TIMED_EDIT = 41;
	
	TimerClient timerClient;
	EventAdapter adapter;
	SvdrpProgressDialog progress;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set title
		setTitle(R.string.action_menu_timers);

		// Attach view
		setContentView(getMainLayout());

		// create an adapter
		adapter = new TimeEventAdapter(this);

		// attach adapter to ListView
		final ListView listView = (ListView) findViewById(R.id.timer_list);
		listView.setAdapter(adapter);

		// set click listener
		listView.setOnItemClickListener(this);

		// context menu wanted
		registerForContextMenu(listView);

		// start query
		startTimerQuery();
	}

	@Override
	protected void onResume() {
		super.onResume();
		reloadIfNeeded();
	}

	private void reloadIfNeeded() {

		final VdrManagerApp app = (VdrManagerApp) getApplication();
		if (app.isReload()) {
			app.setReload(false);
			startTimerQuery();
		}
	}

	protected void updateWindowTitle(int topic, int subtopic) {
		String title;
		title = getString(topic);
		if (subtopic != -1) {
			title += " > " + getString(subtopic);
		}
		setTitle(title);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (timerClient != null) {
			timerClient.abort();
		}
		if (progress != null) {
			progress.dismiss();
			progress = null;
		}
	}

	@Override
	public void onCreateContextMenu(final ContextMenu menu, final View v,
			final ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);

		if (v.getId() == R.id.timer_list) {
			final MenuInflater inflater = getMenuInflater();
			final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

			// set menu title
			final EventListItem item = adapter.getItem(info.position);
			final EventFormatter formatter = new EventFormatter(item);
			menu.setHeaderTitle(formatter.getTitle());

			inflater.inflate(R.menu.epg_list_item_menu, menu);

			// remove unneeded menu items
			menu.findItem(R.id.epg_item_menu_timer_add).setVisible(false);
			final MenuItem enableMenuItem = menu
					.findItem(R.id.epg_item_menu_timer_toggle);
			enableMenuItem
					.setTitle(item.getTimer().isEnabled() ? R.string.epg_item_menu_timer_disable
							: R.string.epg_item_menu_timer_enable);
		}
	}
//
//	@Override
//	public boolean onContextItemSelected(final MenuItem item) {
//
//		final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
//				.getMenuInfo();
//		final EventListItem event = adapter.getItem(info.position);
//
//		switch (item.getItemId()) {
//		case R.id.epg_item_menu_timer_modify: {
//			onItemClick(null, null, info.position, 0);
//			break;
//		}
//		case R.id.epg_item_menu_timer_delete: {
//			deleteTimer(event);
//			break;
//		}
//		case R.id.epg_item_menu_timer_toggle: {
//			toggleTimer(event);
//		}
//		}
//
//		return true;
//	}

	public void onItemClick(final AdapterView<?> parent, final View view,
			final int position, final long id) {

		// save selected item
		final Timer timer = adapter.getItem(position).getTimer();
		if (timer == null) {
			// header click
			return;
		}


		//timer.getChannelName()
		//timer.getEvent();

		// show details
		final Intent intent = new Intent();
		intent.setClass(this, EpgDetailsActivity.class);
		startActivity(intent);
	}

	private void startTimerQuery() {

		// get timer client
		timerClient = new TimerClient();

		// create backgound task
		final SvdrpAsyncTask<Timer, SvdrpClient<Timer>> task = new SvdrpAsyncTask<Timer, SvdrpClient<Timer>>(
				timerClient);

		// create progress dialog
		progress = new SvdrpProgressDialog(this, timerClient);

		// attach listener
		task.addListener(this);

		// start task
		task.run();
	}

	public void svdrpEvent(final SvdrpEvent event, final Timer result) {

		if (progress != null) {
			progress.svdrpEvent(event);
		}

		switch (event) {
		case CONNECTING:
			break;
		case FINISHED_ABNORMALY:
		case CONNECT_ERROR:
			switchNoConnection();// TODO pass arg, what is the problem
		case LOGIN_ERROR:
			progress.dismiss();
			switchNoConnection();
			break;
		case FINISHED_SUCCESS:
			adapter.clear();
			for (final Timer timer : timerClient.getResults()) {
				adapter.add(new EventListItem(timer));
			}
			// adapter.sortItems();
			progress.dismiss();
			progress = null;
			if (timerClient.getResults().isEmpty()) {
				Toast.makeText(TimerListActivity.this, R.string.epg_no_items,
						Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}

	public void svdrpException(final SvdrpException exception) {
		if (progress != null) {
			progress.svdrpException(exception);
		}
	}

	private void deleteTimer(final EventListItem item) {

		final DeleteTimerTask task = new DeleteTimerTask(this, item.getTimer()) {
			@Override
			public void finished() {
				// refresh epg list after return
				final VdrManagerApp app = (VdrManagerApp) getApplication();
				app.setReload(true);
				reloadIfNeeded();
			}
		};
		task.start();
	}

	@Override
	public boolean onContextItemSelected(final MenuItem item) {

		final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();
		final EventListItem event = adapter.getItem(info.position);

		switch (item.getItemId()) {
		case R.id.epg_item_menu_timer_add:
		case R.id.epg_item_menu_timer_modify: {
			//prepareTimer(event);
			final Intent intent = new Intent();
			intent.setClass(this, TimerDetailsActivity.class);
			startActivityForResult(intent, REQUEST_CODE_TIMED_EDIT);
			break;
		}
		case R.id.epg_item_menu_timer_delete: {
			deleteTimer(event);
			break;
		}
		case R.id.epg_item_menu_timer_toggle: {
			toggleTimer(event);
			break;
		}
		case R.id.epg_item_menu_live_tv: {
			Utils.stream(this, event.getEvent());
			break;

		}
		}

		return true;
	}
	
	
	private void toggleTimer(final EventListItem item) {

		final ToggleTimerTask task = new ToggleTimerTask(this, item.getTimer()) {
			@Override
			public void finished() {
				// refresh epg list after return
				final VdrManagerApp app = (VdrManagerApp) getApplication();
				app.setReload(true);
				reloadIfNeeded();
			}
		};
		task.start();
	}

	protected void retry() {
		startTimerQuery();
	}

	protected void refresh() {
		startTimerQuery();
	}

	@Override
	protected int getMainLayout() {
		return R.layout.timer_list;
	}

}
