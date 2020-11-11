package org.example.mybatis.mapper.generator;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/3 0:43
 */
public class MybatisGenerator {
    List<String> warnings = new ArrayList<>();
    static File file = null;
//    ConfigurationParser cp =
//    Configuration conf =
    @BeforeClass
    public static void init(){
//        file = new File("src/resources/mybatis/generatorConfig.xml");
//        file = new File("file.xml");
        file = new File("C:\\Users\\Chenzhimei\\Downloads\\data\\eclipse_project\\simple-maven-project\\src\\main\\resources\\mybatis\\generatorConfig.xml");
    }


    @Test
    public void generate() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        // 指定配置文件
        File configFile = new File("C:\\Users\\Chenzhimei\\Downloads\\data\\eclipse_project\\simple-maven-project\\src\\main\\resources\\mybatis\\generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
