package br.edu.utfpr.tsi.singleton;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

public class Logger {
    private static volatile Logger INSTANCE = new Logger();
    private RandomAccessFile writer;
    private double counter;

    private Logger() {
        System.out.println("Created instance logger");
        var fileCounter = new File("counter.txt");
        try {
            this.writer = new RandomAccessFile(fileCounter, "rw");
            var file ="counter.txt";

            var reader = new FileReader(file);
            var tokenizer = new StreamTokenizer(reader);

            while (tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                if (tokenizer.ttype == StreamTokenizer.TT_WORD) {
                    if (tokenizer.sval.equals("called")) {
                        tokenizer.nextToken();
                        counter = tokenizer.nval;
                    }
                }
            }

            System.out.println("last called method: "+ counter);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public synchronized void writeFile()  {
        var channel = this.writer.getChannel();

        try {
            var lock = channel.tryLock();
            this.writer.seek(0);
            this.writer.writeBytes("method called " + ++counter);
            lock.release();
        } catch (final OverlappingFileLockException | IOException e) {
            try {
                this.writer.close();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
