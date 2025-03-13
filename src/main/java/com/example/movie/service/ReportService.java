package com.example.movie.service;

import com.example.movie.dto.ReportDto;
import com.example.movie.model.Report;
import com.example.movie.model.User;
import com.example.movie.repository.ReportRepository;
import com.example.movie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    @Transactional
    public void reportUser(String reporterEmail, String reportedEmail, String reason) {
        User reporter = userRepository.findByEmail(reporterEmail)
                .orElseThrow(() -> new IllegalArgumentException("신고자를 찾을 수 없습니다."));
        User reported = userRepository.findByEmail(reportedEmail)
                .orElseThrow(() -> new IllegalArgumentException("신고대상을 찾을 수 없습니다."));

        // 자기 자신을 신고하는 경우 체크
        if (reporter.getEmail().equals(reported.getEmail())) {
            throw new IllegalArgumentException("자기 자신을 신고할 수 없습니다.");
        }

        // 이미 신고한 경우 체크
        boolean alreadyReported = reportRepository.existsByReporterAndReported(reporter, reported);
        if (alreadyReported) {
            throw new IllegalStateException("이미 신고한 사용자입니다.");
        }

        Report report = Report.builder()
                .reporter(reporter)
                .reported(reported)
                .reason(reason)
                .build();

        reportRepository.save(report);
    }

    // 관리자용 신고 목록 조회
    public List<ReportDto> getReportList() {
        return reportRepository.findByProcessedFalseOrderByCreatedAtDesc()
                .stream()
                .map(ReportDto::from)
                .collect(Collectors.toList());
    }

    // 관리자용 유저 차단
    @Transactional
    public void blockUser(String userEmail) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        user.block();

        // 해당 유저에 대한 모든 신고를 처리완료로 표시
        reportRepository.findAllByReported(user)
                .forEach(Report::markAsProcessed);
    }
}
