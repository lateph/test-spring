package com.mgg.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mgg.springboot.dao.models.Users;
import com.mgg.springboot.services.UsersService;
import com.mgg.springboot.utils.JwtTokenUtil;
import com.mgg.springboot.dao.models.JwtResponse;
import com.mgg.springboot.dao.models.LoginForm;
import com.mgg.springboot.beans.JwtErrorResponses;

@RestController
public class AuthenticationController {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UsersService UsersService;

	@PostMapping("/authentication")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginForm user) throws Exception {

		if (user.getStrategy().equals("jwt")) {
			// System.prin
			// We call findByEmail, if no, email, then error thrown in UserServiceImpl
			final String tokenjwt = user.getAccessToken();
			String email = jwtTokenUtil.getUsernameFromToken(tokenjwt);
			System.out.println("jancok kontol2" + email);
			Users userFound = UsersService.findByEmail(email);
			final String token = jwtTokenUtil.generateToken(userFound);
			return ResponseEntity.ok(new JwtResponse(token, userFound));
		} else {
			// We call findByEmail, if no, email, then error thrown in UserServiceImpl
			Users userFound = UsersService.findByEmail(user.getEmail());
			// if no error, we need to check password same?
			if (!BCrypt.checkpw(user.getPassword(), userFound.getPassword())) {
				JwtErrorResponses jwtResponses = new JwtErrorResponses("NotAuthenticated", "Invalid login", 401,
						"not-authenticated");

				return ResponseEntity.status(401).body(jwtResponses);
			}

			final String token = jwtTokenUtil.generateToken(userFound);
			String email = jwtTokenUtil.getUsernameFromToken(token);

			return ResponseEntity.ok(new JwtResponse(token, userFound));
		}
	}

}
