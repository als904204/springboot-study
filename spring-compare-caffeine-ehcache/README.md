# Ehcache

- build.grdale
```groovy

// Ehcache
implementation 'org.ehcache:ehcache:3.10.8'
// Spring Cache
implementation 'org.springframework.boot:spring-boot-starter-cache'
// JCache (Java Caching API)
implementation 'javax.cache:cache-api:1.1.0'

```

- resources/ehcache.xml

```xml
<config
  xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'
  xmlns='http://www.ehcache.org/v3'
  xsi:schemaLocation="http://www.ehcache.org/v3 http://www.ehcache.org/schema/ehcache-core.xsd">


  <cache alias="platformEhcache">
    <key-type>java.lang.String</key-type>
    <value-type>java.util.List</value-type>
    <expiry>
      <ttl unit="seconds">60</ttl> <!--TTL-->
    </expiry>
    <resources>
      <heap unit="entries">100</heap> <!--  캐시에서 관리할 수 있는 힙(heap) 메모리 내 객체의 최대 개수 -->
      <offheap unit="MB">100</offheap> <!-- 100MB의 데이터를 오프힙 메모리에 저장 -->
    </resources>

  </cache>

</config>
```

- Logic
```java
 @Cacheable(value = "platformEhcache", key = "#platform", condition = "#platform.equals('NAVER')")
    public List<WebtoonDto> findPlatformWebtoonsByEhcache(String platform) {
        log.info("[ehcache] 서비스 호출");

        List<Webtoon> webtoons = webtoonRepository.findByPlatform(platform);

        List<WebtoonDto> response = new ArrayList<>();


        for (Webtoon webtoon : webtoons) {
            response.add(new WebtoonDto(webtoon));
        }

        return response;
    }

```

---

# Caffeine

- build.gradle
```groovy

// caffeine
implementation 'com.github.ben-manes.caffeine:caffeine:3.1.8'
// Spring Cache
implementation 'org.springframework.boot:spring-boot-starter-cache'
// JCache (Java Caching API)
implementation 'javax.cache:cache-api:1.1.0'

```

- setting class
```java
@Getter
@AllArgsConstructor
public enum CacheType {
    CACHE_STORE("platformCaffeine", 60, 100);
    private final String cacheName;     // 캐시 이름
    private final int expireAfterWrite; // 만료시간
    private final int maximumSize;      // 최대 갯수
}
```


- config class
```java
@Configuration
public class CaffeineCacheConfig {


    @Bean
    public List<CaffeineCache> caffeineConfig() {
        return Arrays.stream(CacheType.values())
            .map(cache -> new CaffeineCache(cache.getCacheName(), Caffeine.newBuilder()
                    .recordStats()
                    .expireAfterWrite(cache.getExpireAfterWrite(),
                        TimeUnit.SECONDS)
                    .maximumSize(cache.getMaximumSize())
                    .build()
                )
            )
            .collect(Collectors.toList());
    }

    @Bean
    public CacheManager cacheManager(List<CaffeineCache> caffeineCaches) {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        cacheManager.setCaches(caffeineCaches);
        return cacheManager;
    }
}
```

- logic

```java
 @Cacheable(value = "platformCaffeine", key = "#platform", condition = "#platform.equals('NAVER')")
    public List<WebtoonDto> findPlatformWebtoonsByCaffeine(String platform) {
        log.info("[caffeine] 서비스 호출");

        List<Webtoon> webtoons = webtoonRepository.findByPlatform(platform);

        List<WebtoonDto> response = new ArrayList<>();


        for (Webtoon webtoon : webtoons) {
            response.add(new WebtoonDto(webtoon));
        }

        return response;
    }
```