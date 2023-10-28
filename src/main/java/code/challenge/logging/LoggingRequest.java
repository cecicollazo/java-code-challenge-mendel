package code.challenge.logging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoggingRequest {

    private final Logger LOG = LogManager.getLogger(this.getClass());

    public void display(HttpServletRequest request, Object body) {
        StringBuilder message = new StringBuilder();
        message.append("\r\nMethod = [").append(request.getMethod()).append("]");
        message.append("\r\nPath = [").append(request.getRequestURI()).append("] ");
        if(!Objects.isNull(body)) {
            try {
                message.append("\r\nBody = [").append(new ObjectMapper().writeValueAsString(body)).append("]");
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        LOG.info("\r\nRequest: [" + message + "\r\n]");
    }
}
