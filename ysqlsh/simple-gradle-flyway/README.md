# YSQL / Simple Maven

This is a simple java application showing connection to YugabyteDB.

## Prerequisite
- Java 17 and above

## Build

```bash
git clone https://github.com/yogendra/yb-simple-sample.git yb-simple-sample
cd yb-simple-sample/ysql/simple-gradle-flyway
./gradlew assemble
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
  -jar build/libs/simple-gradle-flyway.jar
```
Example
```bash
java \
  -Djdbc.url=jdbc:yugabytedb://localhost:5433/yugabyte \
  -Djdbc.user=yugabyte \
  -Djdbc.password="" \
  -Dssl.mode=disable \
  -Dsql.command="select current_date, current_time, current_timestamp, now();"  \
  -jar build/libs/simple-gradle-flyway.jar
```

```bash
java \
  -Djdbc.url=jdbc:yugabytedb://localhost:5433/yugabyte \
  -Djdbc.user=yugabyte \
  -Djdbc.password="" \
  -Dssl.mode=disable \
  -Dsql.command="select NAME from PERSON;"  \
  -jar build/libs/simple-gradle-flyway.jar
```

## Flyway Migration

```bash
./gradlew flywayMigrate
```

Customize flyway properties either in `build.gradle` file or via command line with `-Pflyway.<propoerty>=<value>` parameter(s)


```bash
./gradlew flywayInfo
```
