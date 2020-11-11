package org.example.mybatis.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/9 2:50
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "query", args = {Statement.class, ResultHandler.class})})
public class MyInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.err.println("拦截方法……intercept……");
        Object target = invocation.getTarget();
        System.err.println("目标对象： " + target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object parameter = metaObject.getValue("parameterHandler.parameterObject");
        BoundSql boundSql = (BoundSql)metaObject.getValue("boundSql");

        System.err.println("parameter-------------" + parameter);
        System.err.println("boundSql-------------" + boundSql);
        System.err.println("aql-------------" + boundSql.getSql());
//        System.err.println("aql-------------" + boundSql.getSql());
//        metaObject.setValue("", "");
        Object result = invocation.proceed();//放行
        System.err.println("执行结果" + result);
        return result;
    }

    @Override
    public Object plugin(Object target) {//将拦截器中定义的增强功能和原来的核心对象合并起来
        Object wrap = Plugin.wrap(target, this);
        System.out.println("plugin……" + wrap);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("设置属性：" + properties);
    }
}
