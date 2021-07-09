package com.blogspot.ranganathankm.spring.custom.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.blogspot.ranganathankm.spring.custom.auth.model.AppRole;
import com.blogspot.ranganathankm.spring.custom.auth.model.RoleMaster;

/**
 *
 * @author ranga
 */
public interface RoleMasterRepository extends JpaRepository<RoleMaster, Integer> 
{
    public RoleMaster findByName(@Param("name") AppRole appRole);
}
