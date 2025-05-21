package dev.irof.kanjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    @GetMapping
    void get() {
        logger.info("get()");
    }

    @PostMapping
    void post() {
        logger.info("post()");
    }
}
