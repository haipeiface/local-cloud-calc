package demo.apachvfs;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class JschTest {
    public static void main(String[] args) throws Exception {
        ChannelSftp sftp = createSftp();
        System.out.println("done");
    }
    private static ChannelSftp createSftp() throws Exception {
        JSch jsch = new JSch();

        SftpProperties config = new SftpProperties();

        config.setUsername("root");
        config.setPassword("Abc123");
        config.setProtocol("sftp");
        config.setHost("192.168.203.121");
        config.setPort(22);
        config.setSessionConnectTimeout(150000);
        config.setChannelConnectedTimeout(150000);

        Session session = createSession(jsch, config.getHost(), config.getUsername(), config.getPort());
        session.setPassword(config.getPassword());
        session.connect(config.getSessionConnectTimeout());

        Channel channel = session.openChannel(config.getProtocol());
        channel.connect(config.getChannelConnectedTimeout());

        return (ChannelSftp) channel;
    }

    private static Session createSession(JSch jsch, String host, String username, Integer port) throws Exception {
        Session session = null;

        if (port <= 0) {
            session = jsch.getSession(username, host);
        } else {
            session = jsch.getSession(username, host, port);
        }

        if (session == null) {
            throw new Exception(host + " session is null");
        }

        session.setConfig("StrictHostKeyChecking","no");
        return session;
    }
}
