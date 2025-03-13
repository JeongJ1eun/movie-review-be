package com.example.movie.dto;

import com.example.movie.model.Report;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReportDto {
    private Long id;
    private String reporterEmail; // 신고자 이메일
    private String reportedEmail; // 신고당한 사용자 이메일
    private String reason;
    private boolean processed;
    private LocalDateTime createdAt;

    @Builder
    public ReportDto(Long id, String reporterEmail, String reportedEmail,
                     String reason, boolean processed, LocalDateTime createdAt) {
        this.id = id;
        this.reporterEmail = reporterEmail;
        this.reportedEmail = reportedEmail;
        this.reason = reason;
        this.processed = processed;
        this.createdAt = createdAt;
    }

    public static ReportDto from(Report report) {
        return ReportDto.builder()
                .id(report.getId())
                .reporterEmail(report.getReporter().getEmail())
                .reportedEmail(report.getReported().getEmail())
                .reason(report.getReason())
                .processed(report.isProcessed())
                .createdAt(report.getCreatedAt())
                .build();
    }
}
