package com.firmwareforce.firmwareforcebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firmwareforce.firmwareforcebackend.vote;

@Repository
public interface VoteRepository extends JpaRepository<vote, Long> {

}