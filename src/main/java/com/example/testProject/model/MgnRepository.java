package com.example.testProject.model;

import com.example.testProject.model.entriy.MGN_MGNI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface MgnRepository extends JpaRepository<MGN_MGNI, String>, JpaSpecificationExecutor<MGN_MGNI>, PagingAndSortingRepository<MGN_MGNI, String> {

//    @Query(value = "SELECT * FROM mgni WHERE MGNI_ID = ?1",nativeQuery = true)
    MGN_MGNI findByMgniId(String id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM mgni WHERE MGNI_ID = ?1",nativeQuery = true)
    void deleteByMgniId(String mgniId);
}
