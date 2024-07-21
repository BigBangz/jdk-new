package thread;

import util.FileUtil;

import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * @author bigbangz.github.io
 * @date 2024/7/21 下午9:05
 */
public class VirtualThreadV3 {
    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            FileUtil fileUtil = new FileUtil();
            IntStream.range(30_0000, 40_0000).forEach(i -> {
                executor.submit(() -> {
                    final int num = i;
                    fileUtil.writeFile(num);
                    return i;
                });
            });
            // 隐式调用 executor.close()方法
        }
    }
}
