package demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@Getter
@Setter
@Slf4j
@RestController
class GreetingController {

    @Value("${acme.greeting}")
    String greeting;

    @GetMapping("/")
    public Object greet(@RequestParam(defaultValue = "World") String name) {

        log.info("Greet {}", name);

        return Collections.singletonMap("greeting", String.format("%s %s", greeting, name));
    }
}
