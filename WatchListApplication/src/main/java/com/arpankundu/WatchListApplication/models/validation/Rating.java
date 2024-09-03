package com.arpankundu.WatchListApplication.models.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RatingAnnotationLogic.class)
@Documented
public @interface Rating {

	String message() default "Please Enter Value from 1 to 10!!";
	
	Class<?>[]groups() default{};
	Class<? extends Payload>[] payload() default{};
}
