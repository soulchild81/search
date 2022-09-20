package com.soulchild.search.api.service;

import com.soulchild.search.api.model.PopularKeywordDto;
import com.soulchild.search.common.pager.PagerList;

public interface PopularKeywordService {
    // 인입 검색어 저장
    void setPopular(String query);

    //인기검색어 리스트 조회
    PagerList<PopularKeywordDto> getPopularList(int size);
}
