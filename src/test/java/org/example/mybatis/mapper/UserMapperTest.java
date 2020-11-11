package org.example.mybatis.mapper;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.mybatis.entity.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @BeforeClass  : 该方法表示启动测试类对象测试之前启动的方法, 所以该方法必须是static 修饰的(可以通过类名直接访问).一般用来打开配置文件,初始化资源等
 * @AfterClass   ：该方法表示测试类对象测试完成之后启动的方法, 所以该方法必须是static 修饰的(可以通过类名直接访问).一般用来关闭数据库,结束资源等
 * @Before          :该方法表示调用每个测试方法前都会被调用一次
 * @After             :该方法表示调用每个测试方法后都会被调用一次
 * @Ignore          :已经被忽略的测试方法 ,我们测试的话,会自动过滤掉
 */

public class UserMapperTest {
    private static SqlSessionFactory factory = null;
//    private static UserMapper userMapper = null;

    @BeforeClass
    public static void init() throws IOException, ClassNotFoundException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-config.xml");
        factory = new SqlSessionFactoryBuilder().build(inputStream);
//        userMapper = factory.openSession().getMapper(UserMapper.class);
    }

    @Test
    public void findById() {
        try (SqlSession session1 = factory.openSession(); SqlSession session2 = factory.openSession()){
            UserMapper userMapper1 = session1.getMapper(UserMapper.class);
            System.out.println(userMapper1.findById(1));
//            session1.commit();
            UserMapper userMapper2 = session2.getMapper(UserMapper.class);
            System.out.println(userMapper2.findById(1));
            System.out.println(userMapper1 == userMapper2);

        }




    }

    @Test
    public void findAll() {
        SqlSession session = factory.openSession();
        final UserMapper userMapper = session.getMapper(UserMapper.class);
        Integer pageNum = 2;
        Integer pageSize = 10;//offset = (pageNum - 1) * pageSize

        //pagehelper用法https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/zh/HowToUse.md
        //第一种
        Page page1 = PageHelper.startPage(pageNum, pageSize, true);
        List<User> list1 = userMapper.findAll();
        System.out.println(list1);
        System.out.println(page1.getTotal());
        System.out.println(page1.getCountColumn());
        System.out.println(page1.getReasonable());
        System.out.println("-----------------------");


        //第二种用法,lambda用法(jdk8)
        Page<User> page2 = PageHelper.startPage(pageNum, pageSize).doSelectPage(()-> userMapper.findAll());
        System.out.println(page2.getResult());

        //第三种用法
        PageHelper.startPage(pageNum, pageSize);
        List<User> list3 = userMapper.findAll();
        //用PageInfo对结果进行包装
        PageInfo page3 = new PageInfo(list3);
        //测试PageInfo全部属性
        //PageInfo包含了非常全面的分页属性
        System.err.println(page3.getPageNum());
        System.err.println(page3.getPageSize());
        System.err.println(page3.getStartRow());
        System.err.println(page3.getEndRow());
        System.err.println(page3.getTotal());
        System.err.println(page3.getPages());
        System.err.println(page3.isHasPreviousPage());
        System.err.println(page3.isHasNextPage());

    }

    @Test
    public void update() {
        SqlSession session = factory.openSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setName("nobody");
        user.setUid(1);
        userMapper.update(user);

    }

    @Test
    public void add() {
        SqlSession session = factory.openSession(ExecutorType.BATCH);
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        for(int i = 0; i < 10000; i++){
            user.setName("Amy" + i);
            user.setAge(21);
            userMapper.add(user);
        }
        session.commit();
    }

    @Test
    public void addBatch() {
        SqlSession session = factory.openSession();
//        SqlSession session = factory.openSession(true);自动提交
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = new ArrayList<>();
        users.add(new User(null, "amy", 11, null));
        users.add(new User(null, "amy", 11, null));
        users.add(new User(null, "amy", 11, null));
        users.add(new User(null, "amy", 11, null));
        for (User user : users) {
            System.out.println(user);
        }
        userMapper.addBatch(users);
        session.commit();//没有设置自动提交时，要手工提交，否则数据库没有新插入的数据
        for (User user : users) {
            System.out.println(user);
        }

    }
}