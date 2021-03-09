package com.csc.algafood.api.model;

import java.util.List;

import com.csc.algafood.domain.model.Cookery;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cookeries")
@Data
public class CookeriesXmlWrapper {
	
	@JacksonXmlProperty(localName = "cookery")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull
	private List<Cookery> cookeries;

}
