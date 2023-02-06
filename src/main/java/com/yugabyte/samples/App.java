package com.yugabyte.samples;

import com.yugabyte.ysql.YBClusterAwareDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Program to connect to YugabyteDB (OSS, Anywhere or Managed)
 */
public class App {

  public static void main(String[] args) {
    String jdbcUrl = System.getProperty("jdbc.url");
    String jdbcUser = System.getProperty("jdbc.user");
    String jdbcPassword = System.getProperty("jdbc.password");
    String sslMode = System.getProperty("ssl.mode");
    String sslRootCert = System.getProperty("ssl.root.cert");
    String sql = System.getProperty("sql.command");
    String topologyKeys = System.getProperty("jdbc.topology.keys");
    String additionalEndpoints = System.getProperty("jdbc.additional.endpoints");

    YBClusterAwareDataSource ds = new YBClusterAwareDataSource();
    ds.setUrl(jdbcUrl);
    ds.setUser(jdbcUser);
    ds.setPassword(jdbcPassword);

    if (sslMode != null
        &&  !sslMode.isBlank()
      && "disable".equalsIgnoreCase(sslMode)){
      ds.setSsl(true);
      ds.setSslMode(sslMode);
      ds.setSslRootCert(sslRootCert);
    }
    if(topologyKeys != null && !topologyKeys.isBlank()){
      ds.setTopologyKeys(topologyKeys);
    }
    if(additionalEndpoints != null && !additionalEndpoints.isBlank()){
      ds.setAdditionalEndpoints(additionalEndpoints);
    }

    try (Connection conn = ds.getConnection();
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql)
    ) {
      System.out.println(">>>> Successfully connected to YugabyteDB!");
      while (rs.next()) {
        System.out.println(rs.getString(1));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
