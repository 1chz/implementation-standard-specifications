package io.github.shirohoo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Logger;

final class CharacterGeneratorProtocol {
    private static final Logger log = Logger.getLogger(CharacterGeneratorProtocol.class.getSimpleName());

    private static final int FIRST_PRINTABLE_CHARACTER = 33;
    private static final int NUMBER_OF_PRINTABLE_CHARACTER = 94;
    private static final int NUMBER_OF_CHARACTERS_PER_LINE = 72;

    @SuppressWarnings("all")
    void handle(Socket socket) {
        int start = FIRST_PRINTABLE_CHARACTER;
        byte[] line = new byte[NUMBER_OF_CHARACTERS_PER_LINE + 2];

        try (socket) {
            while (true) {
                Thread.sleep(100);

                for (int i = start; i < start + NUMBER_OF_CHARACTERS_PER_LINE; i++) {
                    line[i - start] = (byte) (((i - FIRST_PRINTABLE_CHARACTER) % NUMBER_OF_PRINTABLE_CHARACTER) + FIRST_PRINTABLE_CHARACTER);
                }
                line[72] = 0x0D; // ASCII '\r'
                line[73] = 0x0A; // ASCII '\n'

                OutputStream out = socket.getOutputStream();
                out.write(line);
                log.info("[Thread-%s] send: %s".formatted(Thread.currentThread().getId(), new String(line)));

                start = ((start + 1) - FIRST_PRINTABLE_CHARACTER) % NUMBER_OF_PRINTABLE_CHARACTER + FIRST_PRINTABLE_CHARACTER;
            }
        } catch (IOException e) {
            log.severe("I/O error occurs when creating the output stream or the socket is not connected.");
        } catch (InterruptedException e) {
            log.severe("A thread has interrupted the current thread.");
        }
    }
}
