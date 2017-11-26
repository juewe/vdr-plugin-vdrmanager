package de.bjusystems.vdrmanager.data;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Vdr {

	/**
	 *
	 */
	@DatabaseField(columnName = "_id", generatedId = true)
	private Integer id;

	@DatabaseField(columnName = "name")
	private String name = "VDR";

	/**
	 * Use secure channel
	 */
	@DatabaseField(defaultValue = "false")
	private boolean secure;

	/** host name or ip */
	@DatabaseField
	private String host = "0.0.0.0";

	/** port */
	@DatabaseField
	private Integer port = 6420;

	/** Password */
	@DatabaseField
	private String password = "";

	/** should channels be filtered? */
	@DatabaseField
	private boolean filterChannels = false;

	/** last channel to retrieve */
	@DatabaseField
	private String channelFilter = "";

	/** Enable remote wakeup */
	@DatabaseField
	private boolean wakeupEnabled = false;

	/** URL of the wakeup script */
	@DatabaseField
	private String wakeupUrl = "";

	/** User for wakeup */
	@DatabaseField
	private String wakeupUser = "";

	/** Password for wakeup */
	@DatabaseField
	private String wakeupPassword = "";

	/**
	 * vdr mac for wol
	 *
	 * @since 0.2
	 */
	@DatabaseField
	private String mac = "";

	/**
	 * which wakeup method to use
	 *
	 * @since 0.2
	 *
	 */
	@DatabaseField
	private String wakeupMethod = "wol";

	/** Check for running VDR is enabled */
	@DatabaseField
	private boolean aliveCheckEnabled;

	/** Intervall for alive test */
	@DatabaseField
	private Integer aliveCheckInterval;

	/** Buffer before event */
	@DatabaseField
	private int timerPreMargin = 5;

	/** Buffer after event */
	@DatabaseField
	private int timerPostMargin = 30;

	/** Default priority */
	@DatabaseField
	private int timerDefaultPriority = 50;

	/** Default lifetime */
	@DatabaseField
	private int timerDefaultLifetime = 99;

	/** user defined epg search times */
	@DatabaseField
	private String epgSearchTimes = "";

	/**
	 * Which port to use for streaming
	 *
	 * @since 0.2
	 */
	@DatabaseField
	private int streamPort = 3000;

	/**
	 * Which format to use for streaming
	 *
	 * @since 0.2
	 */
	@DatabaseField
	private String streamFormat = "TS";

	/**
	 * Do not send broadcasts, send directly to the host (router problem)
	 *
	 * @since 0.2
	 */
	@DatabaseField
	private String wolCustomBroadcast = "255.255.255.255";

	/**
	 * Use remux ?
	 */
	@DatabaseField
	private boolean enableRemux = false;

	/**
	 * Remux command
	 */
	@DatabaseField
	private String remuxCommand = "EXT";

	/**
	 * Remux command Parameter
	 */
	@DatabaseField
	private String remuxParameter = "QUALITY=DSL1000";

	@DatabaseField
	private String encoding = "utf-8";

	/**
	 * Connection timeout
	 */
	@DatabaseField
	private int connectionTimeout = 10;

	/**
	 * Read Timeout
	 */
	@DatabaseField
	private int readTimeout = 10;

	/**
	 * Timeout for a whole command run
	 */
	@DatabaseField
	private int timeout = 60;

	@DatabaseField
	private String streamingUsername = "";

	@DatabaseField
	private String streamingPassword = "";

	@DatabaseField
	private int livePort = 8008;

	@DatabaseField
	private String recStreamMethod = "vdr-live";
	
	@DatabaseField(columnName="smarttvwebPort")
	private int smarttvwebPort = 8000;
	
	@DatabaseField(columnName="smarttvwebType")
	private String smarttvwebType="ts";

	@DatabaseField
	private boolean enableRecStreaming = false;

	@DatabaseField(columnName = "stz")
	private String serverTimeZone = "Europe/Berlin";

	@DatabaseField(columnName="svdrpHost")
	private String svdrpHost = "";

	@DatabaseField(columnName="svdrpPort")
	private int svdrpPort = 6419;
	
	@DatabaseField(columnName="enableRemote")
	private boolean enableRemote = true;
	
	public String getServerTimeZone() {
		return serverTimeZone;
	}

	public void setServerTimeZone(String serverTimeZone) {
		this.serverTimeZone = serverTimeZone;
	}

	public String getRecStreamMethod() {
		return recStreamMethod;
	}

	public void setRecStreamMethod(String recStreamMethod) {
		this.recStreamMethod = recStreamMethod;
	}

	public int getLivePort() {
		return livePort;
	}

	public void setLivePort(int livePort) {
		this.livePort = livePort;
	}

	public boolean isEnableRecStreaming() {
		return enableRecStreaming;
	}

	public void setEnableRecStreaming(boolean enableRecStreaming) {
		this.enableRecStreaming = enableRecStreaming;
	}

	public String getStreamingPassword() {
		return streamingPassword;
	}

	public void setStreamingPassword(String streamingPassword) {
		this.streamingPassword = streamingPassword;
	}

	public String getStreamingUsername() {
		return streamingUsername;
	}

	public void setStreamingUsername(String streamingUsername) {
		this.streamingUsername = streamingUsername;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSecure() {
		return secure;
	}

	public void setSecure(boolean secure) {
		this.secure = secure;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isFilterChannels() {
		return filterChannels;
	}

	public void setFilterChannels(boolean filterChannels) {
		this.filterChannels = filterChannels;
	}

	public String getChannelFilter() {
		return channelFilter;
	}

	public void setChannelFilter(String channelFilter) {
		this.channelFilter = channelFilter;
	}

	public boolean isWakeupEnabled() {
		return wakeupEnabled;
	}

	public void setWakeupEnabled(boolean wakeupEnabled) {
		this.wakeupEnabled = wakeupEnabled;
	}

	public String getWakeupUrl() {
		return wakeupUrl;
	}

	public void setWakeupUrl(String wakeupUrl) {
		this.wakeupUrl = wakeupUrl;
	}

	public String getWakeupUser() {
		return wakeupUser;
	}

	public void setWakeupUser(String wakeupUser) {
		this.wakeupUser = wakeupUser;
	}

	public String getWakeupPassword() {
		return wakeupPassword;
	}

	public void setWakeupPassword(String wakeupPassword) {
		this.wakeupPassword = wakeupPassword;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getWakeupMethod() {
		return wakeupMethod;
	}

	public void setWakeupMethod(String wakeupMethod) {
		this.wakeupMethod = wakeupMethod;
	}

	public boolean isAliveCheckEnabled() {
		return aliveCheckEnabled;
	}

	public void setAliveCheckEnabled(boolean aliveCheckEnabled) {
		this.aliveCheckEnabled = aliveCheckEnabled;
	}

	public int getAliveCheckInterval() {
		return aliveCheckInterval;
	}

	public void setAliveCheckInterval(int aliveCheckInterval) {
		this.aliveCheckInterval = aliveCheckInterval;
	}

	public int getTimerPreMargin() {
		return timerPreMargin;
	}

	public void setTimerPreMargin(int timerPreMargin) {
		this.timerPreMargin = timerPreMargin;
	}

	public int getTimerPostMargin() {
		return timerPostMargin;
	}

	public void setTimerPostMargin(int timerPostMargin) {
		this.timerPostMargin = timerPostMargin;
	}

	public int getTimerDefaultPriority() {
		return timerDefaultPriority;
	}

	public void setTimerDefaultPriority(int timerDefaultPriority) {
		this.timerDefaultPriority = timerDefaultPriority;
	}

	public int getTimerDefaultLifetime() {
		return timerDefaultLifetime;
	}

	public void setTimerDefaultLifetime(int timerDefaultLifetime) {
		this.timerDefaultLifetime = timerDefaultLifetime;
	}

	public String getEpgSearchTimes() {
		return epgSearchTimes;
	}

	public void setEpgSearchTimes(String epgSearchTimes) {
		this.epgSearchTimes = epgSearchTimes;
	}

	public int getStreamPort() {
		return streamPort;
	}

	public void setStreamPort(int streamPort) {
		this.streamPort = streamPort;
	}

	public String getStreamFormat() {
		return streamFormat;
	}

	public void setStreamFormat(String streamFormat) {
		this.streamFormat = streamFormat;
	}

	public String getWolCustomBroadcast() {
		return wolCustomBroadcast;
	}

	public void setWolCustomBroadcast(String wolCustomBroadcast) {
		this.wolCustomBroadcast = wolCustomBroadcast;
	}

	public boolean isEnableRemux() {
		return enableRemux;
	}

	public void setEnableRemux(boolean enableRemux) {
		this.enableRemux = enableRemux;
	}

	public String getRemuxCommand() {
		return remuxCommand;
	}

	public void setRemuxCommand(String remuxCommand) {
		this.remuxCommand = remuxCommand;
	}

	public String getRemuxParameter() {
		return remuxParameter;
	}

	public void setRemuxParameter(String remuxParameter) {
		this.remuxParameter = remuxParameter;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	private static <T> T get(Map<String, Object> map, String key) {
		return get(map, key, null);
	}

	private static <T> T get(Map<String, Object> map, String key, Object def) {
		if (map.containsKey(key)) {
			return (T) map.get(key);
		}
		return (T) def;
	}

	private static Integer getInteger(Map<String, Object> map, String key) {
		return getInteger(map, key, 0);
	}

	private static Integer getInteger(Map<String, Object> map, String key,
			Integer def) {
		if (map.containsKey(key) == false) {
			return def;
		}

		Object obj = get(map, key);
		if (obj instanceof Integer) {
			return (Integer) obj;
		}

		try {
			return Integer.valueOf(String.valueOf(obj));
		} catch (NumberFormatException nfe) {
			return def;
		}
	}

	// private static Integer getInteger(Map<String, Object> map, String key) {
	// if (map.containsKey(key) == false) {
	// return Integer.valueOf(0);
	// }

	// Object obj = get(map, key);
	// if (obj instanceof Integer) {
	// return (Integer) obj;
	// }
	// return Integer.valueOf(String.valueOf(obj));
	// }

	private static Boolean getBoolean(Map<String, Object> map, String key) {
		return getBoolean(map, key, false);
	}

	private static Boolean getBoolean(Map<String, Object> map, String key,
			boolean defValue) {
		if (map.containsKey(key) == false) {
			return defValue;
		}
		Object obj = get(map, key);
		if (obj instanceof Boolean) {
			return (Boolean) obj;
		}
		return Boolean.valueOf(String.valueOf(obj));
	}

	public HashMap<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("vdr_name", name);
		map.put("vdr_host", host);
		map.put("vdr_port", port);
		map.put("vdr_password", password);
		map.put("vdr_secure", secure);

		map.put("limit_channels", filterChannels);
		map.put("last_channel", channelFilter);

		map.put("key_wakeup_enabled", wakeupEnabled);
		map.put("key_wakeup_url", wakeupUrl);
		map.put("key_wakeup_user", wakeupUser);
		map.put("key_wakeup_password", wakeupPassword);
		map.put("key_wakeup_method", wakeupMethod);
		map.put("key_wol_custom_broadcast", wolCustomBroadcast);
		map.put("key_wakeup_wol_mac", mac);

		map.put("key_conntimeout_key", connectionTimeout);
		map.put("key_vdr_readtimeout", readTimeout);
		map.put("key_vdr_timeout", timeout);

		map.put("timer_pre_start_buffer", timerPreMargin);
		map.put("timer_post_end_buffer", timerPostMargin);
		map.put("timer_default_priority", timerDefaultPriority);
		map.put("timer_default_lifetime", timerDefaultLifetime);

		map.put("streamingport", streamPort);
		map.put("key_streaming_password", streamingPassword);
		map.put("key_streaming_username", streamingUsername);
		map.put("key_vdr_encoding", encoding);
		map.put("livetv_streamformat", streamFormat);
		map.put("remux_command", remuxCommand);
		map.put("remux_parameter", remuxParameter);
		map.put("remux_enable", enableRemux);
		map.put("key_rec_stream_enable", enableRecStreaming);
		map.put("key_live_port", livePort);
		map.put("key_recstream_method", recStreamMethod);
		map.put("key_timezone", serverTimeZone);
		
		
		map.put("key_smarttvweb_port", smarttvwebPort);
		map.put("key_smarttvweb_recstream_method", smarttvwebType);
		map.put("key_remote_enable", enableRemote);
		map.put("key_svdrp_host", svdrpHost);
		map.put("key_svdrp_port", svdrpPort);
		return map;
	}

	public void set(Map<String, Object> map) {
		init(map);
/*
		name = get(map, "vdr_name");
		host = get(map, "vdr_host");
		port = getInteger(map, "vdr_port");
		password = get(map, "vdr_password");
		secure = getBoolean(map, "vdr_secure");

		filterChannels = getBoolean(map, "limit_channels");
		channelFilter = get(map, "last_channel");

		wakeupEnabled = getBoolean(map, "key_wakeup_enabled");
		wakeupUrl = get(map, "key_wakeup_url");
		wakeupUser = get(map, "key_wakeup_user");
		wakeupPassword = get(map, "key_wakeup_password");
		wakeupMethod = get(map, "key_wakeup_method");
		wolCustomBroadcast = get(map, "key_wol_custom_broadcast");
		mac = get(map, "key_wakeup_wol_mac");

		connectionTimeout = getInteger(map, "key_conntimeout_key");
		readTimeout = getInteger(map, "key_vdr_readtimeout");
		timeout = getInteger(map, "key_vdr_timeout");

		timerPreMargin = getInteger(map, "timer_pre_start_buffer"
				);
		timerPostMargin = getInteger(map, "timer_post_end_buffer");
		timerDefaultPriority = getInteger(map, "timer_default_priority");
		timerDefaultLifetime = getInteger(map, "timer_default_lifetime");

		streamPort = getInteger(map, "streamingport");
		streamingPassword = get(map, "key_streaming_password");
		streamingUsername = get(map, "key_streaming_username");
		encoding = get(map, "key_vdr_encoding");
		streamFormat = get(map, "livetv_streamformat");
		remuxCommand = get(map, "remux_command");
		remuxParameter = get(map, "remux_parameter");
		enableRemux = getBoolean(map, "remux_enable");

		enableRecStreaming = getBoolean(map, "key_rec_stream_enable");
		livePort = getInteger(map, "key_live_port");
		recStreamMethod = get(map, "key_recstream_method");
		*/
	}

	public void init(Map<String, Object> map) {
		name = get(map, "vdr_name", "VDR");
		host = get(map, "vdr_host", "0.0.0.0");
		port = getInteger(map, "vdr_port", 6420);
		password = get(map, "vdr_password", "");
		secure = getBoolean(map, "vdr_secure");

		filterChannels = getBoolean(map, "limit_channels", true);
		channelFilter = get(map, "last_channel", "");

		wakeupEnabled = getBoolean(map, "key_wakeup_enabled", false);
		wakeupUrl = get(map, "key_wakeup_url", "");
		wakeupUser = get(map, "key_wakeup_user", "");
		wakeupPassword = get(map, "key_wakeup_password", "");
		wakeupMethod = get(map, "key_wakeup_method", "wol");
		wolCustomBroadcast = get(map, "key_wol_custom_broadcast", "");
		mac = get(map, "key_wakeup_wol_mac", "");

		connectionTimeout = getInteger(map, "key_conntimeout_key", 10);
		readTimeout = getInteger(map, "key_vdr_readtimeout", 10);
		timeout = getInteger(map, "key_vdr_timeout", 60);

		timerPreMargin = getInteger(map, "timer_pre_start_buffer", 5);
		timerPostMargin = getInteger(map, "timer_post_end_buffer", 30);
		timerDefaultPriority = getInteger(map, "timer_default_priority", 50);
		timerDefaultLifetime = getInteger(map, "timer_default_lifetime",99);

		streamPort = getInteger(map, "streamingport", 3000);
		streamingPassword = get(map, "key_streaming_password", "");
		streamingUsername = get(map, "key_streaming_username", "");
		encoding = get(map, "key_vdr_encoding", "utf-8");
		streamFormat = get(map, "livetv_streamformat", "TS");
		remuxCommand = get(map, "remux_command", "EXT");
		remuxParameter = get(map, "remux_parameter", "");
		enableRemux = getBoolean(map, "remux_enable");

		enableRecStreaming = getBoolean(map, "key_rec_stream_enable");
		livePort = getInteger(map, "key_live_port", 8008);
		recStreamMethod = get(map, "key_recstream_method", "vdr-live");
		serverTimeZone = get(map, "key_timezone", TimeZone.getDefault().getID());
		
		smarttvwebPort=  getInteger(map, "key_smarttvweb_port", 8000);
		smarttvwebType=  get(map, "key_smarttvweb_recstream_method", "progressive");
		enableRemote = getBoolean(map, "key_remote_enable", true);
		svdrpHost = get(map, "key_svdrp_host", "");
		svdrpPort = getInteger(map, "key_svdrp_port", 6419);
	}

	public int getSmarttvwebPort() {
		return smarttvwebPort;
	}

	public void setSmarttvwebPort(int smarttvwebPort) {
		this.smarttvwebPort = smarttvwebPort;
	}

	public String getSmarttvwebType() {
		return smarttvwebType;
	}

	public void setSmarttvwebType(String smarttvwebType) {
		this.smarttvwebType = smarttvwebType;
	}

	public String getSvdrpHost() {
		return svdrpHost;
	}

	public void setSvdrpHost(String svdrpHost) {
		this.svdrpHost = svdrpHost;
	}

	public int getSvdrpPort() {
		return svdrpPort;
	}

	public void setSvdrpPort(int svdrpPort) {
		this.svdrpPort = svdrpPort;
	}

	public boolean isEnableRemote() {
		return enableRemote;
	}

	public void setEnableRemote(boolean enableRemote) {
		this.enableRemote = enableRemote;
	}

}
