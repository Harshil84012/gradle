package com.sungjun.api.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sungjun.responseDto.GetTokenClaims;
import com.sungjun.security.jwt.JwtTokenProvider;
import com.sungjun.util.Utilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;

@Configuration

public class BeanFactory {

//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;

    @Bean
    public JwtTokenProvider jwtTokenProvider() {

        return new JwtTokenProvider();
    }

    @Bean
    public Utilities utilities() {
        return new Utilities();
    }


    @Bean
    @RequestScope
    public GetTokenClaims getTokenClaims(HttpServletRequest request, JwtTokenProvider jwtTokenProvider) throws JsonProcessingException {
        String token = jwtTokenProvider.resolveToken(request);
        return token != null ? getTokenClaimsDTO(request) : new GetTokenClaims();
    }

//    @Bean
//    @RequestScope
//    public List<String> getPermissionsFromUserName(HttpServletRequest request) {
//        String token = jwtTokenProvider.resolveToken(request);
//        String userName = jwtTokenProvider.getUsername(token);
//
//        return utilities.getPermissionsFromUserName(userName);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    public GetTokenClaims getTokenClaimsDTO(HttpServletRequest request) {
        return new GetTokenClaims(

                jwtTokenProvider().resolveToken(request),
                this.jwtTokenProvider().getUsername(this.jwtTokenProvider().resolveToken(request)),
                this.jwtTokenProvider().getUserIdToken(this.jwtTokenProvider().resolveToken(request)),
                this.jwtTokenProvider().getSystemRole(this.jwtTokenProvider().resolveToken(request)));
    }

    @Bean
    public RestTemplate
    restTemplate() {
        return new RestTemplate();
    }
}
