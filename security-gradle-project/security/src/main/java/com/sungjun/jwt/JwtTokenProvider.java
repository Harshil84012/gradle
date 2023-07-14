package com.sungjun.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.sungjun.entity.PrivilegesEntity;
import com.sungjun.entity.RolePrivilegesEntity;
import com.sungjun.entity.UserEntity;
import com.sungjun.entity.UserRoleEntity;
import com.sungjun.repository.RolePrivilegesRepository;
import com.sungjun.repository.UserRepository;
import com.sungjun.repository.UserRoleRepository;
import com.sungjun.responseDto.JwtResponseDto;
import com.sungjun.responseDto.RefreshTokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final String AUTHORIZATION = "Authorization";
    private static final String USER_ID = "userId";

    private static final String SYSTEM_ROLE = "role";

    private final String apiKey = "e3LHqZMEtP7pc8HTJeU7cnslny6OQhNgaHa3Jdbjd0jK4AJUBz3xoRLS6AtuWgv4g0pu2L7OIpZvMOtGhGIZLSQ0P0oiQcY1OrvM";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RolePrivilegesRepository rolePrivilegesRepository;

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        if (bearerToken != null) {
            return bearerToken;
        }
        return bearerToken;
    }


    public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
        Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token);
        return true;
    }

    public Authentication getAuthentication(String token) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(getSystemRole(token)));
        UserDetails userDetails = new User(getUsername(token), getUsername(token), true, false, false, false, grantedAuthorities);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().getSubject();
    }

    public Integer getUserIdToken(String token) {
        return (Integer) Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().get(USER_ID);
    }

    public String getSystemRole(String token) {
        return (String) Jwts.parser().setSigningKey(apiKey).parseClaimsJws(token).getBody().get(SYSTEM_ROLE);
    }


    // create token for login user
    public JwtResponseDto createAccessToken(String username, Integer userId, String role, List<PrivilegesEntity> privilegesEntities) throws JsonProcessingException {

        Claims claims = Jwts.claims().setSubject(username);
        claims.put(USER_ID, userId);
        claims.put(SYSTEM_ROLE, role);
        Date now = new Date();
        Date validity = new Date(now.getTime() + 5000);
        String accessToken = Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validity)//
                .signWith(SignatureAlgorithm.HS256, apiKey)//
                .compact();

        Date validityRefresh = new Date(now.getTime() + 1800000);
        String refreshToken = Jwts.builder()//
                .setClaims(claims)//
                .setIssuedAt(now)//
                .setExpiration(validityRefresh)//
                .signWith(SignatureAlgorithm.HS256, apiKey)//
                .compact();

        return new JwtResponseDto(accessToken, refreshToken);
    }

    // create new token from old token
    public RefreshTokenResponse createAccessTokenFromRefreshToken(String token) {
        System.out.println("new token will generate from here" + token);
        Claims claims = Jwts.claims().setSubject(getUsername(token));
        claims.put(USER_ID, getUserIdToken(token));
        claims.put(SYSTEM_ROLE, getSystemRole(token));
        Date now = new Date();
        Date validity = new Date(now.getTime() + 1800000);
        String accessToken =  Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, apiKey)
                .compact();

        return new RefreshTokenResponse(accessToken);
    }

    public List<String> getPermission(Authentication authentication, Object permission) {

        Optional<UserEntity> user = userRepository.findByUsernameIgnoreCase(authentication.getName());
        Optional<UserRoleEntity> userRoleEntity = userRoleRepository.findByUser(user.get());
        Optional<RolePrivilegesEntity> rolePrivilegeList = rolePrivilegesRepository.findById(userRoleEntity.get().getId());
        List<PrivilegesEntity> privilegeList = rolePrivilegeList.stream().map(RolePrivilegesEntity::getPrivilege).collect(Collectors.toList());
        List<String> permissions = privilegeList.stream().map(PrivilegesEntity::getPrivilegeName).collect(Collectors.toList());
        return permissions;
    }

}
