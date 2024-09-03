package com.arpankundu.WatchListApplication.models.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingAnnotationLogic implements ConstraintValidator<Rating, Float>{

	@Override
	public boolean isValid(Float value, ConstraintValidatorContext context) {
		return value>=1 && value<=10;
	}

}
