package com.blogspot.ranganathankm.user.jwt.model;

/**
 *
 * @author ranga
 */
public abstract class AppAuth
{
    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";

    private static final String HAS_AUTHORITY_SUFFIX = "')";
    private static final String HAS_AUTHORITY_PREFIX = "hasAuthority('";

    public static final String HAS_AUTHORITY_ADMIN = HAS_AUTHORITY_PREFIX + ADMIN + HAS_AUTHORITY_SUFFIX;
    public static final String HAS_AUTHORITY_USER = HAS_AUTHORITY_PREFIX + USER + HAS_AUTHORITY_SUFFIX;
}
