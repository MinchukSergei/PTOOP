package by.bsuir.fksis.info.ptoop.console.command.checksum;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * Output stream which add checksum to file
 */
public class ChecksumOutputStream extends BufferedOutputStream {
    private Checksum checksum;
    public ChecksumOutputStream(OutputStream out) {
        super(out);
         checksum = new Adler32();
    }

    public ChecksumOutputStream(OutputStream out, int size) {
        super(out, size);
        checksum = new Adler32();
    }

    @Override
    public synchronized void write(int b) throws IOException {
        super.write(b);
        checksum.update(b);
    }

    @Override
    public synchronized void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
        checksum.update(b, off, len);
    }

    @Override
    public void write(byte[] b) throws IOException {
        super.write(b);
        checksum.update(b);
    }

    @Override
    public void close() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(checksum.getValue());
        this.write(buffer.array());
        this.write(buffer.array().length);
        super.close();
    }
}
