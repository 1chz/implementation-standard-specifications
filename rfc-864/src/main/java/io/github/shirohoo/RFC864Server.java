package io.github.shirohoo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Logger;

final class RFC864Server {
    private static final Logger log = Logger.getLogger(RFC864Server.class.getSimpleName());

    private static final int PORT = 8_000;

    /**
     * requested maximum length of the queue of incoming connections.
     */
    private static final int BACKLOG = 0;

    private final CharacterGeneratorProtocol protocol;

    RFC864Server() {
        this.protocol = new CharacterGeneratorProtocol();
    }

    @SuppressWarnings("all")
    void run() {
        log.info("RFC864 server start !");
        while (true) {
            try (ServerSocket socket = new ServerSocket(PORT, BACKLOG)) {
                Socket accept = socket.accept();
                protocol.handle(accept);
            } catch (UnknownHostException e) {
                log.severe("Host not found: %s".formatted(e.getMessage()));
            } catch (IOException e) {
                log.severe("I/O Error: %s".formatted(e.getMessage()));
            }
        }
    }
}
