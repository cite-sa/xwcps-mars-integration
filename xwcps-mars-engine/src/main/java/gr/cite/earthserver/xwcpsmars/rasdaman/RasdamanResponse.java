package gr.cite.earthserver.xwcpsmars.rasdaman;

import java.io.Serializable;

public class RasdamanResponse implements Serializable {
	private String contentType;
	
	private byte[] entity;
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public byte[] getEntity() {
		return entity;
	}
	
	public void setEntity(byte[] entity) {
		this.entity = entity;
	}
}
