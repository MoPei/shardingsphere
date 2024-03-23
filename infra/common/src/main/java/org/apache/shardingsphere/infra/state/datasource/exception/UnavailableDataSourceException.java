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

package org.apache.shardingsphere.infra.state.datasource.exception;

import org.apache.shardingsphere.infra.exception.core.external.server.ShardingSphereServerException;

import java.sql.SQLException;

/**
 * Data source state exception.
 */
public final class UnavailableDataSourceException extends ShardingSphereServerException {
    
    private static final long serialVersionUID = -8058761885303180333L;
    
    private static final String ERROR_CATEGORY = "DATA-SOURCE";
    
    private static final int ERROR_CODE = 1;
    
    public UnavailableDataSourceException(final SQLException cause, final String dataSourceName) {
        super(ERROR_CATEGORY, ERROR_CODE, String.format("Data source `%s` is unavailable.", dataSourceName), cause);
    }
}
