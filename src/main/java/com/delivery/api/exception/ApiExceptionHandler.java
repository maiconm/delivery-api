package com.delivery.api.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(NotFoundException.class)
	protected ResponseEntity<Object> tratarNotFoundException(NotFoundException ex, WebRequest request) {
		
		ErroGenerico erro = new ErroGenerico();
		
		erro.setErro(ex.getMessage());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	@ExceptionHandler(ConflictException.class)
	protected ResponseEntity<Object> tratarConflictException(ConflictException ex, WebRequest request) {
		
		ErroGenerico erro = new ErroGenerico();
		
		erro.setErro(ex.getMessage());
		
		return handleExceptionInternal(ex, erro, new HttpHeaders(), HttpStatus.CONFLICT, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		String mensagem = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente";
		
		return handleValidationInternal(ex, request, mensagem, ex.getBindingResult());
		
	}
	
	private ResponseEntity<Object> handleValidationInternal(Exception ex, WebRequest request,
			String informacao, BindingResult bindingResult) {
		
		List<ApiValidationExceptionResponse.Erro> erros = bindingResult.getFieldErrors().stream()
			.map(campo -> {
				
				String mensagem = messageSource.getMessage(campo, LocaleContextHolder.getLocale());
				
				ApiValidationExceptionResponse.Erro erro = new ApiValidationExceptionResponse.Erro();
				
				erro.setCampo(campo.getField());
				
				erro.setErro(mensagem);
				
				return erro;
				
			}).collect(Collectors.toList());
		
		ApiValidationExceptionResponse resposta = new ApiValidationExceptionResponse();
		
		resposta.setStatus(HttpStatus.BAD_REQUEST.value());
		
		resposta.setMensagem(informacao);
		
		resposta.setValidacoes(erros);
		
		return handleExceptionInternal(ex, resposta, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
}
