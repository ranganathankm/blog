package com.blogspot.ranganathankm.user.jwt.model;

import lombok.Data;

/**
 *
 * @author ranga
 */
@Data
public class UserLoginDto {
    private String username, password;
}
