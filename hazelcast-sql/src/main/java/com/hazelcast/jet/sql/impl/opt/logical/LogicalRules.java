/*
 * Copyright 2021 Hazelcast Inc.
 *
 * Licensed under the Hazelcast Community License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://hazelcast.com/hazelcast-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.sql.impl.opt.logical;

import org.apache.calcite.rel.rules.CoreRules;
import org.apache.calcite.rel.rules.PruneEmptyRules;
import org.apache.calcite.tools.RuleSet;
import org.apache.calcite.tools.RuleSets;

public final class LogicalRules {

    private LogicalRules() {
    }

    public static RuleSet getRuleSet() {
        return RuleSets.ofList(
                // Filter rules
                FilterLogicalRule.INSTANCE,
                CoreRules.FILTER_MERGE,
                CoreRules.FILTER_PROJECT_TRANSPOSE,
                FilterIntoScanLogicalRule.INSTANCE,
                CoreRules.FILTER_AGGREGATE_TRANSPOSE,
                CoreRules.FILTER_INTO_JOIN,
                CoreRules.FILTER_REDUCE_EXPRESSIONS,

                // Project rules
                ProjectLogicalRule.INSTANCE,
                CoreRules.PROJECT_MERGE,
                CoreRules.PROJECT_REMOVE,
                CoreRules.PROJECT_FILTER_TRANSPOSE,
                ProjectIntoScanLogicalRule.INSTANCE,

                // Scan rules
                FullScanLogicalRule.INSTANCE,
                FullFunctionScanLogicalRules.SPECIFIC_FUNCTION_INSTANCE,
                FullFunctionScanLogicalRules.DYNAMIC_FUNCTION_INSTANCE,

                // Aggregate rules
                AggregateLogicalRule.INSTANCE,

                // Sort rules
                SortLogicalRule.INSTANCE,

                // Join rules
                JoinLogicalRule.INSTANCE,
                CoreRules.JOIN_PROJECT_RIGHT_TRANSPOSE_INCLUDE_OUTER,
                CoreRules.JOIN_REDUCE_EXPRESSIONS,

                // Union rules
                UnionLogicalRule.INSTANCE,

                // Value rules
                ValuesLogicalRules.CONVERT_INSTANCE,
                ValuesLogicalRules.FILTER_INSTANCE,
                ValuesLogicalRules.PROJECT_INSTANCE,
                ValuesLogicalRules.PROJECT_FILTER_INSTANCE,
                ValuesLogicalRules.UNION_INSTANCE,

                // DML rules
                InsertLogicalRule.INSTANCE,
                SinkLogicalRule.INSTANCE,
                UpdateLogicalRules.INSTANCE,
                UpdateLogicalRules.NOOP_INSTANCE,
                DeleteLogicalRule.INSTANCE,

                SelectByKeyMapLogicalRules.INSTANCE,
                SelectByKeyMapLogicalRules.PROJECT_INSTANCE,
                InsertMapLogicalRule.INSTANCE,
                SinkMapLogicalRule.INSTANCE,
                UpdateByKeyMapLogicalRule.INSTANCE,
                DeleteByKeyMapLogicalRule.INSTANCE,

                // Miscellaneous
                PruneEmptyRules.PROJECT_INSTANCE,
                PruneEmptyRules.FILTER_INSTANCE
        );
    }
}
