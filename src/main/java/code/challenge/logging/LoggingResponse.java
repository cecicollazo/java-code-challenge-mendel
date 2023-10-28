package code.challenge.logging;

import jakarta.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingResponse {

    private final Logger LOG = LogManager.getLogger(this.getClass());

    public void display(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder message = new StringBuilder();
        message.append("\r\nMethod = [").append(request.getMethod()).append("]");
        message.append("\r\nStatus = [").append(response.getStatus()).append("]");
        try {
            message.append("\r\nBody = [").append(new ObjectMapper().writeValueAsString(body)).append("]");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        LOG.info("\r\nResponse: [" + message + "\r\n]");
    }

}
