package com.example.springbootjparelationship.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review,Long> {

    List<Review> findByMemberId(Long memberId);
}
