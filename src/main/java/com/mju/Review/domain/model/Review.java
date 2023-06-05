package com.mju.Review.domain.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "review")
@NoArgsConstructor
public class Review {

    @Id
    @Column(name = "review_index")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewIndex;

    @Column(name = "user_id")
    private String userId;
    @Column(name = "review_grade")
    private Long grade;
    @Column(name = "review_date")
    private LocalDate date;
    @Column(name = "review_content")
    private String review_content;
    @Column(name = "course_index")
    private Long courseIndex;
    @Column(name = "good_count")
    private int likes;
    @Transient
    private String userName;
    @Transient
    private List<Review> reviewList = new ArrayList<>(); //하나씩 등록, 삭제, 수정을 할거라 필요없을것같음.근데 조회땜에 있어야 하나? 우선 보류

    @ElementCollection
    private List<String> likedUserIds = new ArrayList<>();
    public List<String> getLikedUserIds() {
        return likedUserIds;
    }
    public void addLikedUserId(String userId) {
        likedUserIds.add(userId);
    }

    @ElementCollection
    private List<String> delikedUserIds = new ArrayList<>();
    public List<String> getDelikedUserIds() {
        return delikedUserIds;
    }
    public void addDelikedUserId(String userId) {
        delikedUserIds.add(userId);
    }

    public List<Review> getReviewList() {
        return this.reviewList;
    }

    public void addNickName(String userName) {
        this.userName = userName;
    }

    @PrePersist
    public void prePersist(){
        this.date = LocalDate.now();
    }

    @Builder
    public Review(Long grade, String user_id, String review_content, int likes, Long courseIndex){
        this.grade = grade;
        this.userId = user_id;
        this.review_content = review_content;
        this.likes = likes;
        this.courseIndex = courseIndex;
    }

    public Review(Long grade, String review_content){
        this.grade = grade;
        this.review_content = review_content;
    }

    public void incrementLike() {
        this.likes++;
    }

    public void decrementLike() {
        this.likes--;
    }

}
