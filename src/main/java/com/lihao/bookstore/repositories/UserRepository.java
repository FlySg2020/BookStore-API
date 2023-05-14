package com.lihao.bookstore.repositories;
import com.lihao.bookstore.entities.ApiUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<ApiUser, Long> {

    Optional<ApiUser> findByUserName(String userName);
}
