package com.example.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
public class Logging {
    private static final Logger logger = LoggerFactory.getLogger(Logging.class);

    @Autowired
    private ObjectMapper mapper;

    public void logHeaders(HttpServletRequest request) {
        logger.info("Incoming Request: {} - {}", request.getMethod(), request.getRequestURL());
        if(request.getQueryString() != null) logger.info( "Incoming Query Params: {}", request.getQueryString());

        Map<String, String> headersMap = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }

        try {
            String headersJson = mapper.writeValueAsString(headersMap);
            logger.info("Headers: {}", headersJson);
        } catch (IOException e) {
            logger.error("Error converting headers to JSON", e);
        }
    }

    public void logRequestBody(ContentCachingRequestWrapper request) throws IOException {
        String payload = new String(request.getContentAsByteArray(), request.getCharacterEncoding());
        logger.info("Request Body: {}", payload);
    }
}
