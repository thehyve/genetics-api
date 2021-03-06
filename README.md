## To deploy in production

[![codecov](https://codecov.io/gh/opentargets/genetics-api/branch/master/graph/badge.svg)](https://codecov.io/gh/opentargets/genetics-api)
[![Build Status](https://travis-ci.com/opentargets/genetics-api.svg)](https://travis-ci.com/opentargets/genetics-api)
[![Docker Repository on Quay](https://quay.io/repository/opentargets/genetics-api/status "Docker Repository on Quay")](https://quay.io/repository/opentargets/genetics-api)

You have to define `production.conf` file at the root of the project and it must
contain at least these lines

```
include "application.conf"
# https://www.playframework.com/documentation/latest/Configuration
http.port = 8080
http.port = ${?PLAY_PORT}
play.http.secret.key = "changeme"
play.http.secret.key = ${?PLAY_SECRET}

# Root logger:
logger=ERROR
# Logger used by the framework:
logger.play=ERROR
# Logger provided to your application:
logger.application=INFO

slick.dbs {
  default {
    profile = "clickhouse.ClickHouseProfile$"
    db {
      driver = "ru.yandex.clickhouse.ClickHouseDriver"
      url = "jdbc:clickhouse://machine.internal:8123/ot"
      url = ${?SLICK_CLICKHOUSE_URL}
      numThreads = 4
      queueSize = 128
    }
  }
  sumstats {
    profile = "clickhouse.ClickHouseProfile$"
    db {
      driver = "ru.yandex.clickhouse.ClickHouseDriver"
      url = "jdbc:clickhouse://machine2.internal:8123/sumstats"
      url = ${?SLICK_CLICKHOUSE_URL_SS}
      numThreads = 4
      queueSize = 128
    }
  }
}

ot.elasticsearch {
  host = "machine.internal:8123"
  host = ${?ELASTICSEARCH_HOST}
  port = 9200
}

# env vars to pass to docker image or `-D`
# http.port=${?PLAY_PORT}
# play.http.secret.key=${?PLAY_SECRET}
# slick.dbs.default.db.url=${?SLICK_CLICKHOUSE_URL}
# ot.elasticsearch.host=${?ELASTICSEARCH_HOST}
# slick.dbs.default.db.url=${?SLICK_CLICKHOUSE_URL_SS}

```


# Copyright
Copyright 2014-2018 Biogen, Celgene Corporation, EMBL - European Bioinformatics Institute, GlaxoSmithKline, Takeda Pharmaceutical Company and Wellcome Sanger Institute

This software was developed as part of the Open Targets project. For more information please see: http://www.opentargets.org

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
