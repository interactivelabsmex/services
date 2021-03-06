package com.il.sod.config.jersey;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class CORSResponseFilter implements ContainerResponseFilter {
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
          throws IOException {
    MultivaluedMap<String, Object> headers = responseContext.getHeaders();
    headers.add("Access-Control-Allow-Origin", "*");
    headers.add("Access-Control-Allow-Methods", "GET, POST, PATCH, DELETE, PUT, OPTIONS");
    headers.add("Access-Control-Max-Age", "3600");
    headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, api_key, Authorization");
  }

}
