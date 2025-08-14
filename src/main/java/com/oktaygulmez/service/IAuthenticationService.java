package com.oktaygulmez.service;

import com.oktaygulmez.dto.DtoUser;
import com.oktaygulmez.jwt.AuthRequest;
import com.oktaygulmez.jwt.AuthResponse;
import com.oktaygulmez.jwt.RefreshTokenRequest;

public interface IAuthenticationService {

	 DtoUser register(AuthRequest request);
	 
	 AuthResponse authenticate(AuthRequest authRequest);
	 
	 AuthResponse refreshToken(RefreshTokenRequest request);
}
