package com.petproject.WineStore.repository;

import com.petproject.WineStore.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "roles")
    Page<User> findAll(Pageable pageable);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = "roles")
    User findByActivationCode(String code);

    @Query("SELECT user.email FROM User user WHERE user.passwordResetCode = :code")
    Optional<String> getEmailByPasswordResetCode(String code);

    @EntityGraph(attributePaths = "roles")
    @Query("SELECT user FROM User user WHERE " +
            "(CASE " +
            "   WHEN :searchType = 'username' THEN UPPER(user.username) " +
            "   WHEN :searchType = 'email' THEN UPPER(user.email) " +
            "   ELSE UPPER(user.email) " +
            "END) " +
            "LIKE UPPER(CONCAT('%',:text,'%'))")
    Page<User> searchUsers(String searchType, String text, Pageable pageable);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phone);
}
