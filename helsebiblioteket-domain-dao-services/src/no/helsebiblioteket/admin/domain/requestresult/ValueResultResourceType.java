package no.helsebiblioteket.admin.domain.requestresult;

import no.helsebiblioteket.admin.domain.ResourceType;

public class ValueResultResourceType extends SingleResultResourceType{
	private ResourceType value;
	public ValueResultResourceType() { }
	public ValueResultResourceType(ResourceType value){
		this.value = value;
	}
	public ResourceType getValue() {
		return value;
	}
	public void setValue(ResourceType value) {
		this.value = value;
	}
}
