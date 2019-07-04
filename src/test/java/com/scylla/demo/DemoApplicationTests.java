package com.scylla.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebFluxTest(DemoApplication.class)
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
    }

}
