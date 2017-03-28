package com.prasant.crawler;

import java.util.Set;

public class WebsiteLink {
	
	public WebsiteLink(String parentUrl) {
		super();
		this.parentUrl = parentUrl;
	}
	private String parentUrl;
	private Set<WebsiteLink> sameDomainLinks;
	private Set<String> externalLinks;
	private Set<String> staticResourcesLinks;
	
	public Set<WebsiteLink> getSameDomainLinks() {
		return sameDomainLinks;
	}
	public void setSameDomainLinks(Set<WebsiteLink> sameDomainLinks) {
		this.sameDomainLinks = sameDomainLinks;
	}
	public Set<String> getExternalLinks() {
		return externalLinks;
	}
	public void setExternalLinks(Set<String> externalLinks) {
		this.externalLinks = externalLinks;
	}
	public Set<String> getStaticResourcesLinks() {
		return staticResourcesLinks;
	}
	public void setStaticResourcesLinks(Set<String> staticResourcesLinks) {
		this.staticResourcesLinks = staticResourcesLinks;
	}
	public String getParentUrl(){
		return parentUrl;
	}
}
