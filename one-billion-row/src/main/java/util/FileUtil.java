package util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

/**
 * @author bigbangz.github.io
 * @date 2024/7/21 下午9:08
 */
public class FileUtil {

    public void writeFile(int num) {
        // 定义要写入的文件路径
        Path filePath = Paths.get("./measurements.txt");

        // 定义要写入的内容
        String content = num + " :Hello, World!";

        try {
            // 将内容写入文件
            Files.write(filePath, Collections.singletonList(content), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
            System.out.println(num + "文件写入成功");
        } catch (IOException e) {
            System.err.println("文件写入失败： " + e.getMessage());
        }
    }
}
