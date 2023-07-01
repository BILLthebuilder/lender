package com.lending.Service;

import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.IdentityInfo;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.security.PrivateKey;

@Service
public class SftpService {
    @Autowired
    private FileSystemManager fileSystemManager;

    @Value("${sftp.host}")
    private String sftpHost;

    @Value("${sftp.port}")
    private int sftpPort;

    @Value("${sftp.username}")
    private String sftpUsername;

    @Value("${sftp.password}")
    private String sftpPassword;

    @Value("${sftp.privateKey}")
    private PrivateKey sftpPrivateKey;

    @Value("${sftp.remoteFilePath}")
    private String sftpRemoteFilePath;

    public void uploadFile(String localFilePath) throws FileSystemException {
        FileSystemOptions opts = createDefaultOptions();

        FileObject localFile = fileSystemManager.resolveFile(localFilePath);
        FileObject remoteFile = fileSystemManager.resolveFile(createSftpUri(), opts);

        remoteFile.copyFrom(localFile, Selectors.SELECT_SELF);

        System.out.println("File uploaded successfully.");
    }

    private FileSystemOptions createDefaultOptions() throws FileSystemException {
        FileSystemOptions opts = new FileSystemOptions();

        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(opts, "no");
        SftpFileSystemConfigBuilder.getInstance().setUserDirIsRoot(opts, true);
        SftpFileSystemConfigBuilder.getInstance().setTimeout(opts, 10000);

        UserAuthenticator auth = new StaticUserAuthenticator(null, sftpUsername, sftpPassword);
        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);

     File pkey = new File(sftpPrivateKey);
        if (sftpPrivateKey != null) {
            SftpFileSystemConfigBuilder.getInstance().setIdentities(opts, new IdentityInfo[]{new IdentityInfo(pkey)});
        }

        return opts;
    }

    private String createSftpUri() {
        return "sftp://" + sftpUsername + "@" + sftpHost + ":" + sftpPort + sftpRemoteFilePath;
    }
}
