package org.emilia.tienchin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.poi.ss.formula.functions.T;
import org.emilia.tienchin.mapper.SysMenuMapper;
import org.emilia.tienchin.pojo.AjaxResult;
import org.emilia.tienchin.pojo.entity.SysMenu;
import org.emilia.tienchin.pojo.vo.RouterVo;
import org.emilia.tienchin.service.SysMenuService;
import org.emilia.tienchin.service.impl.SysMenuServiceImpl;
import org.emilia.tienchin.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 江南一点雨
 * @ 江南一点雨
 * @网站 http://www.itboyhub.com
 * @国际站 http://www.emilia.org
 * @微信 a_java_boy
 * @GitHub https://github.com/lenve
 * @Gitee https://gitee.com/lenve
 */
@SpringBootTest
public class GenerateCode {

    @Autowired
    private SysMenuMapper mapper;
    @Autowired
    private SysMenuServiceImpl service;

    @Test
    public void ts1() {
        // 生成符合HS512要求的安全密钥
        SecretKey key = Jwts.SIG.HS512.key().build();
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

// 如果你需要将密钥保存为字符串，可以进行Base64编码
        String base64Key = Encoders.BASE64.encode(secretKey.getEncoded());
        String b2 = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(b2);
        System.out.println(base64Key);
    }

//    @Test
//    void generateContractCode() {
//
//        String path = "D:\\file\\tienchin";
//
//        FastAutoGenerator.create("jdbc:mysql:///tienchin?serverTimezone=Asia/Shanghai&useSSL=false", "tienchin", "123456")
//                .globalConfig(builder -> {
//                    builder.author("emilia") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(path + "/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("org.emilia.tienchin") // 设置父包名
//                            .moduleName("contract") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tienchin_contract") // 设置需要生成的表名
//                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//    @Test
//    void generateBusinessCode() {
//
//        String path = "/Users/sang/workspace/tienchin-video/code/tienchin/tienchin-business/src/main";
//
//        FastAutoGenerator.create("jdbc:mysql:///tienchin-video?serverTimezone=Asia/Shanghai&useSSL=false", "root", "123")
//                .globalConfig(builder -> {
//                    builder.author("emilia") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(path + "/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("org.emilia.tienchin") // 设置父包名
//                            .moduleName("business") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tienchin_business") // 设置需要生成的表名
//                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//
//    @Test
//    void generateClueCode() {
//
//        String path = "/Users/sang/workspace/tienchin-video/code/tienchin/tienchin-clue/src/main";
//
//        FastAutoGenerator.create("jdbc:mysql:///tienchin-video?serverTimezone=Asia/Shanghai&useSSL=false", "root", "123")
//                .globalConfig(builder -> {
//                    builder.author("emilia") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(path + "/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("org.emilia.tienchin") // 设置父包名
//                            .moduleName("clue") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tienchin_clue", "tienchin_assignment", "tienchin_follow_record") // 设置需要生成的表名
//                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//
//    @Test
//    void generateCourseCode() {
//
//        String path = "/Users/sang/workspace/tienchin-video/code/tienchin/tienchin-course/src/main";
//
//        FastAutoGenerator.create("jdbc:mysql:///tienchin-video?serverTimezone=Asia/Shanghai&useSSL=false", "root", "123")
//                .globalConfig(builder -> {
//                    builder.author("emilia") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(path + "/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("org.emilia.tienchin") // 设置父包名
//                            .moduleName("course") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tienchin_course") // 设置需要生成的表名
//                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//
//    @Test
//    void generateActivityCode() {
//
//        String path = "/Users/sang/workspace/tienchin-video/code/tienchin/tienchin-activity/src/main";
//
//        FastAutoGenerator.create("jdbc:mysql:///tienchin-video?serverTimezone=Asia/Shanghai&useSSL=false", "root", "123")
//                .globalConfig(builder -> {
//                    builder.author("emilia") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(path + "/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("org.emilia.tienchin") // 设置父包名
//                            .moduleName("activity") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tienchin_activity") // 设置需要生成的表名
//                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//
//    @Test
//    void generateChannelCode() {
//
//        String path = "/Users/sang/workspace/tienchin-video/code/tienchin/tienchin-channel/src/main/";
//
//        FastAutoGenerator.create("jdbc:mysql:///tienchin-video?serverTimezone=Asia/Shanghai&useSSL=false", "root", "123")
//                .globalConfig(builder -> {
//                    builder.author("emilia") // 设置作者
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir(path + "/java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("org.emilia.tienchin") // 设置父包名
//                            .moduleName("channel") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, path + "/resources/mapper")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("tienchin_channel") // 设置需要生成的表名
//                            .addTablePrefix("tienchin_"); // 设置过滤表前缀
//                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
}
