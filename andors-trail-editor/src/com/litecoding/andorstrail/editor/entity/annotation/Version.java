package com.litecoding.andorstrail.editor.entity.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;;

@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
	int ver() default 25;
}
