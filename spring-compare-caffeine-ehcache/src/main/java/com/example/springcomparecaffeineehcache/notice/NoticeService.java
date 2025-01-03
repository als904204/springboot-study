package com.example.springcomparecaffeineehcache.notice;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Cacheable(value = "noticeAllCache", key = "'allNotices'")
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

}
