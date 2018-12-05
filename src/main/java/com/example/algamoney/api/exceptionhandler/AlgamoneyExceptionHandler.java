package com.example.algamoney.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AlgamoneyExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
	String mensajeUsuario =	messageSource.getMessage("mensaje.invalido", null, LocaleContextHolder.getLocale());
	
	String mensajeDesarrollador = ex.getCause().toString();
		
	return handleExceptionInternal(ex, new Error(mensajeUsuario,mensajeDesarrollador), headers, HttpStatus.BAD_REQUEST , request);	
	}
	
	public static class Error {
		private String mensajeUsuario;
		private String mensajeDesarrollador;
		
		public Error(String mensajeUsuario, String mensajeDesarrollador) {
			super();
			this.mensajeUsuario = mensajeUsuario;
			this.mensajeDesarrollador = mensajeDesarrollador;
		}

		public String getMensajeUsuario() {
			return mensajeUsuario;
		}

		public String getMensajeDesarrollador() {
			return mensajeDesarrollador;
		}
		
		
		
		
	}
	
}
