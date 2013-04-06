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

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.resource.Singleton;

/**
 * A resource intended for load balancer health checks.
 */
@Singleton
@Path("/ping")
public class Ping {

    /**
     * 200 OK with no response body
     * <p>
     * NOTE: 204 No Content is not supported by all load balancers..
     * </p>
     * 
     * @see {@link Response#ok()}
     * @see {@link ResponseBuilder#build()}
     */
    private static final Response pong = Response.ok().build();

    /**
     * Returns a <tt>200 OK</tt> {@link Response}.
     * 
     * @return a 200 OK response with no response body
     * @see {@link Ping#pong}
     */
    @GET
    public Response ping() {
        return pong;
    }
}
