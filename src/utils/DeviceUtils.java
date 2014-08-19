package utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * 关于android手机信息的帮助类
 * 
 * @author LeeFranker
 * 
 */
public class DeviceUtils {

	/**
	 * 获取手机型号
	 * 
	 * @return
	 */
	public static String getLocalModel() {
		return android.os.Build.MODEL;
	}

	/**
	 * 获取手机系统版本
	 * 
	 * @return
	 */
	public static String getLocalSystemVersion() {
		return android.os.Build.VERSION.RELEASE;
	}

	/**
	 * 客户端版本名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String version = "";
		try {
			version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return version;
	}

	/**
	 * 获取手机厂商
	 * 
	 * @return
	 */
	public static String getLocalManufacturer() {
		return android.os.Build.MANUFACTURER;
	}

	/**
	 * 判断设备是否越狱
	 * 
	 * @return
	 */
	public static boolean getIsJailBreak() {
		for (String str : new String[] { "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/" }) {
			if (new File(str + "su").exists()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取UserAgent
	 * 
	 * @param context
	 * @return 在主线程调用，记得有个别手机调用会堵塞导致ANR，不清楚原因。
	 */
	public static String getUserAgent(Context context) {
		String userAgent = null;
		try {
			WebView webView = new WebView(context);
			WebSettings settings = webView.getSettings();
			userAgent = settings.getUserAgentString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userAgent;
	}

	/**
	 * 格式化IP
	 * 
	 * @param context
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getUserIp(Context context) {
		int ip = getIpAddress(context);
		return String.format("%d.%d.%d.%d", (ip & 0xff), (ip >> 8 & 0xff), (ip >> 16 & 0xff), (ip >> 24 & 0xff));
	}

	/**
	 * 获取用户的IP
	 * 
	 * @param context
	 * @return
	 */
	public static int getIpAddress(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		int ipAddress = 0;
		try {
			WifiInfo wifiInfo = wifiManager.getConnectionInfo();
			ipAddress = wifiInfo.getIpAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ipAddress;
	}

	/**
	 * 获取andorid_id
	 * 
	 * @param activity
	 * @return
	 */
	public static String getAndroidId(Context context) {
		return Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
	}

	/**
	 * 获取包名
	 * 
	 * @param activity
	 * @return
	 */
	public static String getPackageName(Context context) {
		String packageName = null;
		try {
			packageName = context.getPackageName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return packageName;
	}

	/**
	 * 手机IMEI
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalDeviceId(Context context) {
		String imie = null;
		try {
			TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			imie = tm.getDeviceId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return imie;
	}

	/**
	 * 获取手机MAC地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalMacAddress(Context context) {
		String mac = null;
		try {
			WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
			WifiInfo info = wm.getConnectionInfo();
			mac = info.getMacAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mac;
	}

	/**
	 * 获取屏幕宽度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenWidth(Context context) {
		int widthPixels = 0;
		try {
			widthPixels = context.getResources().getDisplayMetrics().widthPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getScreenHeight(Context context) {
		int heightPixels = 0;
		try {
			heightPixels = context.getResources().getDisplayMetrics().heightPixels;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return heightPixels;
	}

	/**
	 * 获取获取CPU最大频率（单位KHZ）
	 * 
	 * @return
	 */
	public static String getMaxCpuFreq() {
		String result = null;
		try {
			File file = new File("/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");
			if (file.exists() && file.canRead()) {
				FileInputStream fis = new FileInputStream(file);
				result = getStrFromInput(fis).trim();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			result = "N/A";
		}
		return result;
	}

	/**
	 * 流转字符串
	 * 
	 * @param input
	 * @return
	 */
	private static String getStrFromInput(InputStream input) {
		String result = null;
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		try {
			int size = -1;
			while ((size = input.read()) != -1) {
				byteStream.write(size);
			}
			byteStream.flush();
			result = byteStream.toString();
			byteStream.close();
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取cup数目
	 * 
	 * @return
	 */
	public static int getCPUSize() {
		class CpuFilter implements FileFilter {
			@Override
			public boolean accept(File pathname) {
				if (Pattern.matches("cpu[0-9]", pathname.getName())) {
					return true;
				}
				return false;
			}
		}
		try {
			File dir = new File("/sys/devices/system/cpu/");
			File[] files = dir.listFiles(new CpuFilter());
			return files.length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}
}
