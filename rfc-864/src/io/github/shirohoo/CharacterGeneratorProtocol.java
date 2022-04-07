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

    void handle(Socket socket) throws IOException, InterruptedException {
        int start = FIRST_PRINTABLE_CHARACTER;
        byte[] line = new byte[NUMBER_OF_CHARACTERS_PER_LINE + 2];

        while (true) {
            Thread.sleep(1_000); //ms

            for (int i = start; i < start + NUMBER_OF_CHARACTERS_PER_LINE; i++) {
                line[i - start] = (byte) (((i - FIRST_PRINTABLE_CHARACTER) % NUMBER_OF_PRINTABLE_CHARACTER) + FIRST_PRINTABLE_CHARACTER);
            }
            line[72] = (byte) '\r';
            line[73] = (byte) '\n';

            OutputStream out = socket.getOutputStream();
            out.write(line);
            log.info("[Thread-%s] send: %s".formatted(Thread.currentThread().getId(), new String(line)));

            start = ((start + 1) - FIRST_PRINTABLE_CHARACTER) % NUMBER_OF_PRINTABLE_CHARACTER + FIRST_PRINTABLE_CHARACTER;
        }
    }
}
