package thread;

/**
 * 虚拟线程默认实现方式
 * @author bigbangz.github.io
 * @date 2024/7/21 下午6:08
 */
public class VirtualThreadV1 {
    public static void main(String[] args) {
        Thread customThread = Thread.startVirtualThread(() -> {
            System.out.println("CustomThread run");
            System.out.println("CustomThread open");
        });
        customThread.run();
        System.out.printf("main run");
        customThread.run();
    }
}


