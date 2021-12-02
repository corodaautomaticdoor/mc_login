package com.coroda.mclogin.repository;

import com.coroda.mclogin.model.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query("from Login o where o.loginId =:loginId")
    List<Login> searchId (@Param("loginId")Long loginId);

    @Query(" from Login o where o.email =:email and o.password=:password")
    List<Login> validLogin(@Param("email") String email ,@Param("password") String password );
    @Query(" from Login o where o.email =:email and o.password=:password")
    Login validLogin1(@Param("email") String email ,@Param("password") String password );

}
