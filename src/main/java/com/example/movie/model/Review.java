package com.example.movie.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long movieId; // 영화 ID
    private String author; // 리뷰 작성자
    private int rating; // 별점 (1~5)

    @Column(length = 300)
    private String content; // 300자 이내 리뷰

}
