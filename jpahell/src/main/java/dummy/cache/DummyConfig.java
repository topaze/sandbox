package dummy.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@ComponentScan(basePackages={"dummy.cache"})
@EnableCaching
public class DummyConfig {

    @Bean
    public CacheManager cacheManager() {
	SimpleCacheManager cacheManager = new SimpleCacheManager();
	List<GuavaCache> guavaCaches = new ArrayList<>();

	GuavaCache guavaCache = new GuavaCache(
		"dummy",
		CacheBuilder
		.newBuilder()
		.maximumSize(1000)
		.expireAfterAccess(5, TimeUnit.MINUTES)
		.build()
		);    
	guavaCaches.add(guavaCache);

	cacheManager.setCaches(guavaCaches);
	return cacheManager;
    }

}
