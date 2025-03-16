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

package org.apache.shardingsphere.test.e2e.agent.engine.env.props;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Agent E2E environment properties.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AgentE2EEnvironmentProperties {
    
    /**
     * Load properties.
     *
     * @param propsFileName properties file name
     * @return properties
     */
    @SneakyThrows(IOException.class)
    @SuppressWarnings("AccessOfSystemProperties")
    public static Properties loadProperties(final String propsFileName) {
        Properties result = new Properties();
        try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propsFileName)) {
            result.load(inputStream);
        }
        for (String each : System.getProperties().stringPropertyNames()) {
            result.setProperty(each, System.getProperty(each));
        }
        return result;
    }
}
