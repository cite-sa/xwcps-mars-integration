package gr.cite.earthserver.xwcpsmars.application.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RequestTimings {
	@JsonProperty("requestTranslation")
	private Long requestTranslation;
	
	@JsonProperty("marsRequest")
	private Long marsRequest;
	
	@JsonProperty("rasdamanRegistration")
	private Long rasdamanRegistration;
	
	@JsonProperty("wcsRequest")
	private Long wcsRequest;
	
	@JsonProperty("total")
	private Long total;
	
	public Long getRequestTranslation() {
		return requestTranslation;
	}
	
	public void setRequestTranslation(Long requestTranslation) {
		this.requestTranslation = requestTranslation;
	}
	
	public Long getMarsRequest() {
		return marsRequest;
	}
	
	public void setMarsRequest(Long marsRequest) {
		this.marsRequest = marsRequest;
	}
	
	public Long getRasdamanRegistration() {
		return rasdamanRegistration;
	}
	
	public void setRasdamanRegistration(Long rasdamanRegistration) {
		this.rasdamanRegistration = rasdamanRegistration;
	}
	
	public Long getWcsRequest() {
		return wcsRequest;
	}
	
	public void setWcsRequest(Long wcsRequest) {
		this.wcsRequest = wcsRequest;
	}
	
	public Long getTotal() {
		return (this.requestTranslation != null ? this.requestTranslation : 0L) +
			(this.marsRequest != null ? this.marsRequest : 0L) +
			(this.rasdamanRegistration != null ? this.rasdamanRegistration : 0L) +
			(this.wcsRequest != null ? this.wcsRequest : 0L);
	}
	
	public void setTotal(Long total) {
		this.total = total;
	}
}
