/**
 *    Copyright 2009-2016 the original author or authors.
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
package org.apache.ibatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.session.ResultHandler;

/**
 * StatementHandler 就是管理 java.sql.Statement
 *
 * 有两个直接实现
 *    一个是 BaseStatementHandler，
 *    一个是 RoutingStatementHandler
 *
 *    BaseStatementHandler 有三个实现分别是 SimpleStatementHandler、PreparedStatementHandler、CallableStatementHandler
 *    分别管理的就是上面 Statement、PreparedStatement、CallableStatement 对象
 *
 *    BaseStatementHandler 使用适配器模式，减少了实现接口的复杂性
 *
 *    RoutingStatementHandler 则是包装三种Handler，作为一个代理类进行操
 *
 * @author Clinton Begin
 */
public interface StatementHandler {

  /** 创建 Statement 对象 */
  Statement prepare(Connection connection, Integer transactionTimeout)
      throws SQLException;

  /** 对 sql 中的占位符进行赋值 */
  void parameterize(Statement statement)
      throws SQLException;

  /** 添加打到批处理操作中 */
  void batch(Statement statement)
      throws SQLException;

  /** 批量更新操作 */
  int update(Statement statement)
      throws SQLException;

  /** 执行查询操作并返回结果 */
  <E> List<E> query(Statement statement, ResultHandler resultHandler)
      throws SQLException;

  /** 游标查询 */
  <E> Cursor<E> queryCursor(Statement statement)
      throws SQLException;

  /** 获取 BoundSql 对象 */
  BoundSql getBoundSql();

  /** 获取参数处理器 */
  ParameterHandler getParameterHandler();

}
