package com.gtbr.gtbrshield.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndexWithParam(@RequestParam(required = false) String redirectUrl,
                                    @RequestParam(required = false) Long apiId) {
        return "index";
    }


}
