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

package org.apache.shardingsphere.mode.manager.cluster.coordinator.subscriber;

import com.google.common.eventbus.Subscribe;
import org.apache.shardingsphere.mode.event.config.AlterDatabaseRuleConfigurationEvent;
import org.apache.shardingsphere.mode.event.config.DropDatabaseRuleConfigurationEvent;
import org.apache.shardingsphere.mode.event.config.global.AlterGlobalRuleConfigurationEvent;
import org.apache.shardingsphere.mode.event.config.global.AlterPropertiesEvent;
import org.apache.shardingsphere.mode.event.datasource.unit.AlterStorageUnitEvent;
import org.apache.shardingsphere.mode.event.datasource.unit.RegisterStorageUnitEvent;
import org.apache.shardingsphere.mode.event.datasource.unit.UnregisterStorageUnitEvent;
import org.apache.shardingsphere.mode.manager.ContextManager;

/**
 * Configuration changed subscriber.
 */
@SuppressWarnings("unused")
public final class ConfigurationChangedSubscriber {
    
    private final ContextManager contextManager;
    
    public ConfigurationChangedSubscriber(final ContextManager contextManager) {
        this.contextManager = contextManager;
        contextManager.getInstanceContext().getEventBusContext().register(this);
    }
    
    /**
     * Renew for register storage unit.
     *
     * @param event register storage unit event
     */
    @Subscribe
    public void renew(final RegisterStorageUnitEvent event) {
        if (!event.getActiveVersion().equals(contextManager.getMetaDataContexts().getPersistService().getMetaDataVersionPersistService().getActiveVersionByFullPath(event.getActiveVersionKey()))) {
            return;
        }
        contextManager.getConfigurationContextManager().registerStorageUnit(event.getDatabaseName(),
                contextManager.getMetaDataContexts().getPersistService().getDataSourceUnitService().load(event.getDatabaseName(), event.getStorageUnitName()));
    }
    
    /**
     * Renew for alter storage unit.
     *
     * @param event register storage unit event
     */
    @Subscribe
    public void renew(final AlterStorageUnitEvent event) {
        if (!event.getActiveVersion().equals(contextManager.getMetaDataContexts().getPersistService().getMetaDataVersionPersistService().getActiveVersionByFullPath(event.getActiveVersionKey()))) {
            return;
        }
        contextManager.getConfigurationContextManager().alterStorageUnit(
                event.getDatabaseName(), contextManager.getMetaDataContexts().getPersistService().getDataSourceUnitService().load(event.getDatabaseName(), event.getStorageUnitName()));
    }
    
    /**
     * Renew for unregister storage unit.
     *
     * @param event register storage unit event
     */
    @Subscribe
    public void renew(final UnregisterStorageUnitEvent event) {
        if (!contextManager.getMetaDataContexts().getMetaData().containsDatabase(event.getDatabaseName())) {
            return;
        }
        contextManager.getConfigurationContextManager().unregisterStorageUnit(event.getDatabaseName(), event.getStorageUnitName());
    }
    
    /**
     * Renew for database rule configuration.
     *
     * @param event database rule changed event
     */
    @Subscribe
    public synchronized void renew(final AlterDatabaseRuleConfigurationEvent event) {
        if (!contextManager.getMetaDataContexts().getMetaData().containsDatabase(event.getDatabaseName())) {
            return;
        }
        contextManager.getConfigurationContextManager().alterRuleConfiguration(event.getDatabaseName(), event.getRuleConfig());
    }
    
    /**
     * Renew for database rule configuration.
     *
     * @param event database rule changed event
     */
    @Subscribe
    public synchronized void renew(final DropDatabaseRuleConfigurationEvent event) {
        if (!contextManager.getMetaDataContexts().getMetaData().containsDatabase(event.getDatabaseName())) {
            return;
        }
        contextManager.getConfigurationContextManager().dropRuleConfiguration(event.getDatabaseName(), event.getRuleConfig());
    }
    
    /**
     * Renew for global rule configuration.
     *
     * @param event global rule alter event
     */
    @Subscribe
    public synchronized void renew(final AlterGlobalRuleConfigurationEvent event) {
        if (!event.getActiveVersion().equals(contextManager.getMetaDataContexts().getPersistService().getMetaDataVersionPersistService().getActiveVersionByFullPath(event.getActiveVersionKey()))) {
            return;
        }
        contextManager.getConfigurationContextManager().alterGlobalRuleConfiguration(contextManager.getMetaDataContexts().getPersistService().getGlobalRuleService().load(event.getRuleSimpleName()));
    }
    
    /**
     * Renew for global properties.
     *
     * @param event global properties alter event
     */
    @Subscribe
    public synchronized void renew(final AlterPropertiesEvent event) {
        if (!event.getActiveVersion().equals(contextManager.getMetaDataContexts().getPersistService().getMetaDataVersionPersistService().getActiveVersionByFullPath(event.getActiveVersionKey()))) {
            return;
        }
        contextManager.getConfigurationContextManager().alterProperties(contextManager.getMetaDataContexts().getPersistService().getPropsService().load());
    }
}
