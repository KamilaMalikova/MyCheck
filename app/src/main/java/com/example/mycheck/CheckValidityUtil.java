package com.example.mycheck;

final class CheckValidityUtil {

    private CheckValidityUtil() {
        // private constructor for utility class
    }

    /**
     * Handle illegal character in result string.
     *
     * @param result
     *     the result
     *
     * @return the string
     */
    static String handleIllegalCharacterInResult(final String result) {
        String tempResult = result;
        if (tempResult != null && tempResult.contains(" ")) {
            tempResult = tempResult.replaceAll(" ", "_");
        }
        return tempResult;
    }

    /**
     * Check valid data string.
     *
     * @param data
     *     the data
     *
     * @return the string
     */
    static String checkValidData(final String data) {
        String tempData = data;
        if (tempData == null || tempData.length() == 0) {
            tempData = EasyDeviceInfo.notFoundVal;
        }
        return tempData;
    }

    /**
     * Check valid data string [ ].
     *
     * @param data
     *     the data
     *
     * @return the string [ ]
     */
    static String[] checkValidData(final String[] data) {
        String[] tempData = data;
        if (tempData == null || tempData.length == 0) {
            tempData = new String[] { EasyDeviceInfo.notFoundVal };
        }
        return tempData;
    }
}

