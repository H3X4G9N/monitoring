package com.datalogging.server.repository;

import com.datalogging.server.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM dl_user WHERE id = :id AND token = :token", nativeQuery = true)
    User findByIDAndToken(@Param("id") Long id, @Param("token") String token);

    @Query(value = "SELECT * FROM dl_user WHERE token = :token", nativeQuery = true)
    User findByToken(@Param("token") String token);

    @Query(value = "SELECT * FROM dl_user WHERE username = :username AND password = :password", nativeQuery = true)
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT * FROM dl_user WHERE email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM dl_user WHERE username = :username AND token = :token", nativeQuery = true)
    User findByUsernameAndToken(@Param("username") String username, @Param("token") String token);

    @Query(value = "SELECT * FROM dl_user WHERE username = :username", nativeQuery = true)
    User findByUsername(@Param("username") String username);
}
