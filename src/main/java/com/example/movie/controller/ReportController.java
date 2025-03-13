package com.example.movie.controller;

import com.example.movie.dto.ReportDto;
import com.example.movie.dto.ReportRequest;
import com.example.movie.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> reportUser(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ReportRequest request
    ) {
        reportService.reportUser(
                userDetails.getUsername(), // 이메일이 username으로 사용됨
                request.getReportedEmail(),
                request.getReason()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<ReportDto>> getReportList() {
        return ResponseEntity.ok(reportService.getReportList());
    }

    @PostMapping("/block/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Void> blockUser(@PathVariable String email) {
        reportService.blockUser(email);
        return ResponseEntity.ok().build();
    }
}
