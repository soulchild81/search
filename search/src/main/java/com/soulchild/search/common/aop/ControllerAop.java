package com.soulchild.search.common.aop;

import com.soulchild.search.common.Constant;
import com.soulchild.search.common.Exception.SearchException;
import com.soulchild.search.common.annotation.SearchApi;
import com.soulchild.search.common.result.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAop {
    @Around(value="@annotation(searchApi)")
    public SearchResult<Object> process(ProceedingJoinPoint joinPoint, SearchApi searchApi) throws Throwable {
        SearchResult<Object> searchResult = new SearchResult<Object>();
        try{
            Object result = joinPoint.proceed();
            searchResult = (SearchResult<Object>)result;

            this.setResult(searchResult , Constant.RESULT_CODE.SUCCESS.getMsg(), Constant.RESULT_CODE.SUCCESS.name() , Integer.toString(Constant.RESULT_CODE.SUCCESS.getCode()));
            searchResult.setData(searchResult.getResult());
            return searchResult;
        }catch(Exception e){
            e.printStackTrace();
            if(e instanceof SearchException) {
                SearchException be = (SearchException) e;
                this.setResult(searchResult , be.getResult_code().getMsg(), be.getResult_code().name() , Integer.toString(be.getResult_code().getCode()));
            } else {
                this.setResult(searchResult , Constant.RESULT_CODE.UNKNOWN_ERROR.getMsg(), Constant.RESULT_CODE.UNKNOWN_ERROR.name(), Integer.toString(Constant.RESULT_CODE.UNKNOWN_ERROR.getCode()));
            }

            return searchResult;
        }
    }

    public void setResult(SearchResult<Object> searchResult , String msg , String type , String code){
        searchResult.setMessage(msg);
        searchResult.setMessage_type(type);
        searchResult.setReturn_code(code);
    }
}
