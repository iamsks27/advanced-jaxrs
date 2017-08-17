package org.shivam.learn.rest.filters;

import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.internal.util.Base64;


@Provider
public class SecurityFilter implements ContainerRequestFilter {

	private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	private static final String SECURED_URL_PREFIX = "secure";

	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)) {
			List<String> auth = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
			if (auth != null && auth.size() > 0) {
				String authToken = auth.get(0);
				authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
				String decodedToken = Base64.decodeAsString(authToken); //some issue while decoding the authorization header
				System.out.println(decodedToken);
				StringTokenizer tokenizer = new StringTokenizer(decodedToken, ":");
				String username = tokenizer.nextToken();
				String password = tokenizer.nextToken();

				if ("user".equals(username) && "password".equals(password)) {
					return;
				}
				
				//System.err.println(username + " " + password);
				Response unauthorizedResponse = Response.status(Response.Status.UNAUTHORIZED)
						.entity("user can't access any resource").build();

				requestContext.abortWith(unauthorizedResponse);
			}
			
		}
	}

}
