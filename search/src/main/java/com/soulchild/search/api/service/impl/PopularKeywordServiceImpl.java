package com.soulchild.search.api.service.impl;

import com.soulchild.search.api.entity.PopularKeyword;
import com.soulchild.search.api.model.PopularKeywordDto;
import com.soulchild.search.api.repository.PopularRepository;
import com.soulchild.search.api.service.PopularKeywordService;
import com.soulchild.search.common.pager.Pager;
import com.soulchild.search.common.pager.PagerList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;

@Service
public class PopularKeywordServiceImpl implements PopularKeywordService {

    public PopularRepository popularRepository;
    @Autowired
    public void setSearchService(PopularRepository popularRepository) {
        this.popularRepository = popularRepository;
    }


    @Override
    @Transactional
    public void setPopular(String query){
        try{
            PopularKeyword popularKeyword = popularRepository.findByKeyword(query);
            if(popularKeyword == null) {
                PopularKeyword po = PopularKeyword.builder().keyword(query).count(1).regDate(new Date()).build();
                popularRepository.save(po);
            }else {
                popularKeyword.setCount(popularKeyword.getCount()+1);
                popularKeyword.setUpdDt(new Date());
                popularRepository.save(popularKeyword);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public PagerList<PopularKeywordDto> getPopularList(int size){
        List<PopularKeywordDto> popularList = new ArrayList<>();
        int total = 0;
        try{
            PageRequest req = PageRequest.of(0, size , Sort.by("count").descending());
            Page<PopularKeyword> list = popularRepository.findByOrderByCountDesc(req);
            total = list.getSize();
            if(!list.getContent().isEmpty()){
                popularList = list.getContent().stream().map(PopularKeywordDto::new).collect(Collectors.toList());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        PagerList list = PagerList.builder().list(popularList).pager(new Pager(1, total , 10)).build();
        return list;
    }
}
