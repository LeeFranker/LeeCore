package utils;

import android.os.Build;

public class APIUtils {

	// 2.2
	public static boolean hasFroyo() {
		return Build.VERSION.SDK_INT >= 8;
	}

	// 2.3
	public static boolean hasGingerbread() {
		return Build.VERSION.SDK_INT >= 9;
	}

	// 3.0
	public static boolean hasHoneycomb() {
		return Build.VERSION.SDK_INT >= 11;
	}

	// 3.1
	public static boolean hasHoneycombMR1() {
		return Build.VERSION.SDK_INT >= 12;
	}

	// 4.2
	public static boolean hasJELLY_BEAN_4_2() {
		return Build.VERSION.SDK_INT >= 17;
	}

	// 4.4
	public static boolean hasKitKat() {
		return Build.VERSION.SDK_INT >= 19;
	}

}
