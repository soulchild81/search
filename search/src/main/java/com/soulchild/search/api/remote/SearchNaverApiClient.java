package com.soulchild.search.api.remote;

import com.soulchild.search.api.remote.model.NaverBlog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "searchNaverApiClient", url = "${feign.client.config.search.naver.url}")
public interface SearchNaverApiClient {

    @GetMapping(value = "/blog.json", produces = "application/json" , headers = {"X-Naver-Client-Id=${search.naver-client-id}" , "X-Naver-Client-Secret=${search.naver-client-secret}"})
    NaverBlog getNaverBlog(@RequestParam("query") String query,
                           @RequestParam("sort") String sort,
                           @RequestParam("start") int page,
                           @RequestParam("display") int size);
}
