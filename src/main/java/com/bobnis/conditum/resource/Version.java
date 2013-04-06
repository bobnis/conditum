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

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;

import com.sun.jersey.spi.resource.Singleton;

/**
 * A resource intended for exposing this application's metadata, such as
 * version, name and description, from the set {@link Resource}'s loaded
 * {@link Properties}.
 */
@Singleton
@Path("/version")
@Produces(APPLICATION_JSON)
public class Version {

    private static final Logger log = LoggerFactory.getLogger(Version.class);

    /** container for a 200 OK with JSON body */
    private static Response RESPONSE = null;

    /** a valid property resource */
    private Resource resource;

    @Required
    public void setVersion(Resource resource) {
        this.resource = resource;
    }

    /**
     * Initializes {@link Version#RESPONSE} from the set
     * {@link Version#resource}.
     */
    @PostConstruct
    public void init() {
        final Properties properties = new Properties();

        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (IOException e) {
            log.error("problem opening stream", e);
        }

        if (inputStream != null) {
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                log.error("problem loading properties", e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("probem closing stream", e);
                }
            }
        }

        log.debug("loaded properties: {}", properties);

        final JSONObject json = new JSONObject(properties);
        RESPONSE = Response.ok(json.toString()).build();
    }

    /**
     * Returns a <tt>200 OK</tt> {@link Response} with a JSON body.
     * 
     * @response.representation.200.mediaType application/json
     * 
     * @return a 200 OK with {@link Version#resource}'s response body
     */
    @GET
    public Response version() {
        return RESPONSE;
    }
}
