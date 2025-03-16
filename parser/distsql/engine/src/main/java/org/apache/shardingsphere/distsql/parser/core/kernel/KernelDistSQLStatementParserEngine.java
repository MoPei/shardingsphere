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

package org.apache.shardingsphere.distsql.parser.core.kernel;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.apache.shardingsphere.sql.parser.api.ASTNode;
import org.apache.shardingsphere.sql.parser.core.ParseASTNode;
import org.apache.shardingsphere.sql.parser.core.SQLParserFactory;
import org.apache.shardingsphere.sql.parser.exception.SQLParsingException;
import org.apache.shardingsphere.sql.parser.statement.core.statement.SQLStatement;

/**
 * SQL statement parser engine for kernel DistSQL.
 */
public final class KernelDistSQLStatementParserEngine {
    
    /**
     * Parse SQL.
     *
     * @param sql SQL to be parsed
     * @return SQL statement
     */
    public SQLStatement parse(final String sql) {
        ASTNode astNode = SQLParserFactory.newInstance(sql, KernelDistSQLLexer.class, KernelDistSQLParser.class).parse();
        return getSQLStatement(sql, (ParseASTNode) astNode);
    }
    
    private SQLStatement getSQLStatement(final String sql, final ParseASTNode parseASTNode) {
        if (parseASTNode.getRootNode() instanceof ErrorNode) {
            throw new SQLParsingException(sql);
        }
        return (SQLStatement) (new KernelDistSQLStatementVisitor()).visit(parseASTNode.getRootNode());
    }
}
