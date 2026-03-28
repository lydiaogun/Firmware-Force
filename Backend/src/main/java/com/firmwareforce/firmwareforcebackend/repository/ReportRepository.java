package com.firmwareforce.firmwareforcebackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.firmwareforce.firmwareforcebackend.report;
import java.util.List;
@Repository
public interface ReportRepository extends JpaRepository<report, Long> {
    List<report> findAllByOrderByPriorityDesc();
    List<report> findAllByOrderByVoteCountDesc();
}