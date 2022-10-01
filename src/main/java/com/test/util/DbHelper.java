package com.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class DbHelper {
    private String driver;
    private String url;
    private String user;
    private String pwd;
    private Connection connection;

    // 自定义连接信息
    public DbHelper(String driver, String url, String user, String pwd) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.pwd = pwd;
        getConnection();
    }

    // 从配置文件创建读取信息
    public DbHelper() {
        InputStream is = this.getClass().getResourceAsStream("/db.properties");
        Properties p = new Properties();
        try {
            p.load(is);
            this.driver = p.get("driver").toString();
            this.url = p.get("url").toString();
            // sqlite3无需用户名密码
//            this.user = p.get("user").toString();
//            this.pwd = p.get("pwd").toString();
            getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //建立与数据库的连接
    private void getConnection(){
        try {
            if (this.connection == null || this.connection.isClosed()) {
                Class.forName(this.driver);
                this.connection = DriverManager.getConnection(this.url, this.user, this.pwd);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(0);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(1);
        }
    }

    //执行sql
    public int excecuteUpdate(String sql, List<Object> paramList) {
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            for (int i=0; i<paramList.size(); i++) {
                ps.setObject(i+1, paramList.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //执行查询
    public List<Map<String,Object>> executeQuery(String sql, List<Object> paramList)
    {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //设置参数
            if(paramList != null)
            {
                for(int i = 0; i < paramList.size(); i++)
                    preparedStatement.setObject(i+1, paramList.get(i));
            }
            //执行sql
            ResultSet resultSet = preparedStatement.executeQuery();
            //将结果集中的数据读取出来  存到集合中
            //返回的是当前查询出来的表结构中的所有的列的信息
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //存放所有的记录
            List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
            while(resultSet.next())
            {
                String columnName;
                Object columnValue;
                //查询当前记录中的所有列
                Map<String,Object> map=new HashMap<String,Object>();
                //找到结果集中的字段名
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++) {
                    columnName = resultSetMetaData.getColumnName(i);//获取列的名称
                    columnValue= resultSet.getObject(columnName);//根据列的名称获取列的值
                    //将每一列的数据存入map
                    map.put(columnName,columnValue);// name  'zhangsan'
                }
                //一行已经组装到了一个map中，再将map存入list
                list.add(map);
            }

            return list;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
