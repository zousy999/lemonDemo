package com.lemon.validate.controller;

import com.lemon.exceptionhandler.vo.ResultVO;
import com.lemon.validate.bean.LogTypeEnum;
import com.lemon.validate.bean.ValidDtoIn;
import com.lemon.validate.bean.ValidDtoIn2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2020/12/15 9:29.
 *
 * @author lemon
 */
@RequestMapping("/valid")
@RestController
public class ValidController {

    @RequestMapping("/t1")
    public ResultVO<String> testValid(@RequestBody @Validated ValidDtoIn dtoIn){
        return new ResultVO<>(dtoIn.getLogType());
    }

    @RequestMapping("/t2")
    public ResultVO<LogTypeEnum> testValid(@RequestBody @Validated ValidDtoIn2 dtoIn){
        return new ResultVO<>(dtoIn.getLogType());
    }
}
