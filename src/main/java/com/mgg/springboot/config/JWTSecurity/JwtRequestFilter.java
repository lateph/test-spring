package com.mgg.springboot.config.JWTSecurity;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedReactiveAuthorizationManager;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mgg.springboot.dao.models.Users;
import com.mgg.springboot.services.UsersService;
import com.mgg.springboot.utils.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mgg.springboot.beans.JwtErrorResponses;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private UsersService usersService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private ObjectMapper objectMapper;

	public void handleResponse(HttpServletResponse response, String jwtError)
			throws ServletException, IOException {

		String jwtResponseJson = objectMapper
				.writeValueAsString(new JwtErrorResponses("NotAuthenticated", jwtError, 401,
						"not-authenticated"));

		response.setStatus(401);
		response.setContentType("application/json");

		PrintWriter out = response.getWriter();
		out.print(jwtResponseJson);
		out.flush();

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String jwtToken = request.getHeader("Authorization");

		String endpoint = request.getRequestURI().split("/", 0)[1];
		String method = request.getMethod();

		// Check whether method=POST and endpoints = users or authenticte
		// If yes, no need token
		// If no:
		// a. check if header is null
		// b. check whether token is correct format, secret correct and is not expired
		// c. if the email exist in db

		// Everything ok, set the id and email in AuthenticationManager so that can be
		// passed down and accessed inside the rest enpoint

		// Check whether method=POST and endpoints = users or authenticte
		if (
			!(
				(endpoint.equals("users") && method.equals("POST"))
				|| 
				(endpoint.equals("authentication") && method.equals("POST")
				|| 
				(endpoint.equals("uploads") && method.equals("GET"))
			))) {

			// a. check if header is null
			if (jwtToken == null) {

				handleResponse(response, "Not authenticated");
				return;
			}

			// b. check whether token is correct format, secret correct and is not expired
			try {

				// c. if the email exist in db
				String email = jwtTokenUtil.getUsernameFromToken(jwtToken);
				System.out.println("test"+email);
				Users dbUser = usersService.findByEmail(email);

				if (email == dbUser.getEmail()) {
					// throw error
					handleResponse(response, "Invalid login");
					return;
				}

				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dbUser, null);
				SecurityContextHolder.getContext().setAuthentication(token);
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				handleResponse(response, "jwt expired");
				return;

			} catch (Exception e) {
				System.out.println("invalid algorithm");
				handleResponse(response, "invalid algorithm");
				return;
			}
		}

		// We have not done passing down the email into the REST end point. Must use
		// SecurityContextHolder
		// SecurityContextHolder.getContext().setAuthentication(authToken);

		chain.doFilter(request, response);
	}

}