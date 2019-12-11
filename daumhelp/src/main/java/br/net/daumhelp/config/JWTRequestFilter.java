package br.net.daumhelp.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtTokenUtill jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		final String requestTokenHeader = request.getHeader("token") == null ? request.getParameter("token") : request.getHeader("token");
		String username = null;
		String jwtToken = null;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, token, Content-Type, Authorization, credential, X-XSRF-TOKEN, ");

		if (requestTokenHeader != null) {
			jwtToken = requestTokenHeader;
			try {
				
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				
			} catch (IllegalArgumentException e) {
				System.out.println("Não foi possível obter o token JWT");
			} catch (ExpiredJwtException e) {
				System.out.println("O token JWT expirou");
			}
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
			
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		chain.doFilter(request, response);
	}
}