package gr.cite.earthserver.xwcpsmars.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URI;

@JsonInclude(Include.NON_EMPTY)
public class XwcpsMarsResponseEntity<T> {

	@JsonProperty("href")
	private URI href;

	@JsonProperty("body")
	private T body;

	public XwcpsMarsResponseEntity() {

	}

	public XwcpsMarsResponseEntity(URI href) {
		this.href = href;
	}

	public XwcpsMarsResponseEntity(T body) {
		this.body = body;
	}

	public XwcpsMarsResponseEntity(URI href, T body) {
		this.href = href;
		this.body = body;
	}

	public URI getHref() {
		return href;
	}

	public XwcpsMarsResponseEntity<T> setHref(URI href) {
		this.href = href;
		return this;
	}

	public T getBody() {
		return body;
	}

	public XwcpsMarsResponseEntity<T> setBody(T body) {
		this.body = body;
		return this;
	}
	
	/*public static <T> Builder<T> builder() {
		return new Builder<T>();
	}*/
	
	/*public static class Builder<T> {
		
		private XwcpsMarsResponseEntity<T> entity;
		
		private Builder() {
			this.entity = new XwcpsMarsResponseEntity<T>();
		}
		
		public Builder<T> href(String href) {
			this.entity.setHref(href);
			return this;
		}
		
		public Builder<T> body(T body) {
			this.entity.setBody(body);
			return this;
		}
		
		public XwcpsMarsResponseEntity<T> build() {
			return this.entity;
		}
	}*/
}
