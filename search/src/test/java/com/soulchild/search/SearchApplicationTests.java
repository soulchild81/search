package com.soulchild.search;

import com.soulchild.search.api.service.PopularKeywordService;
import com.soulchild.search.api.service.SearchService;
import com.soulchild.search.common.Constant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@DisplayName("검색")
@SpringBootTest
class SearchApplicationTests {
    @Resource
    private MockMvc mockMvc;

    public SearchService searchService;
    @Autowired
    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    public PopularKeywordService popularKeywordService;
    @Autowired
    public void setPopularKeywordService(PopularKeywordService popularKeywordService) {
        this.popularKeywordService = popularKeywordService;
    }


    @DisplayName("블로그 검색 - API 조회")
    @Test
    public void SearchBlogTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                    .get("/search/v1/blog")
                                    .param("query", "nike")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                                    .andReturn();

        System.out.println("HTTP STATUS : " + result.getResponse().getStatus());
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus(), "HTTP STATUS 200 이 아닙니다.");
    }

    @DisplayName("인기검색어 - API 조회")
    @Test
    public void PopularKeywordTest() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                                    .get("/search/v1/popular/keyword")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .accept(MediaType.APPLICATION_JSON))
                                    .andReturn();

        System.out.println("HTTP STATUS : " + result.getResponse().getStatus());
        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus(), "HTTP STATUS 200 이 아닙니다.");
    }

    @DisplayName("외부 검색 API 타임아웃 - API 조회")
    @Test
    public void BlogTimeOutTest(){
        assertTimeout(Duration.ofMillis(3000), () -> searchService.getBlog("모니터" , Constant.SEARCH_SORT_PARAMETER.accuracy, 1 , 10));
    }

    @DisplayName("인기검색어 DB 조회 - DB 조회")
    @Test
    public void PopularKeyword(){
        assertNotNull(popularKeywordService.getPopularList(Constant.SEARCH_SIZE));
    }





}
