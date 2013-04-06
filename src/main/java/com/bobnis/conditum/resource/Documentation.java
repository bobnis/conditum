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

import static javax.ws.rs.core.MediaType.TEXT_HTML;
import static org.apache.commons.io.Charsets.UTF_8;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;

import com.sun.jersey.spi.resource.Singleton;

/**
 * A resource for displaying an injected HTML document.
 */
@Singleton
@Path("/")
@Produces(TEXT_HTML)
public class Documentation {

    private static final Logger log = Logger.getLogger(Documentation.class);

    /** container for {@link Documentation#resource}'s contents */
    private static Response RESPONSE = null;

    /** a valid HTML document */
    private Resource resource;

    @Required
    public void setPage(Resource page) {
        this.resource = page;
    }

    /**
     * Initializes {@link Documentation#RESPONSE} from the set
     * {@link Documentation#resource}.
     */
    @PostConstruct
    public void init() {
        InputStream input = null;
        try {
            input = resource.getInputStream();
        } catch (IOException e) {
            log.error("problem opening stream", e);
        }

        if (input != null) {
            try {
                final String html = IOUtils.toString(input, UTF_8);
                RESPONSE = Response.ok(html).build();
                log.debug("documentation successfully loaded");
            } catch (IOException e) {
                log.error("problem reading stream", e);
            } finally {
                try {
                    input.close();
                } catch (IOException e) {
                    log.error("problem closing stream", e);
                }
            }
        }
    }

    /**
     * Returns a <tt>200 OK</tt> {@link Response}.
     * 
     * @response.representation.200.mediaType text/html
     * 
     * @return a 200 OK with {@link Documentation#resource} response body
     */
    @GET
    public Response documentation() {
        return RESPONSE;
    }
}
