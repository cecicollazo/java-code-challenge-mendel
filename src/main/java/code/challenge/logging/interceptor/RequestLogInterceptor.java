package code.challenge.logging.interceptor;

import code.challenge.logging.LoggingRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@ControllerAdvice
public class RequestLogInterceptor extends RequestBodyAdviceAdapter {

    private LoggingRequest loggingRequest;
    private HttpServletRequest request;

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter,
                                Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        loggingRequest.display(request, body);
        return super.afterBodyRead(body, inputMessage, parameter, targetType, converterType);
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Autowired
    public void setDisplayLogRequest(LoggingRequest loggingRequest) {
        this.loggingRequest = loggingRequest;
    }

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }
}
