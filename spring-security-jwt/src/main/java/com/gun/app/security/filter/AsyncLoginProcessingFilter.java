package com.gun.app.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gun.app.security.exception.AuthMethodNotSupportedException;
import com.gun.app.security.model.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 비동기 로그인 처리를 위한 시큐리티 필터
 */
@Slf4j
public class AsyncLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper;

    public AsyncLoginProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler) {
        super(defaultFilterProcessesUrl);
        this.objectMapper = objectMapper;
        this.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        this.setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    /**
     * 비동기 post형식으로 온 요청에 대해 username, password를 받아 토큰 생성 후 AuthenticationManager에게 전달함.
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        if(!HttpMethod.POST.name().equals(request.getMethod()) || this.isAsync(request)){
            log.debug("비동기 로그인 처리 지원이 되지 않는 메소드 요청입니다. :: "+request.getMethod());
            throw new AuthMethodNotSupportedException("Authentication method not supported");
        }
        LoginRequest loginRequest = objectMapper.readValue(request.getReader(), LoginRequest.class);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        return this.getAuthenticationManager().authenticate(token);
    }

    /**
     * 비동기 요청이 맞는지 검증
     * @param request
     * @return
     */
    private boolean isAsync(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}