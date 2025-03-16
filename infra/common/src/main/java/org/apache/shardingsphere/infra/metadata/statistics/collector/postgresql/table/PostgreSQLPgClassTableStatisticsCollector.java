/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.metadata.statistics.collector.postgresql.table;

import com.cedarsoftware.util.CaseInsensitiveMap;
import org.apache.shardingsphere.infra.metadata.ShardingSphereMetaData;
import org.apache.shardingsphere.infra.metadata.database.schema.model.ShardingSphereSchema;
import org.apache.shardingsphere.infra.metadata.database.schema.model.ShardingSphereTable;
import org.apache.shardingsphere.infra.metadata.statistics.collector.postgresql.PostgreSQLTableStatisticsCollector;

import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

/**
 * Table statistics collector for pg_catalog.pg_class of PostgreSQL.
 */
public final class PostgreSQLPgClassTableStatisticsCollector implements PostgreSQLTableStatisticsCollector {
    
    private static final String PUBLIC_SCHEMA = "public";
    
    @Override
    public Collection<Map<String, Object>> collect(final String databaseName, final String schemaName, final String tableName, final ShardingSphereMetaData metaData) throws SQLException {
        Collection<Map<String, Object>> result = new LinkedList<>();
        ShardingSphereSchema publicSchema = metaData.getDatabase(databaseName).getSchema(PUBLIC_SCHEMA);
        if (null != publicSchema) {
            for (ShardingSphereTable each : publicSchema.getAllTables()) {
                Map<String, Object> columnValues = new CaseInsensitiveMap<>(4, 1F);
                columnValues.put("oid", 0L);
                columnValues.put("relnamespace", 0L);
                columnValues.put("relname", each.getName());
                columnValues.put("relkind", "r");
                result.add(columnValues);
            }
        }
        return result;
    }
    
    @Override
    public String getSchemaName() {
        return "pg_catalog";
    }
    
    @Override
    public String getTableName() {
        return "pg_class";
    }
    
    @Override
    public String getType() {
        return "pg_catalog.pg_class";
    }
}
