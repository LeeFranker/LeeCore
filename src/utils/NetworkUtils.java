package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 关于android手机网络状态的工具类
 * 
 * @author LeeFranker
 * 
 */
public class NetworkUtils {
	/**
	 * 判断是否是网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getIsNetworkConnected(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断是否是WIFI网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getIsWifiConnected(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				if (info.getType() == ConnectivityManager.TYPE_WIFI) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 判断是否是手机网络连接
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getIsMobileConnected(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo info = cm.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
