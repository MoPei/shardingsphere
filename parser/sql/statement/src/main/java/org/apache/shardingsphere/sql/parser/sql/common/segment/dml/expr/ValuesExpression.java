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

package org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.shardingsphere.sql.parser.sql.common.segment.dml.assignment.InsertValuesSegment;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Values expression.
 */
@RequiredArgsConstructor
@Getter
public final class ValuesExpression implements ExpressionSegment {
    
    private final int startIndex;
    
    private final int stopIndex;
    
    private final Collection<InsertValuesSegment> rowConstructorList = new LinkedList<>();
    
    @Override
    public String getText() {
        StringBuilder result = new StringBuilder();
        for (InsertValuesSegment each : rowConstructorList) {
            result.append(each.getValues());
        }
        return result.toString();
    }
}
