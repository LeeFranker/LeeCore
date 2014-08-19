package utils;

import android.content.Context;
import android.content.res.Resources;

public class ResourcesTool {

	private static final String DRAWABLE = "drawable";

	private static final String STRING = "string";

	private static final String STYLE = "style";

	private static final String LAYOUT = "layout";

	private static final String ID = "id";

	private static final String COLOR = "color";

	private static final String RAW = "raw";

	private static final String ANIM = "anim";

	private static final String ATTR = "attr";

	private static String mPackageName;

	private static Resources mResources;

	public static void init(Context context) {
		mPackageName = context.getPackageName();
		mResources = context.getResources();
	}

	/**
	 *  获取主包资源的ID
	 * 
	 * @param sourceName
	 * @param sourceType
	 * @return
	 */
	private static int getResourceId(String sourceName, String sourceType) {
		if (mResources == null)
			return -1;
		else
			return mResources.getIdentifier(sourceName, sourceType, mPackageName);
	}

	public static int getResouceIdForString(String sourceName) {
		return getResourceId(sourceName, STRING);
	}

	public static int getResouceIdForID(String sourceName) {
		return getResourceId(sourceName, ID);
	}

	public static int getResouceIdForLayout(String sourceName) {
		return getResourceId(sourceName, LAYOUT);
	}

	public static int getResouceIdForDrawable(String sourceName) {
		return getResourceId(sourceName, DRAWABLE);
	}

	public static int getResouceIdForStyle(String sourceName) {
		return getResourceId(sourceName, STYLE);
	}

	public static int getResouceIdForColor(String sourceName) {
		return getResourceId(sourceName, COLOR);
	}

	public static int getResouceIdForRaw(String sourceName) {
		return getResourceId(sourceName, RAW);
	}

	public static int getResouceIdForAnim(String sourceName) {
		return getResourceId(sourceName, ANIM);
	}

	public static int getResouceIdForAttr(String sourceName) {
		return getResourceId(sourceName, ATTR);
	}
}
