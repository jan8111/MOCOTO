package com.xiaoi.shMonitor;

import com.xiaoi.shMonitor.moco.MocoServer;
import com.xiaoi.shMonitor.util.Runner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Runner.runExample(MocoServer.class);
    }



}
