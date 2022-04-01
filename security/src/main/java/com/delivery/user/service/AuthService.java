package com.delivery.user.service;

import com.delivery.user.dto.auth.AuthRequest;
import com.delivery.user.dto.auth.AuthResponse;

public interface AuthService {

    AuthResponse doAuth(AuthRequest authRequest) throws Exception;
}
