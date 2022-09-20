package com.soulchild.search.common.Exception;

import com.soulchild.search.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SearchException extends RuntimeException{

    public SearchException() {
        super();
    }

    public SearchException(Constant.RESULT_CODE resultCode) {
        super();
        this.result_code = resultCode;
    }

    private Constant.RESULT_CODE result_code;
}
