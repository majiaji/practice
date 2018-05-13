package com.fantasy.practice.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by jiaji on 2018/4/17.
 */
@Controller
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "file_item") MultipartFile file) {
        logger.error(file.getOriginalFilename());
        return "done";
    }

}
