package utils;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;

/**
 * 关于路径的工具类
 * 
 * @author LeeFranker
 * 
 */

public class PathUtils {

	/**
	 * Environment.getExternalStorageState() 获取外部存储卡路径。
	 * 
	 * @return
	 */
	public static String getPathByAPI() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File file = Environment.getExternalStorageDirectory();
			if (file.exists() && file.canRead() && file.canWrite())
				return file.getPath();
		}
		return null;
	}

	/**
	 * 判断SD是否是外置SD卡，还是内部存储卡。
	 * 
	 * @return 2.3版本之后可以用这个方法。
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static boolean getIsSdInPhone() {
		// Android 2.3以下，没有没有这个方法。
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD)
			return false;
		return Environment.isExternalStorageRemovable();
	}

	/**
	 * 通过反射getVolumePath()放射，获取所有外部存储的路径。 注： 1.Android
	 * 3.2及以上，StorageManager才有getVolumePath()这个方法 2.Android
	 * 2.3及以上，才可以调用这个方法；否则返回null
	 * 
	 * @param context
	 * @return 如果是Android 2.3，返回null；否则当前返回所有外部存储的根路径
	 */
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static List<String> getPathByReflection(Context context) {
		// Android 2.3以下，没有StorageManager，所以无法执行下面的逻辑
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD)
			return null;
		StorageManager sm = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
		try {
			String[] paths = (String[]) sm.getClass().getMethod("getVolumePaths").invoke(sm);
			if (paths == null)
				return null;

			List<String> results = new ArrayList<String>();
			for (String path : paths) {
				File file = new File(path);
				boolean exist = file.exists();
				boolean canRead = file.canRead();
				boolean canWrite = file.canWrite();
				if (exist && canRead && canWrite)
					results.add(path);
			}
			return results;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
