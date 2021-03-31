package demo.mina;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

public class Server {

    private static final int SERVER_PORT = 9123;

    public static void main(String[] args) throws Throwable {
        NioSocketAcceptor acceptor = new NioSocketAcceptor();

        acceptor.getFilterChain().addLast(
                "codec",
                new ProtocolCodecFilter(
                        new ObjectSerializationCodecFactory()));

        acceptor.getFilterChain().addLast("logger", new LoggingFilter());

        acceptor.setHandler(new ServerSessionHandler());
        acceptor.bind(new InetSocketAddress(SERVER_PORT));

        System.out.println("Listening on port " + SERVER_PORT);
    }
}
