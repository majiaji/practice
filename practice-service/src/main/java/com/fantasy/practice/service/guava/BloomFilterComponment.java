package com.fantasy.practice.service.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;

/**
 * Created by jiaji on 16/12/4.
 */
@Component
public class BloomFilterComponment {
    private BloomFilter<String> bloomFilter;

    @PostConstruct
    void init() {
        bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("utf-8")), 10, 0.03);
    }

    boolean put(String content) {
        return bloomFilter.put(content);
    }


    boolean mightContain(String content) {
        return bloomFilter.mightContain(content);
    }

}
