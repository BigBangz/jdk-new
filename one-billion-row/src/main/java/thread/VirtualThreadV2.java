package thread;

import util.FileUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author bigbangz.github.io
 * @date 2024/7/21 下午6:50
 */
public class VirtualThreadV2 {
    public static void main(String[] args) {
        // 创建调度器:
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        FileUtil fileUtil = new FileUtil();
        // 创建大量虚拟线程并调度:
        for (int i = 10_0000; i < 20_0000; i++) {
            // 也可以直接传入Runnable或Callable:
            final int num = i;
            executor.submit(() -> {
                fileUtil.writeFile(num);
            });
        }
        // 关闭ExecutorService，不再接受新的任务
        executor.close();
    }

}
