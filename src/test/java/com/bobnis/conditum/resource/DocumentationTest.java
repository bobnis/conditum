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

import static com.bobnis.conditum.util.ResponseUtils.assertEqual;

import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

/**
 * Test class for {@link Documentation}.
 */
public class DocumentationTest {

    /** class under scrutiny */
    private Documentation resource;

    /** arbitrary HTML */
    private static final String HTML = "<html>some docs</html>";

    /** an injected HTML resource */
    private static final Resource page = new ByteArrayResource(HTML.getBytes());

    @Before
    public void setup() {
        resource = new Documentation();
        resource.setPage(page);
        resource.init();
    }

    @Test
    public void documentation() {
        final Response expected = Response.ok(HTML).build();
        final Response actual = resource.documentation();

        assertEqual(expected, actual);
    }
}
