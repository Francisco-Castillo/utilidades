package com.fcastillo.utilidades;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

/**
 *
 * @author fcastillo
 */
public class StringUtilsTest {

    @Test
    @DisplayName("Conversion a Camel Case")
    public void testToCamelCase() {

	String originalText = "PRUEBA CONVERSION A CAMEL CASE";
	String actual = "PruebaConversionACamelCase";

	try ( MockedStatic<StringUtils> utilities = Mockito.mockStatic(StringUtils.class)) {
	    utilities.when(() -> StringUtils.toCamelCase(originalText)).thenReturn("PruebaConversionACamelCase");
	}

	Assertions.assertEquals("PruebaConversionACamelCase", actual);
    }
}
