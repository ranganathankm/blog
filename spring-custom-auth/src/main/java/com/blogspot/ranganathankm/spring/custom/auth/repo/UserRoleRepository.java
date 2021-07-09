package com.blogspot.ranganathankm.spring.custom.auth.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blogspot.ranganathankm.spring.custom.auth.model.AppUser;
import com.blogspot.ranganathankm.spring.custom.auth.model.UserRole;

/**
 *
 * @author ranga
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>
{
    public List<UserRole> findByAppUser(AppUser appUser);
}
