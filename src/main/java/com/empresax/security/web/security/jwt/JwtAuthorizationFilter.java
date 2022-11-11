package com.empresax.security.web.security.jwt;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.empresax.security.persistence.entity.StateType;
import com.empresax.security.persistence.repository.IRolEntityCrudRepository;
import com.empresax.security.persistence.repository.IUserEnitityCrudRepository;

import io.jsonwebtoken.ExpiredJwtException;

// 1 Authorization
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private IUserEnitityCrudRepository userRepo;
    @Autowired
    private IRolEntityCrudRepository rolRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenWithBearer = request.getHeader("Authorization");
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
            String token = tokenWithBearer.replace("Bearer ", "");
            String username = null;
            try{
                username= JwtUtil.getSubject(token);
            }catch(ExpiredJwtException e){System.out.println(e.getMessage());}
            if (username != null) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        username,
                        userRepo.findPasswordByUsername(username),
                        rolRepo.findAllRolesByUsername(username).stream()
                                .filter(rol -> rol.getState().equals(StateType.ACTIVE))
                                .map(rol -> new SimpleGrantedAuthority(rol.getName()))
                                .collect(Collectors.toSet()));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

}
