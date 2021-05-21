package com.blogspot.ranganathankm.user.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blogspot.ranganathankm.user.jwt.model.AppUser;
import java.util.Optional;

/**
 *
 * @author ranga
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer>
{
    public Optional<AppUser> findFirstByLoginName(String loginName);
    public int countByLoginName(String loginName);
}
