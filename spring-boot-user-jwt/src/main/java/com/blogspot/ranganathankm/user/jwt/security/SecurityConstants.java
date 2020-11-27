package com.blogspot.ranganathankm.user.jwt.security;

/**
 *
 * @author ranga
 */
public interface SecurityConstants {
    public static final String SIGN_UP_URL = "/users/record";
    public static final String HEADER_NAME = "Authorization";
    public static final Long EXPIRATION_TIME = 1000L*60*30;
    public static final String TOKEN = "token";
    public static final String ROLE = "role";
}
