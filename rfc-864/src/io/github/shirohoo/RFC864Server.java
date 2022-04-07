package io.github.shirohoo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

final class RFC864Server {
    private static final Logger log = Logger.getLogger(RFC864Server.class.getSimpleName());

    private static final int PORT = 8_000;

    private static final int BACKLOG = 8_000;

    private final CharacterGeneratorProtocol protocol;
    private final ExecutorService threadPool;

    RFC864Server() {
        this.protocol = new CharacterGeneratorProtocol();
        this.threadPool = Executors.newCachedThreadPool();
        log.info("RFC864 server start !");
    }

    void run() {
        while (true) {
            try (ServerSocket socket = new ServerSocket(PORT, BACKLOG)) {
                Socket accept = socket.accept();
                Runnable task = createTask(accept);
                threadPool.submit(task);
            } catch (UnknownHostException e) {
                log.severe("Host not found: %s".formatted(e.getMessage()));
            } catch (IOException e) {
                log.severe("I/O Error: %s".formatted(e.getMessage()));
            }
        }
    }

    private Runnable createTask(Socket accept) {
        return () -> {
            try {
                protocol.handle(accept);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    public static void main(String[] args) {
        new RFC864Server().run();
    }
}
