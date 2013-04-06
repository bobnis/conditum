/*
 * Copyright 2013 Bobnis Innovations
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bobnis.conditum.util;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.Assert;

/**
 * Utilities for testing {@link Response}'s.
 */
public class ResponseUtils {

    /**
     * Asserts that the given expected response is equal to the given actual
     * response:
     * <ol>
     * <li>status</li>
     * <li>entity</li>
     * <li>metadata</li>
     * </ol>
     * 
     * @param expected
     *            the expected response, what it should be
     * @param actual
     *            the actual response, what it is
     * @see {@link Response#getStatus()}
     * @see {@link Response#getEntity()}
     * @see {@link Response#getMetadata()}
     * @see {@link Assert#assertEquals(long, long)}
     * @see {@link Assert#assertEquals(Object, Object)}
     */
    public static void assertEqual(Response expected, Response actual) {
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getEntity(), actual.getEntity());
        assertEquals(expected.getMetadata(), actual.getMetadata());
    }
}
