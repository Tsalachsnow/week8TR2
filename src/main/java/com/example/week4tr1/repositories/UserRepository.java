package com.example.week4tr1.repositories;

import com.example.week4tr1.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findUsersByEmail(String email);
     Optional<UserInfo> findById(Long id);

    List<UserInfo> findAll();

    @Modifying
    @Query("UPDATE UserInfo SET loginStatus=?1 WHERE id=?1")
    UserInfo findByIdAndLoginStatus(Long id, Integer loginStatus);

    @Modifying
    @Query("SELECT loginName FROM UserInfo WHERE loginName=?1")
    boolean findByLoginName(String username);
}
