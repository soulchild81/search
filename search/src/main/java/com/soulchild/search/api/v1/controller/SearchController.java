package com.soulchild.search.api.v1.controller;

import com.soulchild.search.api.model.BlogDto;
import com.soulchild.search.api.model.PopularKeywordDto;
import com.soulchild.search.api.service.PopularKeywordService;
import com.soulchild.search.api.service.SearchService;
import com.soulchild.search.common.Constant;
import com.soulchild.search.common.Exception.SearchException;
import com.soulchild.search.common.annotation.SearchApi;
import com.soulchild.search.common.pager.PagerList;
import com.soulchild.search.common.result.SearchResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags="검색", value="검색 관련 API")
@RestController
@RequestMapping("/search/v1")
public class SearchController {

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

    @GetMapping("/blog")
    @SearchApi
    @ApiOperation(value="인입된 쿼리로 카카오 , 네이버 검색 API 를 호출하여 블로그를 검색한다.", notes="블로그 검색")
    public SearchResult<PagerList<BlogDto>> searchBlog(@ApiParam(value="쿼리", required=true) @RequestParam(value="query",required=true) String query,
                                                       @ApiParam(value="정렬", required=true , allowableValues="accuracy , recency") @RequestParam(value="sort", required=false , defaultValue = "accuracy") Constant.SEARCH_SORT_PARAMETER sort,
                                                       @ApiParam(value="요청 페이지", required=true) @RequestParam(value="page", required=false , defaultValue = "1") int page,
                                                       @ApiParam(value="요청 사이즈", required=true) @RequestParam(value="size", required=false , defaultValue = "10") int size ){

        if(query.isEmpty()){
            throw new SearchException(Constant.RESULT_CODE.INVALID_PARAMS);
        }

        // 검색어 저장
        popularKeywordService.setPopular(query);

        //블로그 조회
        PagerList<BlogDto> blogList = searchService.getBlog(query , sort , page , size);

        if(blogList.getList() == null){
            throw new SearchException(Constant.RESULT_CODE.NONE_RESULT);
        }

        return new SearchResult<>(blogList);
    }

    @GetMapping("/popular/keyword")
    @SearchApi
    @ApiOperation(value="인기검색어 top 10 조회", notes="인기검색어 조회 ")
    public SearchResult<List<PopularKeywordDto>> popularKeyword(){
        // 인기 검색어 조회
        List<PopularKeywordDto> list = popularKeywordService.getPopularList(Constant.SEARCH_SIZE);
        return new SearchResult<>(list);
    }

}
