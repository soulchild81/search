package com.soulchild.search.api.service.impl;

import com.soulchild.search.api.model.BlogDto;
import com.soulchild.search.api.remote.SearchKakaoApiClient;
import com.soulchild.search.api.remote.SearchNaverApiClient;
import com.soulchild.search.api.remote.model.KakaoBlog;
import com.soulchild.search.api.remote.model.NaverBlog;
import com.soulchild.search.api.service.SearchService;
import com.soulchild.search.common.Constant;
import com.soulchild.search.common.pager.PagerList;
import com.soulchild.search.common.pager.Pager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    public SearchKakaoApiClient searchKakaoApiClient;

    @Resource
    public SearchNaverApiClient searchNaverApiClient;

    @Override
    public PagerList<BlogDto> getBlog(String query , Constant.SEARCH_SORT_PARAMETER sort , int page , int size){
        List<BlogDto> blogList = new ArrayList<>();
        int total = 0;

        try{
            KakaoBlog kakaoBlog = searchKakaoApiClient.getKakaoBlog(Constant.SEARCH_CATEGORY.WEB.getDesc() , query , sort.getKakao() , page , size);
            total = kakaoBlog.getMeta().getTotal_count();
            if(!kakaoBlog.getDocuments().isEmpty()){
                blogList = kakaoBlog.getDocuments().stream().map(s -> new BlogDto(s.getContents() , s.getTitle() , s.getUrl() , s.getDatetime())).collect(Collectors.toList());
            }
            //throw new Exception();
        }catch(Exception e){
            NaverBlog naverBlog = searchNaverApiClient.getNaverBlog(query , sort.getNaver() , page , size);
            total = naverBlog.getTotal();
            if(!naverBlog.getItems().isEmpty()){
                blogList = naverBlog.getItems().stream().map(s -> new BlogDto(s.getDescription() , s.getTitle() , s.getLink() , s.getPostdate())).collect(Collectors.toList());
            }
        }
        PagerList list = PagerList.builder().list(blogList).pager(new Pager(page, total , size)).build();
        return list;
    }

}
