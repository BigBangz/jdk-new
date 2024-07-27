package file;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Executors;

/**
 * 并发读取文件
 *
 * @author bigbangz.github.io
 */
public class ConcurrentReadFile {
    private static final String FILE = "./measurements.txt";

    public static void main(String[] args) throws IOException {
        final long parallels = 10_0000;

        try (FileChannel fileChannel = FileChannel.open(Path.of(FILE), StandardOpenOption.READ);
             var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            long fileSize = fileChannel.size();
            long chunkSize = Math.ceilDiv(fileSize, parallels);
            ConcurrentReadFile readFile = new ConcurrentReadFile();
            for (int i = 0; i < parallels; i++) {
                long startPosition = i * chunkSize;
                long endPosition = (i == parallels - 1) ? fileSize : startPosition + chunkSize;
                System.out.println(startPosition + "-" + endPosition);
                System.out.println(i + " 总");
                executor.submit(() -> readFile.segmentFile(fileChannel, startPosition, endPosition));
            }
        }
    }

    /**
     * todo 目前并发量 受到cpu核心数限制
     * @param fileChannel  fileChannel
     * @param startPosition 开始偏移量
     * @param endPosition 结束偏移量
     */
    public void segmentFile(FileChannel fileChannel, long startPosition, long endPosition){
        try {
            System.out.println(startPosition + "-" + endPosition);
            ByteBuffer buffer = ByteBuffer.allocate((int) (endPosition - startPosition));
            fileChannel.position(startPosition);
            fileChannel.read(buffer);
            buffer.flip();

            // 处理读取到的数据，例如打印或存储到其他数据结构中
            while (buffer.hasRemaining()) {
                System.out.print((char) buffer.get());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

