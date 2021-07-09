package com.blogspot.ranganathankm.spring.custom.auth.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.blogspot.ranganathankm.spring.custom.auth.model.AppUser;
import com.blogspot.ranganathankm.spring.custom.auth.model.UserPassword;

/**
 *
 * @author ranga
 */
public interface UserPasswordRepository extends CrudRepository<UserPassword, Long>
{
    public UserPassword findByAppUser(AppUser appUser);
}
