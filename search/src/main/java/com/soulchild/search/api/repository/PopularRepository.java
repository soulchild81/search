package com.soulchild.search.api.repository;

import com.soulchild.search.api.entity.PopularKeyword;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.LockModeType;

public interface PopularRepository extends PagingAndSortingRepository<PopularKeyword, Integer> {
    // 인입된 쿼리 저장
    @Lock(LockModeType.PESSIMISTIC_READ)
    PopularKeyword findByKeyword(String query);

    // 인기검색어 10위까지 리스트 조회
    Page<PopularKeyword> findByOrderByCountDesc(Pageable page);
}
