package cn.haigle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序。
 *
 * @author haigle
 * @date 2019/6/19 14:22
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class VirtueApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtueApplication.class, args);
        System.out.println("VIRTUE启动成功 \\( ^▽^ )/ \n");
    }

}
