# SpringBoot 내장 캐시 사용

# build.gradle
```groovy
    implementation 'org.springframework.boot:spring-boot-starter-cache'
```

# MainApplication 설정
```java
@EnableCaching // 캐시 활성화
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}

```

# 캐싱 적용
```java
   @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book");
    }
```

---
# 결과

```textmate
2024-01-14T00:22:42.832+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : 캐시 안쓴 repository
2024-01-14T00:22:42.832+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : 책 찾는중...
2024-01-14T00:22:45.833+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:22:48.838+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-4567 -->Book(isbn=isbn-4567, title=Some book)
2024-01-14T00:22:51.840+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:22:54.845+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-4567 -->Book(isbn=isbn-4567, title=Some book)
2024-01-14T00:22:57.851+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:23:00.856+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:23:00.856+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : 걸린 시간(캐시 미사용): 18024480917
2024-01-14T00:23:00.856+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : 캐시 사용 repository
2024-01-14T00:23:00.856+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : 책 찾는중...
2024-01-14T00:23:03.865+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:23:06.868+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-4567 -->Book(isbn=isbn-4567, title=Some book)
2024-01-14T00:23:06.869+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:23:06.869+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-4567 -->Book(isbn=isbn-4567, title=Some book)
2024-01-14T00:23:06.869+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:23:06.869+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : isbn-1234 -->Book(isbn=isbn-1234, title=Some book)
2024-01-14T00:23:06.870+09:00  INFO 29094 --- [           main] com.study.cache.book.AppRunner           : 걸린 시간(캐시 사용): 6013129959

Process finished with exit code 0

```