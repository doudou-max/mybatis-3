package org.sang.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.sql.Connection;
import java.util.Properties;

/**
 * @author: doudou
 * @since: 2022-11-28
 */
@Intercepts(
        {
                @Signature
                        (
                                type = StatementHandler.class,
                                method = "prepare",
                                args = {Connection.class, Integer.class}
                        )
        }
)
public class DemoInterceptor implements Interceptor {

    /**
     * 插件增强逻辑
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("插件增强");
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("获取属性值：" + properties);
    }

}
