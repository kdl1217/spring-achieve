package com.kon.config;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.file.remote.gateway.AbstractRemoteFileOutboundGateway;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.ftp.gateway.FtpOutboundGateway;
import org.springframework.integration.ftp.outbound.FtpMessageHandler;
import org.springframework.integration.ftp.session.DefaultFtpSessionFactory;
import org.springframework.integration.ftp.session.FtpRemoteFileTemplate;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.io.File;

/**
 * note
 *
 * @author kon, created on 2021/12/1T14:01.
 * @version 1.0.0-SNAPSHOT
 */
@Configuration
public class FtpConfig {

    @Bean
    public MessageChannel lsChannel() {
        return new QueueChannel();
    }

    @Bean
    public MessageChannel downloadChannel() {
        return new QueueChannel();
    }

    @Bean
    public MessageChannel downloadsChannel() {
        return new QueueChannel();
    }

    @Bean
    public MessageChannel uploadChannel() {
        return new QueueChannel();
    }

    @Bean
    public SessionFactory<FTPFile> ftpSessionFactory() {
        DefaultFtpSessionFactory sessionFactory = new DefaultFtpSessionFactory();
        sessionFactory.setHost("your.ftp.ip");
        sessionFactory.setPort(21);
        sessionFactory.setUsername("your.ftp.userName");
        sessionFactory.setPassword("your.ftp.password");
        return new CachingSessionFactory<>(sessionFactory);
    }

//    @Bean
//    public FtpInboundFileSynchronizer sftpInboundFileSynchronizer() {
//        FtpInboundFileSynchronizer ftpInboundFileSynchronizer = new FtpInboundFileSynchronizer(ftpSessionFactory());
//        ftpInboundFileSynchronizer.setDeleteRemoteFiles(false);
//        ftpInboundFileSynchronizer.setRemoteDirectory("/");
//        ftpInboundFileSynchronizer.setFilter(new FtpSimplePatternFileListFilter("*.xlsx"));
//        return ftpInboundFileSynchronizer;
//    }

//    @Bean
//    @InboundChannelAdapter(channel = "ftpChannel", poller = @Poller(fixedDelay = "3000"))
//    public MessageSource<File> sftpMessageSource() {
//        FtpInboundFileSynchronizingMessageSource source =
//                new FtpInboundFileSynchronizingMessageSource(sftpInboundFileSynchronizer());
//        source.setLocalDirectory(new File("ftp-inbound"));
//        source.setAutoCreateLocalDirectory(true);
//        source.setLocalFilter(new AcceptOnceFileListFilter<>());
//        return source;
//    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(60));
        return pollerMetadata;
    }

    /**
     * 注入FtpRemoteFileTemplate
     *      可以通过FtpRemoteFileTemplate执行上传下载
     * @return FtpRemoteFileTemplate
     */
    @Bean
    public FtpRemoteFileTemplate ftpRemoteFileTemplate() {
        return new FtpRemoteFileTemplate(ftpSessionFactory());
    }

    @Bean
    @ServiceActivator(inputChannel = "lsChannel")
    public MessageHandler lsHandler() {
        FtpOutboundGateway ftpOutboundGateway = new FtpOutboundGateway(ftpSessionFactory(), "ls", "payload");
        //配置项
        ftpOutboundGateway.setOptions("-dirs");
        return ftpOutboundGateway;
    }

    @Bean
    @ServiceActivator(inputChannel = "downloadChannel")
    public MessageHandler downloadHandler() {
        FtpOutboundGateway ftpOutboundGateway = new FtpOutboundGateway(ftpSessionFactory(),
                AbstractRemoteFileOutboundGateway.Command.GET.getCommand(), "payload");
        ftpOutboundGateway.setOptions("-R");
        ftpOutboundGateway.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
        // 下载文件的指定目录
        ftpOutboundGateway.setLocalDirectory(FileUtils.getTempDirectory());
        ftpOutboundGateway.setAutoCreateLocalDirectory(true);
        ftpOutboundGateway.onError(new NullPointerException());
        return ftpOutboundGateway;
    }

    @Bean
    @ServiceActivator(inputChannel = "downloadsChannel")
    public MessageHandler downloadsHandler() {
        FtpOutboundGateway ftpOutboundGateway = new FtpOutboundGateway(ftpSessionFactory(),
                AbstractRemoteFileOutboundGateway.Command.MGET.getCommand(), "payload");
        ftpOutboundGateway.setOptions("-R");
        ftpOutboundGateway.setFileExistsMode(FileExistsMode.REPLACE_IF_MODIFIED);
        // 下载文件的指定目录
        ftpOutboundGateway.setLocalDirectory(FileUtils.getTempDirectory());
        ftpOutboundGateway.setAutoCreateLocalDirectory(true);
        ftpOutboundGateway.onError(new NullPointerException());
        return ftpOutboundGateway;
    }

    @Bean
    @ServiceActivator(inputChannel = "uploadChannel")
    public MessageHandler uploadHandler() {
        FtpMessageHandler handler = new FtpMessageHandler(ftpSessionFactory());
        // incar_pkg目录下的文件夹
        handler.setRemoteDirectoryExpression(new LiteralExpression("java"));
        // 如果文件夹不存在，自动创建
        handler.setAutoCreateDirectory(true);
        handler.setFileNameGenerator(message -> {
            if (message.getPayload() instanceof File) {
                return ((File) message.getPayload()).getName();
            } else {
                throw new IllegalArgumentException("File expected as payload.");
            }
        });
        return handler;
    }

}
