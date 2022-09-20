package com.soulchild.search.common.pager;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Pager {

    @JsonProperty("remain_count")
    private int remainCount;

    private int page;
    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("first_page")
    private int firstPage;

    @JsonProperty("last_page")
    private int lastPage;

    @JsonProperty("prev_page")
    private int prevPage;

    @JsonProperty("next_page")
    private int nextPage;

    @JsonProperty("first_yn")
    private boolean first;

    @JsonProperty("last_yn")
    private boolean last;

    public Pager(int page, int totalCount, int pageSize) {
        super();
        this.page = page;
        this.totalCount = totalCount;
        this.pageSize = pageSize;

        build();
    }

    protected void build() {
        lastPage = (totalCount > 0 && pageSize != 0) ? (int)(totalCount + pageSize - 1) / pageSize : 1;

        if(page > lastPage){
            page = lastPage;
        }

        if(page < 1){
            page = 1;
        }

        if(page == 1){
            first = true;
        }

        if(first) {
            prevPage = 1;
        }
        else {
            prevPage = page - 1;
        }

        last = (page == lastPage);
        if (last) {
            nextPage = lastPage;
        }
        else {
            nextPage = page + 1;
        }

        firstPage = 1;

        if(page < lastPage) {
            remainCount = totalCount - (page * pageSize);
        }
    }

}
