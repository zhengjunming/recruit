package com.qg.recruit.advice;

import com.qg.recruit.domain.Student;
import com.qg.recruit.dto.Result;
import com.qg.recruit.exception.RecruitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 郑俊铭
 * Date: 2017/12/1
 * Time: 22:26
 * No struggle, talent how to match the willfulness.
 * Description: 统一异常处理类
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {

    @ExceptionHandler(RecruitException.class)
    public Result handleException(RecruitException e) {
        return new Result<Student>(e.getStateEnum(), null);
    }
}
