package com.nisum.users.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.nisum.users.config.ApplicationProperties;
import com.nisum.users.models.User;
import com.nisum.users.service.JwtTokenService;
import com.nisum.users.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Implement JwtTokenService methods.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private ApplicationProperties properties;

    /**
     * Constructor with all arguments of the JwtTokenServiceImpl class.
     *
     * @param properties contains properties useful for this class
     */
    public JwtTokenServiceImpl(ApplicationProperties properties) {
        this.properties = properties;
    }

    /**
     * Generates a new token from a user.
     *
     * @param user authenticated user
     * @return String JWT
     */
    @Override
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.JWT_CLAIM_ROLE, Constants.ROLE_USER);

        return Jwts.builder().setClaims(claims).setSubject(user.getEmail())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpireJwt()))
                .signWith(SignatureAlgorithm.HS512, properties.getSecretJwt()).compact();
    }

    /**
     * Validate the user JWT.
     *
     * @param token JWT
     * @return Authentication user authentication
     */
    @Override
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(properties.getSecretJwt())
                .parseClaimsJws(token)
                .getBody();

        String email = claims.getSubject();
        String role = claims.get(Constants.JWT_CLAIM_ROLE, String.class);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Constants.PREFIX_ROLE_AUTHORITY.concat(role)));
        return new UsernamePasswordAuthenticationToken(email, null, authorities);
    }

}
