# YSQL / Simple Maven

This is a simple java application showing connection to YugabyteDB.

## Prerequisite
- Java 17 and above

## Build

```bash
git clone https://github.com/yogendra/yb-simple-sample.git yb-simple-sample
cd yb-simple-sample/ysql/simple-maven
./mvnw package
```


## Run

```bash
java \
  -Djdbc.url=jdbc:yugabytedb://<yugabytedb-hostname>:5433/yugabyte \
  -Djdbc.user=yugabyte \
  -Djdbc.password="Myp@ssw0rd" \
  -Dssl.mode=verify-full \
  -Dssl.root.cert=./parth-to-cert.cer \
  -Dsql.command="select current_date, current_time, current_timestamp, now();"  \
  -jar target/ysql-simple-1.0-SNAPSHOT-jar-with-dependencies.jar
```
