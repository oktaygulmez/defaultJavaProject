package com.oktaygulmez.controller;

import com.oktaygulmez.dto.DtoUser;
import com.oktaygulmez.jwt.AuthRequest;
import com.oktaygulmez.jwt.AuthResponse;
import com.oktaygulmez.jwt.RefreshTokenRequest;

public interface IRestAuthenticationController {

	
	public RootEntity<DtoUser> register(AuthRequest request);
	
	public RootEntity<AuthResponse> authenticate(AuthRequest request);
	
	public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest request);
}
