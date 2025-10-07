package org.emilia.tienchin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author tienchin
 */
@SpringBootApplication
@MapperScan("org.emilia.tienchin.mapper")
public class TienChinApplication {
    public static void main(String[] args) {
         System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(TienChinApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  TienChin健身启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
