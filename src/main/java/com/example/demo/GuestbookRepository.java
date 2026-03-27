package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long> {
    // 최신순 정렬을 위해 ID 내림차순 조회 메서드 추가
    List<Guestbook> findAllByOrderByIdDesc();
}