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

package com.bobnis.conditum.resource;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

/**
 * Test class for {@link Version}.
 */
public class VersionTest {

    private static final Logger log = LoggerFactory
            .getLogger(VersionTest.class);

    /** class under scrutiny */
    private Version resource;

    /** an arbitrary version */
    private static final String VERSION = "1.2.3-RC4";

    /** arbitrary properties loaded from {@link VersionTest#versionProperties} */
    private static final Properties properties = new Properties();

    /** a stubbed version.properties resource */
    private static Resource versionProperties;
    static {
        properties.put("version", VERSION);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            properties.store(out, "generated with love");
            versionProperties = new ByteArrayResource(out.toByteArray());
        } catch (IOException e) {
            log.error("problem storing properties", e);
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                log.error("problem closing stream", e);
            }
        }
    }

    @Before
    public void setup() {
        resource = new Version();
        resource.setVersion(versionProperties);
        resource.init();
    }

    @Test
    public void version() throws JSONException {
        final JSONObject json = new JSONObject();
        json.put("version", VERSION);

        final Response expected = Response.ok(json).build();
        final Response actual = resource.version();

        // TODO alternative to org.json: JSONObject does not override equals
        final String expectedJSON = expected.getEntity().toString();
        final String actualJSON = actual.getEntity().toString();

        // TODO ResponseUtils.assertEqual once JSONObject isn't used..
        assertEquals(expectedJSON, actualJSON);
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getMetadata(), actual.getMetadata());
    }
}
