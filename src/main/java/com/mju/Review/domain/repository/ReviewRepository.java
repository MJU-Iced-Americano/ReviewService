package com.mju.Review.domain.repository;

import com.mju.Review.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByCourseIndex(Long courseIndex);
    List<Review> deleteByCourseIndex(Long courseIndex);
}
