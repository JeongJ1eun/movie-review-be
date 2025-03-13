package com.example.movie.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReportRequest {
    private String reportedEmail; // 신고할 사용자의 이메일
    private String reason;
}
