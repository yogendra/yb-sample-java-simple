package com.yugabyte.samples;
/**
 Copyright 2022 Yugabyte

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

import com.yugabyte.ysql.YBClusterAwareDataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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
      && !sslMode.isBlank()
      && "disable".equalsIgnoreCase(sslMode)) {
      ds.setSsl(true);
      ds.setSslMode(sslMode);
      ds.setSslRootCert(sslRootCert);
    }
    if (topologyKeys != null && !topologyKeys.isBlank()) {
      ds.setTopologyKeys(topologyKeys);
    }
    if (additionalEndpoints != null && !additionalEndpoints.isBlank()) {
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

