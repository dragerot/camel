/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.db;

import org.springframework.jdbc.datasource.DataSourceUtils;

/**
 *
 * @author toregard
 */
public class CustomJDBCAppender extends org.apache.log4j.jdbc.JDBCAppender{
    
    @Override
    protected java.sql.Connection getConnection() throws java.sql.SQLException {

         if(connection == null) {
             org.springframework.jdbc.datasource.DataSourceTransactionManager dstm = (org.springframework.jdbc.datasource.DataSourceTransactionManager) ExternalBeanFactory.getBean("txManager");
             if(dstm==null) throw new java.sql.SQLException("dstm is null");
             System.out.println("here");
             connection = DataSourceUtils.getConnection(dstm.getDataSource());
             return connection;
         } else{
             return connection;
         }
     }

    /* protected void execute(String sql) throws java.sql.SQLException {

         Connection con = null;
         Statement stmt = null;

         try {
             con = getConnection();
             stmt = con.createStatement();
             stmt.executeUpdate(sql);
             con.commit();
         } catch (SQLException e) {
            if (stmt != null)
                 stmt.close();
            closeConnection(con);
            throw e;
         } 
       }
     */
     protected void closeConnection() throws java.sql.SQLException {
             if (connection != null && !connection.isClosed())
                   connection.close();
     }
}
