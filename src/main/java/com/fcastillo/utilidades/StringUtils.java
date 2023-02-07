package com.fcastillo.utilidades;

import org.apache.commons.text.CaseUtils;

/**
 *
 * @author fcastillo
 */
public class StringUtils {

    public static String toCamelCase(String text) {
	return CaseUtils.toCamelCase(text, true, ' ');
    }
}
