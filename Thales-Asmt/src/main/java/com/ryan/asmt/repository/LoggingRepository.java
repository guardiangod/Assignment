package com.ryan.asmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ryan.asmt.entity.RequestLogging;

@Repository
public interface LoggingRepository extends JpaRepository<RequestLogging, Long> {

}
