package com.lending.configuration.sftp;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SftpConfiguration {
    @Bean
    public FileSystemManager fileSystemManager() throws FileSystemException {
        StandardFileSystemManager manager = new StandardFileSystemManager();
        manager.init();
        return manager;
    }
}

