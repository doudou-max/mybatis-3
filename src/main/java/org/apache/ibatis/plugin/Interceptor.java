/**
 *    Copyright 2009-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * mybatis 的拦截器
 * 使用场景：
 *    分页功能
 *    公共字段赋值
 *    性能监控日志
 *
 * 可以拦截的方法：
 *    执行器 Executor(query,update,commit,rollback...)
 *    SQL语句构造器 StatementHandler
 *    参数处理器ParameterHandler 和 结果处理器ResultSetHandler
 *
 * 使用插件
 *    在 mybatis-config.xml 文件的 <configuration/> 标签中指定 <plugin/>
 *
 * mybatis-plus 分页插件
 *    PaginationInterceptor
 *
 * 参考文章：
 *    https://blog.csdn.net/weixin_52851967/article/details/125190987
 *    https://www.cnblogs.com/redwinter/p/16607597.html
 *
 * @author Clinton Begin
 */
public interface Interceptor {

  /** 拦截方法 */
  Object intercept(Invocation invocation) throws Throwable;

  /** 插件注入 */
  Object plugin(Object target);

  void setProperties(Properties properties);

}
