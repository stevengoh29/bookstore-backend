package com.example.demo.component;

import com.example.demo.module.common.JwtService;
import com.example.demo.module.user.User;
import com.example.demo.module.user.UserService;
import com.example.demo.util.Logging;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.util.UUID;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;

    @Autowired
    private Logging logging;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, java.io.IOException {
        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        final String authHeader = request.getHeader("Authorization");
        logging.logHeaders(wrappedRequest);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);
            final String userEmail = jwtService.extractUsername(jwt);

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (userEmail != null && authentication == null) {
                User userDetails = userService.getUserById(UUID.fromString(userEmail));

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }

            filterChain.doFilter(wrappedRequest, response);
        } catch (AuthenticationException exception) {
            logger.error(exception.getMessage(), exception);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token. Please re-login to get a valid token.");
//            handlerExceptionResolver.resolveException(request, response, null, exception);
        } catch (ExpiredJwtException exception) {
            logger.error(exception.getMessage(), exception);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
        } finally {
            logging.logRequestBody(wrappedRequest);
        }
    }
}
