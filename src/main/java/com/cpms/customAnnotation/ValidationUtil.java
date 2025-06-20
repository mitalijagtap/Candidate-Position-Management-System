package com.cpms.customAnnotation;

import org.apache.commons.lang3.StringUtils;

public class ValidationUtil {

    public static boolean isBlank(final CharSequence cs) {
        return StringUtils.isBlank(cs);
    }

}
