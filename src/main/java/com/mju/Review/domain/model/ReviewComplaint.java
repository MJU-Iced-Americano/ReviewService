package com.mju.Review.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "complaint")
public class ReviewComplaint {

    public ReviewComplaint(String complaintContent, ComplaintType type) {
    }

    public enum ComplaintType {
        HATE_SPEECH, PROFANITY, SPAM, ILLEGAL_CONTENT, ETC;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long complaintIndex;

    @Column(name = "complaint_content")
    private String complaintContent;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "review_index")
    private Review review;

    @Enumerated(EnumType.STRING)
    @Column(name = "complaint_type")
    private ComplaintType type;

    @Builder
    public ReviewComplaint(String complaintContent, ComplaintType type, Review review){
        this.complaintContent= complaintContent;
        this.type = type;
        this.review = review;
    }

}
