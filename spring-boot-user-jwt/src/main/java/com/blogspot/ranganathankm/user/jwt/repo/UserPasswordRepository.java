package com.blogspot.ranganathankm.user.jwt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.blogspot.ranganathankm.user.jwt.model.AppUser;
import com.blogspot.ranganathankm.user.jwt.model.UserPassword;

/**
 *
 * @author ranga
 */
public interface UserPasswordRepository extends CrudRepository<UserPassword, Long>
{
    public UserPassword findByAppUser(AppUser appUser);
}
