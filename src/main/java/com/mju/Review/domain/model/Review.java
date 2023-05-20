package com.mju.Review.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "review")
public class Review {

    @Id
    @Column(name = "review_index")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "review_grade")
    private Long grade;
    @Column(name = "user_photo")
    private String user_photo;
    @Column(name = "user_name")
    private String user_name;
    @Column(name = "review_date")
    private LocalDateTime date;
    @Column(name = "review_content")
    private String review_content;
    @Column(name = "good_count")
    private int likes;

    public Review() {

    }

    @PrePersist
    public void prePersist(){
        this.date = LocalDateTime.now();
    }

    @Builder
    public Review(Long grade, String user_photo, String user_name, LocalDateTime date, String review_content, int likes){
        this.grade = grade;
        this.user_photo = user_photo;
        this.user_name = user_name;
        this.date = date;
        this.review_content = review_content;
        this.likes = likes;
    }

    public Review(Long grade, String user_photo, String user_name, String review_content){
        this.grade = grade;
        this.user_photo = user_photo;
        this.user_name = user_name;
        this.review_content = review_content;
    }

    public void incrementLike() {
        this.likes++;
    }

    public void decrementLike() {
        this.likes--;
    }
}
