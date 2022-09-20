package com.soulchild.search.api.service;

import com.soulchild.search.api.entity.PopularKeyword;
import com.soulchild.search.api.model.PopularKeywordDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PopularKeywordService {
    // 인입 검색어 저장
    void setPopular(String query);

    //인기검색어 리스트 조회
    List<PopularKeywordDto> getPopularList(int size);
}
