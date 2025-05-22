package dev.irof.kanjava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

/**
 * UDPで送信されるたメトリクスを受信する簡易実装です
 *
 * 使用できる環境では nc などの別プロセスで受信するほうが、実際にUDPで送信されていることがわかるかと思います。
 */
@Component
@ConditionalOnProperty("irof.udp-receiver.enabled")
public class UdpReceiver implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(UdpReceiver.class);
    private final int port;

    public UdpReceiver(@Value("${irof.udp-receiver.port}") int port) {
        this.port = port;
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        // 送信側でバッファリングされる場合 management.statsd.metrics.export.max-packet-length がMAXなのでそれ以上にする
        byte[] buffer = new byte[1400];

        try (DatagramSocket socket = new DatagramSocket(port)) {
            logger.debug("Listening for UDP packets on port {} ...", port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String received = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                logger.debug("Received:: {}", received);
            }
        }
    }
}
