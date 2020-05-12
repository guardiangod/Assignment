package com.ryan.asmt.exception;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ryan.asmt.model.CommonResponse;
import com.ryan.asmt.util.Constants;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, 
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		StringBuilder errors = new StringBuilder();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.append(error.getField() + ": " + error.getDefaultMessage()).append(", ");
		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			errors.append(error.getObjectName() + ": " + error.getDefaultMessage()).append(", ");
		}
		
		final CommonResponse apiResponse  = new CommonResponse(Constants.INVALID_METHOD_ARGUMENT, errors.toString());
		return handleExceptionInternal(ex, apiResponse, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, 
    		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		StringBuilder errors = new StringBuilder();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(error.getField() + ": " + error.getDefaultMessage()).append(", ");
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.append(error.getObjectName() + ": " + error.getDefaultMessage()).append(", ");
        }
        
        final CommonResponse apiResponse  = new CommonResponse(Constants.INVALID_REQUEST, errors.toString());
        return handleExceptionInternal(ex, apiResponse, headers, HttpStatus.BAD_REQUEST, request);
    }
	
	@Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, 
    		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

        final CommonResponse apiResponse  = new CommonResponse(Constants.TYPE_MISMATCH, error);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex, 
    		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = ex.getRequestPartName() + " part is missing";
        
        final CommonResponse apiResponse  = new CommonResponse(Constants.INVALID_REQUEST, error);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
	
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex, 
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = ex.getParameterName() + " parameter is missing";

		final CommonResponse apiResponse  = new CommonResponse(Constants.MISSING_REQUEST_PARAM, error);
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex, 
    		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        final CommonResponse apiResponse  = new CommonResponse(ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
	
	@Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, 
    		final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final CommonResponse apiResponse  = new CommonResponse(Constants.UNSUPPORTED_METHOD, builder.toString());
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex, 
			final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));
		
		final CommonResponse apiResponse  = new CommonResponse(Constants.UNSUPPORTED_MEDIA_TYPE, builder.toString());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, 
			final WebRequest request) {
		final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		
		final CommonResponse apiResponse  = new CommonResponse(Constants.INVALID_METHOD_ARGUMENT, error);
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, 
			final WebRequest request) {
		final StringBuilder errors = new StringBuilder();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errors.append(violation.getRootBeanClass().getName() + " ")
					.append(violation.getPropertyPath() + ": ")
					.append(violation.getMessage()).append(", ");
		}

		final CommonResponse apiResponse  = new CommonResponse(Constants.INVALID_REQUEST, errors.toString());
		return new ResponseEntity<Object>(apiResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({ Exception.class })
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		CommonResponse apiResponse  = new CommonResponse(Constants.GENERIC_ERROR_MESSAGE, request.getDescription(false));
		return new ResponseEntity<Object>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
