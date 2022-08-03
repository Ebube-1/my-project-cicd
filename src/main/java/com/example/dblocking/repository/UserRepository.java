package com.example.dblocking.repository;

import com.example.dblocking.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Version;
import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail (String email);




}
