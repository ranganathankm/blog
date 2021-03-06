package com.blogspot.ranganathankm.spring.custom.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blogspot.ranganathankm.spring.custom.auth.model.AppUser;

/**
 *
 * @author ranga
 */
public interface AppUserRepository extends JpaRepository<AppUser, Integer>
{
    public AppUser findFirstByLoginName(String loginName);
    public int countByLoginName(String loginName);
}
