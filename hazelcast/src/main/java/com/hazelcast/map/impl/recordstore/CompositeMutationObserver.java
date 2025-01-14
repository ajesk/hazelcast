/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.map.impl.recordstore;

import com.hazelcast.internal.serialization.Data;
import com.hazelcast.map.impl.record.Record;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.LinkedList;

import static com.hazelcast.internal.util.CollectionUtil.isEmpty;
import static com.hazelcast.internal.util.ExceptionUtil.rethrow;

class CompositeMutationObserver<R extends Record> implements MutationObserver<R> {

    private Collection<MutationObserver<R>> mutationObservers;

    CompositeMutationObserver() {
    }

    void add(MutationObserver<R> mutationObserver) {
        if (mutationObservers == null) {
            mutationObservers = new LinkedList<>();
        }
        mutationObservers.add(mutationObserver);
    }

    @Override
    public void onClear() {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onClear();
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onPutRecord(Data key, R record,
                            Object oldValue, boolean backup) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onPutRecord(key, record, oldValue, backup);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onReplicationPutRecord(@Nonnull Data key,
                                       @Nonnull R record, boolean populateIndex) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onReplicationPutRecord(key, record, populateIndex);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onUpdateRecord(@Nonnull Data key, @Nonnull R record,
                               Object oldValue, Object newValue, boolean backup) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onUpdateRecord(key, record, oldValue, newValue, backup);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onRemoveRecord(Data key, R record) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onRemoveRecord(key, record);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onEvictRecord(Data key, R record) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onEvictRecord(key, record);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onLoadRecord(@Nonnull Data key, @Nonnull R record, boolean backup) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onLoadRecord(key, record, backup);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }


    @Override
    public void onDestroy(boolean isDuringShutdown, boolean internal) {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onDestroy(isDuringShutdown, internal);
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }

    @Override
    public void onReset() {
        if (isEmpty(mutationObservers)) {
            return;
        }

        Throwable exception = null;

        for (MutationObserver<R> mutationObserver : mutationObservers) {
            try {
                mutationObserver.onReset();
            } catch (Throwable t) {
                if (exception == null) {
                    exception = t;
                }
            }
        }

        if (exception != null) {
            rethrow(exception);
        }
    }
}
