package com.erofeev.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import com.erofeev.annotation.PropertyType;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {	
	 public PropertyType propertyType();
	 int columnNumber();
	 boolean escape();
	 int keyField() default -1;
	 
}
