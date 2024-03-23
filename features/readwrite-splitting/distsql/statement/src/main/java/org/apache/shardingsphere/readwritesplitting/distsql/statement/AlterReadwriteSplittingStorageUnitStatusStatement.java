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

package org.apache.shardingsphere.readwritesplitting.distsql.statement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.distsql.statement.ral.updatable.UpdatableRALStatement;
import org.apache.shardingsphere.sql.parser.sql.common.segment.generic.DatabaseSegment;
import org.apache.shardingsphere.sql.parser.sql.common.statement.available.FromDatabaseAvailable;

import java.util.Optional;

/**
 * Set readwrite-splitting status statement.
 */
@RequiredArgsConstructor
@Getter
public final class AlterReadwriteSplittingStorageUnitStatusStatement extends UpdatableRALStatement implements FromDatabaseAvailable {
    
    private final DatabaseSegment database;
    
    private final String ruleName;
    
    private final String storageUnitName;
    
    private final boolean enable;
    
    @Override
    public Optional<DatabaseSegment> getDatabase() {
        return Optional.ofNullable(database);
    }
}
