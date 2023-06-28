package com.blogApplication.exception;

import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogApplication.apiresponce.ApiResponce;
@RestControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(ResourseNotFoundException.class)
	public ResponseEntity<ApiResponce>ResourseNotFoundException(ResourseNotFoundException e){
		String Message=e.getMessage();
		ApiResponce apiResponce=new ApiResponce(Message,false);
		
		return new ResponseEntity<ApiResponce>(apiResponce,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>MethodArgumentNotValidException(org.springframework.web.bind.MethodArgumentNotValidException e){
		Map<String, String>exceptionMap= new HashMap<>();
		
		e.getBindingResult().getAllErrors().forEach((ex)->{
			String fieldName=((FieldError)ex).getField();
			String message=ex.getDefaultMessage();
			exceptionMap.put(fieldName, message);
			
		
		
		});
		return new ResponseEntity<Map<String,String>>(exceptionMap,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(SecurityException.class)
	public ResponseEntity<ApiResponce>SecurityExceptionHandeler(SecurityException securityException){
		String message=securityException.getMessage();
		return new ResponseEntity<ApiResponce>(new ApiResponce(message,false),HttpStatus.BAD_REQUEST);
	}
	

}
