package com.example.movie.repository;

import com.example.movie.model.Report;
import com.example.movie.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    // 특정 신고자가 특정 사용자를 신고한 기록이 있는지 확인
    boolean existsByReporterAndReported(User reporter, User reported);

    // 특정 사용자가 신고당한 모든 기록 조회
    List<Report> findAllByReported(User reported);

    // 미처리된 신고 목록 조회
    List<Report> findByProcessedFalseOrderByCreatedAtDesc();

    // 특정 기간 내의 신고 목록 조회
    List<Report> findByCreatedAtBetweenOrderByCreatedAtDesc(
            LocalDateTime startDate,
            LocalDateTime endDate
    );

    // 특정 사용자가 신고한 목록 조회
    List<Report> findByReporterOrderByCreatedAtDesc(User reporter);

    // 특정 사용자가 받은 신고 수 카운트
    long countByReported(User reported);
}
