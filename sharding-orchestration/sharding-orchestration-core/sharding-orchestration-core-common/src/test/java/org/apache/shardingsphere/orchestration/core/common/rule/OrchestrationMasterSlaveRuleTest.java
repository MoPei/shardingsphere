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

package org.apache.shardingsphere.orchestration.core.common.rule;

import org.apache.shardingsphere.api.config.masterslave.LoadBalanceStrategyConfiguration;
import org.apache.shardingsphere.api.config.masterslave.MasterSlaveRuleConfiguration;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class OrchestrationMasterSlaveRuleTest {
    
    private final OrchestrationMasterSlaveRule orchestrationMasterSlaveRule = new OrchestrationMasterSlaveRule(
            new MasterSlaveRuleConfiguration("test_ms", "master_db", Arrays.asList("slave_db_0", "slave_db_1"), new LoadBalanceStrategyConfiguration("RANDOM")));
    
    @Test
    public void assertGetSlaveDataSourceNamesWithoutDisabledDataSourceNames() {
        assertThat(orchestrationMasterSlaveRule.getSlaveDataSourceNames(), is(Arrays.asList("slave_db_0", "slave_db_1")));
    }
    
    @Test
    public void assertGetSlaveDataSourceNamesWithDisabledDataSourceNames() {
        orchestrationMasterSlaveRule.updateDisabledDataSourceNames("slave_db_0", true);
        assertThat(orchestrationMasterSlaveRule.getSlaveDataSourceNames(), is(Collections.singletonList("slave_db_1")));
    }
    
    @Test
    public void assertUpdateDisabledDataSourceNamesForDisabled() {
        orchestrationMasterSlaveRule.updateDisabledDataSourceNames("slave_db_0", true);
        assertThat(orchestrationMasterSlaveRule.getDisabledDataSourceNames(), is(Collections.singleton("slave_db_0")));
    }
    
    @Test
    public void assertUpdateDisabledDataSourceNamesForEnabled() {
        orchestrationMasterSlaveRule.updateDisabledDataSourceNames("slave_db_0", true);
        orchestrationMasterSlaveRule.updateDisabledDataSourceNames("slave_db_0", false);
        assertThat(orchestrationMasterSlaveRule.getDisabledDataSourceNames(), is(Collections.emptySet()));
    }
}
