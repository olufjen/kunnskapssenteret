package no.helsebiblioteket.admin.domain.requestresult;

import java.io.Serializable;

import no.helsebiblioteket.admin.domain.ResourceType;

@SuppressWarnings("serial")
public class ValueResultResourceType extends SingleResultResourceType implements Serializable {
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
