package com.example.connectchallengeutils.repository;

import com.example.connectchallengeutils.domain.Keys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeysRepository extends JpaRepository<Keys, Integer> {
}
