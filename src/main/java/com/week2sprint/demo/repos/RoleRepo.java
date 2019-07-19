package com.week2sprint.demo.repos;

import com.week2sprint.demo.models.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface RoleRepo extends CrudRepository<Role, Long>
{
    @Transactional
    @Modifying
    @Query(value = "DELETE from UserRoles where userid = :userid")
    void deleteUserRolesByUserId(long userid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserRoles(userid, roleid) values (:userid, :roleid)", nativeQuery = true)
    void insertUserRoles(long userid, long roleid);

}
{
}
