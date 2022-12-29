package id.co.mandiri.parkinglot.frontend.controller.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class HandlerUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public void writeAsJson(Object data, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(objectMapper.writeValueAsString(data));
        out.flush();
    }

    public void sendResponse(String message, Integer loginRetry, HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put("message", message);
        if (loginRetry != null) {
            data.put("triedLogin", loginRetry);
        }
        if (ex != null) {
            data.put("exception", ex.getMessage());
        }
        writeAsJson(data, response);
    }

    public void sendResponse(String message, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", message);
        writeAsJson(data, response);
    }

    public void sendResponse(String message, HttpServletRequest request, HttpServletResponse response, Exception ex) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("message", message);
        writeAsJson(data, response);
    }
}
