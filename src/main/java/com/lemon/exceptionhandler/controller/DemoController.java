package com.lemon.exceptionhandler.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created on 2020/12/12 11:13.
 * 常用示例
 * @author lemon
 */
@Controller
public class DemoController {

    @RequestMapping("/go")
    public String redirect(){

        return "redirect:http://www.baidu.com";

    }

    /**
     * 下载文件
     */
    @GetMapping("/download/template/labor")
    public ResponseEntity<Object> laborFeeTemplate() throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        ContentDisposition contentDisposition = ContentDisposition.builder("attachment").filename("文件名.XLSX", StandardCharsets.UTF_8).build();
        httpHeaders.setContentDisposition(contentDisposition);
        return ResponseEntity.ok().headers(httpHeaders).body(StreamUtils.copyToByteArray(new ClassPathResource("template/labor_template.xlsx").getInputStream()));
    }
}
