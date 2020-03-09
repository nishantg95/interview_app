package com.accenture.inteview.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class ToLowerCaseDeserializer extends StdDeserializer<String> {
	 
	private static final long serialVersionUID = -3227180948138704136L;

	public ToLowerCaseDeserializer() {
		super(String.class);
	}

	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return _parseString(p, ctxt).toLowerCase();
	}
}
