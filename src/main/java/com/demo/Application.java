package com.demo;


import com.demo.config.MakeStateMachine;
import com.demo.config.SSMConfig;
import com.demo.config.EventsConfig;
import com.demo.config.StatesConfig;
import com.demo.config.TransitionConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableSwagger2
public class Application implements CommandLineRunner {

    @Autowired
    private EventsConfig eventsConfig;

    @Autowired
    private TransitionConfig transitionConfig;

    @Autowired
    private StatesConfig statesConfig;

    @Autowired
    private SSMConfig ssmConfig;
    @Autowired
    private MakeStateMachine makeStateMachine;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        makeStateMachine.getStateMachine().start();
    }

}
