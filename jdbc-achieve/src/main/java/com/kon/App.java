package com.kon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot Launcher
 *
 *      如果你加载一个实体，SQL 语句就会运行。完成此操作后，您将拥有一个完全加载的实体。没有进行延迟加载或缓存。
 *      如果你保存一个实体，它就会被保存。如果你不这样做，它不会。没有脏跟踪，也没有会话。
 *      有一个关于如何将实体映射到表的简单模型。它可能只适用于相当简单的情况。如果您不喜欢那样，您应该编写自己的策略。Spring Data JDBC 仅对使用注释自定义策略提供非常有限的支持。
 *
 * @author kon, created on 2021/12/2T16:38.
 * @version 1.0.0-SNAPSHOT
 */
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
