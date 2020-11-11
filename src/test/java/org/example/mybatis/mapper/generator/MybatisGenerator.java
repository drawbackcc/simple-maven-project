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
import java.io.InputStream;
import java.net.URL;
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
    static File configFile = null;
//    ConfigurationParser cp =
//    Configuration conf =
    @BeforeClass
    public static void init(){
        URL url = MybatisGenerator.class.getClassLoader().getResource("mybatis/generatorConfig.xml");
        configFile = new File(url.getFile());
    }



    @Test
    public void test(){
        // 获取URL
        URL url = getClass().getClassLoader().getResource("mybatis/generatorConfig.xml");
        // 通过url获取File的绝对路径
        File f = new File(url.getFile());
        System.out.println("文件的名子是:" + f.getName());
        System.out.println("文件的大小是:" + f.length());
    }


    @Test
    public void generate() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
