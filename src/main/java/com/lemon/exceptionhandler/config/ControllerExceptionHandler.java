package com.lemon.exceptionhandler.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(value = { MethodArgumentNotValidException.class})
	public ModelAndView methodArgumentNotValidExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException e){
		String message=e.getBindingResult().getFieldError().getDefaultMessage();
		boolean isJsonRequest =isAjaxRequest(request);
		if (isJsonRequest) {
			ModelAndView view = new ModelAndView();
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			view.setView(jsonView);
			return view;
		} else {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/503.html");
			return view;
		}
	}


	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class })
	public ModelAndView methodNotSupportedException(HttpServletRequest request, HttpRequestMethodNotSupportedException e){
		LOGGER.error("HttpRequestMethodNotSupportedException:请求方法不支持:"+e.getMethod()+"\n请求地址："
				+request.getRequestURI()+"\n UA:\n" + request.getHeader("User-Agent"),getClass());
		boolean isJsonRequest =isAjaxRequest(request);
		if (isJsonRequest) {
			ModelAndView view = new ModelAndView();
			MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
			view.setView(jsonView);
			return view;
		} else {
			ModelAndView view = new ModelAndView();
			view.setViewName("redirect:/404.html");
			return view;
		}
	}





	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("x-requested-with");
		if (requestedWith != null && requestedWith.equalsIgnoreCase("XMLHttpRequest")) {
			return true;
		} else {
			return false;
		}
	}
}
