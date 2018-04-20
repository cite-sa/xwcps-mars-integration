package gr.cite.earthserver.xwcpsmars.application.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Strings;
import gr.cite.earthserver.xwcpsmars.mars.request.InstantSerializer;

import java.time.Instant;

@JsonPropertyOrder({
		"id",
		"startTime",
		"endTime",
		"wcsRequest",
		"marsRequest",
		"requestTranslationTime",
		"marsRequestTime",
		//"marsRequestActualExecutionTime",
		"rasdamanRegistrationTime",
		"wcsRequestTime",
		"totalTime"
})

public class LoggedRequest {
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("startTime")
	@JsonSerialize(using = InstantSerializer.class)
	private Instant startTime;
	
	@JsonProperty("endTime")
	@JsonSerialize(using = InstantSerializer.class)
	private Instant endTime;
	
	@JsonProperty("requestTranslationTime")
	private Long requestTranslationTime;
	
	@JsonProperty("marsRequestTime")
	private Long marsRequestTime;
	
	/*@JsonProperty("marsRequestActualExecutionTime")
	private Long marsRequestActualExecutionTime;*/
	
	@JsonProperty("rasdamanRegistrationTime")
	private Long rasdamanRegistrationTime;
	
	@JsonProperty("wcsRequestTime")
	private Long wcsRequestTime;
	
	@JsonProperty("totalTime")
	private Long totalTime;
	
	@JsonProperty("marsRequest")
	private String marsRequest;
	
	@JsonProperty("wcsRequest")
	private String wcsRequest;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Instant getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}
	
	public Instant getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}
	
	public Long getRequestTranslationTime() {
		return requestTranslationTime;
	}
	
	public void setRequestTranslationTime(Long requestTranslationTime) {
		this.requestTranslationTime = requestTranslationTime;
	}
	
	public Long getMarsRequestTime() {
		return marsRequestTime;
	}
	
	public void setMarsRequestTime(Long marsRequestTime) {
		this.marsRequestTime = marsRequestTime;
	}
	
	/*public Long getMarsRequestActualExecutionTime() {
		return marsRequestActualExecutionTime;
	}
	
	public void setMarsRequestActualExecutionTime(Long marsRequestActualExecutionTime) {
		this.marsRequestActualExecutionTime = marsRequestActualExecutionTime;
	}*/
	
	public Long getRasdamanRegistrationTime() {
		return rasdamanRegistrationTime;
	}
	
	public void setRasdamanRegistrationTime(Long rasdamanRegistrationTime) {
		this.rasdamanRegistrationTime = rasdamanRegistrationTime;
	}
	
	public Long getWcsRequestTime() {
		return wcsRequestTime;
	}
	
	public void setWcsRequestTime(Long wcsRequestTime) {
		this.wcsRequestTime = wcsRequestTime;
	}
	
	public Long getTotalTime() {
		if (this.requestTranslationTime == null || this.marsRequestTime == null || this.rasdamanRegistrationTime == null || this.wcsRequestTime == null) return null;
		return this.requestTranslationTime + this.marsRequestTime + this.rasdamanRegistrationTime + this.wcsRequestTime;
	}
	
	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}
	
	public String getMarsRequest() {
		return marsRequest;
	}
	
	public void setMarsRequest(String marsRequest) {
		this.marsRequest = marsRequest;
	}
	
	public String getWcsRequest() {
		return wcsRequest;
	}
	
	public void setWcsRequest(String wcsRequest) {
		this.wcsRequest = wcsRequest;
	}
	
	@JsonIgnore
	public boolean isCompleted() {
		return !Strings.isNullOrEmpty(id) &&
				   startTime != null && endTime != null &&
				   requestTranslationTime != null && marsRequestTime != null && rasdamanRegistrationTime != null && wcsRequestTime != null &&
				   !Strings.isNullOrEmpty(marsRequest) && !Strings.isNullOrEmpty(wcsRequest);
	}
}
