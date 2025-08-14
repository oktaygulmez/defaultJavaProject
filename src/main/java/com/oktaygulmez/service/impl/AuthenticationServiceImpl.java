package com.oktaygulmez.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oktaygulmez.dto.DtoUser;
import com.oktaygulmez.exception.BaseException;
import com.oktaygulmez.exception.ErrorMessage;
import com.oktaygulmez.exception.MessageType;
import com.oktaygulmez.jwt.AuthRequest;
import com.oktaygulmez.jwt.AuthResponse;
import com.oktaygulmez.jwt.JWTService;
import com.oktaygulmez.jwt.RefreshTokenRequest;
import com.oktaygulmez.model.RefreshToken;
import com.oktaygulmez.model.User;
import com.oktaygulmez.repository.RefreshTokenRepository;
import com.oktaygulmez.repository.UserRepository;
import com.oktaygulmez.service.IAuthenticationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

	private final BCryptPasswordEncoder passwordEncoder;

	private final UserRepository userRepository;
	
	private final AuthenticationProvider authenticationProvider;
	
	private final JWTService jwtService;
	
	private final RefreshTokenRepository refreshTokenRepository;
	

	private User createUser(AuthRequest request) {
		User user = new User();
		user.setCreateTime(new Date());
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		return user;
	}
	
	private RefreshToken createRefreshToken(User user) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setCreateTime(new Date());
		refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000*60*60*4));
		refreshToken.setRefreshToken(UUID.randomUUID().toString());
		refreshToken.setUser(user);
		
		return refreshToken;
	}
	
	private boolean isRefreshTokenValid(Date expireDate) {
		return new Date().before(expireDate);
	}

	@Override
	public DtoUser register(AuthRequest request) {
		User savedUser = userRepository.save(createUser(request));

		DtoUser dtoUser = new DtoUser();
		BeanUtils.copyProperties(savedUser, dtoUser);
		return dtoUser;
	}

	@Override
	public AuthResponse authenticate(AuthRequest authRequest) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
		try {
			authenticationProvider.authenticate(authenticationToken);
			Optional<User> optional = userRepository.findByUsername(authRequest.getUsername());
			
			String accessToken = jwtService.generateToken(optional.get());
			RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optional.get()));
			
			return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
		} catch (Exception e) {
			throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
		}
	}

	@Override
	public AuthResponse refreshToken(RefreshTokenRequest request) {
		Optional<RefreshToken> optRefreshToken = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
		if(optRefreshToken.isEmpty()) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, request.getRefreshToken()));
		}
		
		if(!isRefreshTokenValid(optRefreshToken.get().getExpireDate())) {
			throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_IS_EXPIRED, request.getRefreshToken()));
		}
		
		String accessToken = jwtService.generateToken(optRefreshToken.get().getUser());
		RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optRefreshToken.get().getUser()));
		
		return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
	}

}
