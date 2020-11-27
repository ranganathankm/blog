package com.blogspot.ranganathankm.user.jwt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.blogspot.ranganathankm.user.jwt.model.AppRole;
import com.blogspot.ranganathankm.user.jwt.model.RoleMaster;

/**
 *
 * @author ranga
 */
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> 
{
    public RoleMaster findByName(@Param("name") AppRole appRole);
}
