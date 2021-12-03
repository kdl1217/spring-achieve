package com.kon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.io.File;

/**
 * note
 *
 * @author com.kon, created on 2021/12/1T10:50.
 * @version 1.0.0-SNAPSHOT
 */
@Configuration
public class FileConfig {

    public final String INPUT_DIR = "/tmp/source";
    public final String OUTPUT_DIR = "/tmp/target";
    public final String FILE_PATTERN = "*.jpg";

    @Bean
    public MessageChannel fileChannel() {
//        return MessageChannels.direct().get();
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(channel = "fileChannel", poller = @Poller(fixedDelay = "3000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        // 过滤jpg格式文件
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }

    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    public MessageHandler fileWritingMessageHandler() {
        // 文件同步后会一直更新。
        FileWritingMessageHandler writingHandler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        writingHandler.setExpectReply(false);
        // 删除目标资源，
        writingHandler.setDeleteSourceFiles(true);
        return writingHandler;
    }

}
