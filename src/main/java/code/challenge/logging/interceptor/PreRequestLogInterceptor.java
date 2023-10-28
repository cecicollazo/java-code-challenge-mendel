package code.challenge.logging.interceptor;

import code.challenge.logging.LoggingRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class PreRequestLogInterceptor implements HandlerInterceptor {

    private LoggingRequest loggingRequest;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getMethod().equals(HttpMethod.GET.name())) {
            loggingRequest.display(request,null);
        }
        return true;
    }

    @Autowired
    public void setLoggingRequest(LoggingRequest loggingRequest) {
        this.loggingRequest = loggingRequest;
    }

}
