package com.compare.redis_vs_caffeine.dto;

import com.compare.redis_vs_caffeine.entity.Posts;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {
    private List<Posts> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
}
