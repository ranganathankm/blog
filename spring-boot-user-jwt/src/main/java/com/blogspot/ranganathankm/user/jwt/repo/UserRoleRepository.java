package com.blogspot.ranganathankm.user.jwt.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blogspot.ranganathankm.user.jwt.model.AppUser;
import com.blogspot.ranganathankm.user.jwt.model.UserRole;

/**
 *
 * @author ranga
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer>
{
    public List<UserRole> findByAppUser(AppUser appUser);
}
