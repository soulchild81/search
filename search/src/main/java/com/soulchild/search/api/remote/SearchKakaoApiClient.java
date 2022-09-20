package com.soulchild.search.api.remote;


import com.soulchild.search.api.remote.model.KakaoBlog;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "searchKakaoApiClient", url = "${feign.client.config.search.kakao.url}")
public interface SearchKakaoApiClient {

    @GetMapping(value = "/{category}", produces = "application/json" , headers = "Authorization=${search.kakao-access-token}")
    KakaoBlog getKakaoBlog(@PathVariable String category,
                      @RequestParam("query") String query,
                      @RequestParam("sort") String sort,
                      @RequestParam("page") int page,
                      @RequestParam("size") int size);
}
