package de.bjusystems.vdrmanager.data.db;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import de.bjusystems.vdrmanager.data.Vdr;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class OrmDatabaseHelper extends OrmLiteSqliteOpenHelper {

	// name of the database file for your application -- change to something appropriate for your app
	private static final String DATABASE_NAME = "vdrmanager.db";
	// any time you make changes to your database objects, you may have to increase the database version
	private static final int DATABASE_VERSION = 1;

	
	private RuntimeExceptionDao<Vdr, Integer> vdrDAO = null;

	
	public OrmDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * This is called when the database is first created. Usually you should call createTable statements here to create
	 * the tables that will store your data.
	 */
	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			Log.i(OrmDatabaseHelper.class.getName(), "onCreate");
			TableUtils.createTable(connectionSource, Vdr.class);
		} catch (SQLException e) {
			Log.e(OrmDatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}

//		// here we try inserting data in the on-create as a test
//		RuntimeExceptionDao<Note, Integer> dao = getSimpleDataDao();
//		long millis = System.currentTimeMillis();
//		// create some entries in the onCreate
//		Note simple = new Note();
//		dao.create(simple);
//		simple = new SimpleData(millis + 1);
//		dao.create(simple);
//		Log.i(DatabaseHelper.class.getName(), "created new entries in onCreate: " + millis);
	}

	/**
	 * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
	 * the various data to match the new version number.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			Log.i(OrmDatabaseHelper.class.getName(), "onUpgrade");
			TableUtils.dropTable(connectionSource, Vdr.class, true);
			// after we drop the old databases, we create the new ones
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(OrmDatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}


	
	/**
	 * Returns the Database Access Object (DAO) for our Label class. It will create it or just give the cached
	 * value.
	 */
	public RuntimeExceptionDao<Vdr, Integer> getVDRDAO() {
		if (vdrDAO == null) {
			vdrDAO = getRuntimeExceptionDao(Vdr.class);
		}
		return vdrDAO;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		vdrDAO = null;
	}
}