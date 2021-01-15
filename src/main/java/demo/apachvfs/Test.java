package demo.apachvfs;

import com.jcraft.jsch.*;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.provider.sftp.SftpFileSystemConfigBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public static void main(String[] args) throws Exception {

        String username = "root";
        String password = "Abc123";
        String remoteHost = "192.168.203.121";
        String port = "22";
        String remoteDir = "/www/sftp/root";
        String localFile = "D:\\data0\\zaoandushuren.mp4";
        FileSystemOptions fileSystemOptions = new FileSystemOptions();
//        SftpFileSystemConfigBuilder.getInstance().setUserInfo();
        SftpFileSystemConfigBuilder.getInstance().setStrictHostKeyChecking(fileSystemOptions, "no");
        SftpFileSystemConfigBuilder.getInstance().setConnectTimeoutMillis(fileSystemOptions, 150000);
        SftpFileSystemConfigBuilder.getInstance().setSessionTimeoutMillis(fileSystemOptions,150000);
//        需要删掉 C:\Users\peifs\.ssh\下的内容

//        SftpFileSystemConfigBuilder.getInstance().setPreferredAuthentications(fileSystemOptions, "gssapi-with-mic");
//        StaticUserAuthenticator auth = new StaticUserAuthenticator("", username, password);
//        DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(fileSystemOptions, auth);
//        DefaultFileSystemConfigBuilder.getInstance(

        FileSystemManager manager = VFS.getManager();
        String url = "sftp://root:Abc123@192.168.203.121:22/www/sftp/root";

        FileObject local = manager.resolveFile(
                localFile);
        FileObject remote = manager.resolveFile(url,fileSystemOptions);

        remote.copyFrom(local, new AllFileSelector());
//
        local.close();
        remote.close();
    }


}
