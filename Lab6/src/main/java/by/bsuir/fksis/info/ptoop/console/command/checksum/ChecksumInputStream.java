package by.bsuir.fksis.info.ptoop.console.command.checksum;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.nio.file.FileSystemException;
import java.util.Arrays;
import java.util.zip.Adler32;
import java.util.zip.Checksum;

/**
 * Input stream which checks checksum
 */
public class ChecksumInputStream extends ByteArrayInputStream {
    public ChecksumInputStream(byte[] buf) {
        super(validateChecksum(buf));
    }

    public ChecksumInputStream(byte[] buf, int offset, int length) {
        super(validateChecksum(buf), offset, length);
    }

    private static byte[] validateChecksum(byte[] allData) {
        try {
            int checksumSize = allData[allData.length - 1];
            byte[] checkSum = Arrays.copyOfRange(allData, allData.length - checksumSize - 1, allData.length - 1);
            byte[] data = Arrays.copyOfRange(allData, 0, allData.length - checksumSize - 1);
            Checksum checksum = new Adler32();
            checksum.update(data);
            ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
            buffer.put(checkSum);
            buffer.flip();

            if (!Long.valueOf(checksum.getValue()).equals(buffer.getLong())) {
                throw new FileSystemException("Invalid checksum");
            } else {
                return data;
            }
        } catch (IOException | BufferUnderflowException | BufferOverflowException | ReadOnlyBufferException e) {
            throw new RuntimeException("Invalid checksum.");
        }
    }
}
