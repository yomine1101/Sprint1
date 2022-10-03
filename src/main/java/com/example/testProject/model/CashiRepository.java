package com.example.testProject.model;

import com.example.testProject.model.entriy.MGN_CASHI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashiRepository extends JpaRepository<MGN_CASHI, String> {

}
