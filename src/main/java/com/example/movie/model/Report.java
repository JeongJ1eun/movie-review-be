package com.example.movie.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    private User reporter; // 신고한 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_id")
    private User reported; // 신고당한 사용자

    private String reason; // 신고 사유

    private boolean processed; // 처리 여부

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성일시

    @LastModifiedDate
    private LocalDateTime modifiedAt; // 수정일시

    @Builder
    public Report(User reporter, User reported, String reason) {
        this.reporter = reporter;
        this.reported = reported;
        this.reason = reason;
        this.processed = false;
    }

    public void markAsProcessed() {
        this.processed = true;
    }
}