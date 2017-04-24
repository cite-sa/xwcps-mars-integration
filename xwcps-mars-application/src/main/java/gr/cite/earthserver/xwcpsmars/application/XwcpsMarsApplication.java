package gr.cite.earthserver.xwcpsmars.application;

import gr.cite.earthserver.xwcpsmars.application.resources.XwcpsMarsResource;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("restAPI")
public class XwcpsMarsApplication extends ResourceConfig {
	public XwcpsMarsApplication() {
		register(JacksonFeature.class);
		register(XwcpsMarsResource.class);
	}
}