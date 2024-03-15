package com.example.springcomparecaffeineehcache.webtoon;


import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebtoonRepository extends JpaRepository<Webtoon, Integer> {

    Page<Webtoon> findAll(Pageable pageable);

    List<Webtoon> findByPlatform(String platform);

}
