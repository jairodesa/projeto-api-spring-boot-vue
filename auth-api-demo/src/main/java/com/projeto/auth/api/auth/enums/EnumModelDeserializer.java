package com.projeto.auth.api.auth.enums;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

@SuppressWarnings({"rawtypes","unchecked"})
public class EnumModelDeserializer extends JsonDeserializer<EnumModel> {

	@Override
	public EnumModel deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		JsonNode node = jp.getCodec().readTree(jp);
		return findEnum(node.get("type").asText(), node.get("codigo").asText());
	}



	private EnumModel findEnum(String type, String codigo) {
		try {
			Class clazz = Class.forName(type);
			return EnumModelHelper.get().obterInstancia(clazz.getEnumConstants(), codigo);
		} catch (ClassNotFoundException e) {
			return null;
		}
	}

}
