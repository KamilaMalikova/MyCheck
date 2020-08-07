package com.example.mycheck;

public class EasyDeviceInfo {
    /**
     * The Name.
     */
    public static final String nameOfLib = "EasyDeviceInfo";
    /**
     * The constant debuggable.
     */
    public static boolean debuggable = false;
    /**
     * The Not found val.
     */
    public static String notFoundVal = "unknown";

    private EasyDeviceInfo() {

    }

    /**
     * Instantiates a new Easy device info.
     *
     * @param notFoundVal
     *     the not found val
     */
    public static void setNotFoundVal(String notFoundVal) {
        EasyDeviceInfo.notFoundVal = notFoundVal;
    }

    /**
     * Instantiates a new Easy device info.
     *
     * @param notFoundVal
     *     the not found val
     * @param debugFlag
     *     the debug flag
     */
    public static void setConfigs(String notFoundVal, boolean debugFlag) {
        EasyDeviceInfo.notFoundVal = notFoundVal;
        EasyDeviceInfo.debuggable = debugFlag;
    }

    /**
     * Gets library version.
     *
     * @return the library version
     */
    public static String getLibraryVersion() {
        return nameOfLib + " : v" + BuildConfig.VERSION_NAME + " [build-v" + String.valueOf(
                BuildConfig.VERSION_CODE) + "]";
    }

    /**
     * Debug.
     */
    public static void debug() {
        EasyDeviceInfo.debuggable = true;
    }
}
