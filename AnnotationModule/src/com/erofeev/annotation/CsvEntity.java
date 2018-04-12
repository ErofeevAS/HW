package com.erofeev.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvEntity {
	String filename();
	String valuesSeparator();
	int entityId();

}
