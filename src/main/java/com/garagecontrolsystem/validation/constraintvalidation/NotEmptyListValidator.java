//package com.garagecontrolsystem.validation.constraintvalidation;
//
//import java.util.List;
//
//import javax.validation.ConstraintValidator;
//import javax.validation.ConstraintValidatorContext;
//
//import org.springframework.context.annotation.Configuration;
//
//import com.garagecontrolsystem.validation.NotEmptyList;
//
//@Configuration
//public class NotEmptyListValidator 
//		implements ConstraintValidator<NotEmptyList, List>{
//
//	@Override
//	public boolean isValid(List list, ConstraintValidatorContext constraintValidatorContext) {
//	
//		return list != null && !list.isEmpty();
//	}
//
//	@Override
//	public void initialize( NotEmptyList constraintAnnotation ) {
//	
//	}
//
//}
