package com.sungjun.jwt;

import com.sungjun.exception.CustomException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends GenericFilterBean {

    private static final String INVALID_JWT_TOKEN = "Invalid JWT token";
    private final JwtTokenProvider jwtTokenProvider;


    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


//    private void setHeader(HttpServletResponse response, String newToken) {
//        response.setHeader("Authorization", newToken);
//        response.setHeader("Access-Control-Expose-Headers", "Authorization");
//    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletResponse response = (HttpServletResponse) res;
            String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
            System.out.println("token = " + token);

            if ((token != null && !token.isEmpty())) {
                try {
                    jwtTokenProvider.validateToken(token);
                } catch (JwtException | IllegalArgumentException e) {
                    logger.error("Token expired :::");
                }

                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(req, res);
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
