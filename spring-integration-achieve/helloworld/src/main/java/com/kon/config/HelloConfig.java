package com.kon.config;

import com.kon.handler.EndPoint;
import com.kon.handler.InputChannel;
import com.kon.handler.OutputHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;

/**
 * note
 *
 * @author kon, created on 2021/11/30T14:25.
 * @version 1.0.0-SNAPSHOT
 */
@Configuration
public class HelloConfig {

//    @Bean
//    public MessageChannel inputChannel() {
//        DirectChannel directChannel = new DirectChannel();
//        directChannel.subscribe(new InputHandler());
//        return directChannel;
////        return new QueueChannel(10);
//    }

    /**
     * 集成流
     * @see EndPoint 与这个配置一样。不能同时生效
     * @return IntegrationFlow
     */
    @Bean
    public IntegrationFlow hello() {
        return IntegrationFlows.from(inputChannel())
                // 过滤
                .filter("World"::equals)
                .transform(p -> p + " add transform")
                .handle(outputHandler())
                .get();
    }

    @Bean
    public InputChannel inputChannel() {
        return new InputChannel();
    }

    @Bean
    public OutputHandler outputHandler() {
        return new OutputHandler();
    }

//    @Bean
//    public DirectChannel filterChannel() {
//        return new DirectChannel();
//    }

    @Bean
    public DirectChannel transformChannel() {
        return new DirectChannel();
    }

    @Bean
    public DirectChannel consumerChannel() {
        return new DirectChannel();
    }


//    /**
//     * 这里配置之后，轮询到两个Handler执行
//     */
//    @Bean
//    @ServiceActivator(inputChannel = "consumerChannel")
//    public LoggingHandler logging() {
//        LoggingHandler adapter = new
//                LoggingHandler(LoggingHandler.Level.INFO);
//        adapter.setLoggerName("SIMPLE_LOGGER");
//        adapter.setLogExpressionString
//                ("headers.id + ': ' + payload");
//        return adapter;
//    }

}
