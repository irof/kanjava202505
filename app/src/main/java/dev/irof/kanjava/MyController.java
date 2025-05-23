package dev.irof.kanjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class MyController {
    private static final Logger logger = LoggerFactory.getLogger(MyController.class);

    private final MyClient myClient;

    public MyController(MyClient myClient) {
        this.myClient = myClient;
    }

    @GetMapping("get")
    void get(@RequestHeader Map<String, String> headers) {
        logger.info("get(): {}", headers);
    }

    @PostMapping
    void post() {
        logger.info("post()");
    }

    /**
     * 自分の /get をHTTP経由で呼び出すことでコンテキスト伝播とかトレースを見るためのエンドポイントです
     */
    @GetMapping("call-get")
    void callGet() {
        logger.info("callGet()");
        myClient.callGet();
    }
}
