package com.projeto.auth.api.auth.jpaconverters;

import java.io.IOException;

import javax.persistence.AttributeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

public class JsonConverter<T> implements AttributeConverter<T, String> {

	protected static final Logger LOG = LoggerFactory.getLogger(JsonConverter.class);

	private final ObjectWriter writer;

	private final ObjectReader reader;

	public JsonConverter() {

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.ALWAYS);

		mapper.setVisibility(mapper.getSerializationConfig().getDefaultVisibilityChecker()
				.withFieldVisibility(JsonAutoDetect.Visibility.ANY).withGetterVisibility(JsonAutoDetect.Visibility.NONE)
				.withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
		reader = mapper.reader().forType(JsonTypeLike.class);
		writer = mapper.writer().forType(JsonTypeLike.class);
	}

	@Override
	public String convertToDatabaseColumn(T attribute) {
		try {
			if (attribute != null) {
				JsonTypeLike<T> wrapper = new JsonTypeLike<T>(attribute, writer);
				String value = writer.writeValueAsString(wrapper);
				return value;
			} else {
				return null;
			}
		} catch (JsonProcessingException e) {
			LOG.error("Failed to serialize as object into JSON: {}", attribute, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public T convertToEntityAttribute(String dbData) {
		try {
			if (dbData != null) {
				JsonTypeLike<T> wrapper = reader.readValue(dbData);
				T obj = wrapper.readValue(reader);
				return obj;
			} else {
				return null;
			}
		} catch (IOException e) {
			LOG.error("Failed to deserialize as object from JSON: {}", dbData, e);
			throw new RuntimeException(e);
		}
	}

	public static class JsonTypeLike<Y> {

		private String entityType;

		private String entityValue;

		public JsonTypeLike() {
		}

		@SuppressWarnings("unchecked")
		public JsonTypeLike(Y obj, ObjectWriter writer) {
			Class<Y> classType = ((Class<Y>) obj.getClass());
			this.entityType = obj.getClass().getName();

			try {
				this.entityValue = writer.forType(classType).writeValueAsString(obj);
			} catch (JsonProcessingException e) {
				LOG.error("Failed serializing object to JSON: {}", obj, e);
			}
		}

		public Y readValue(ObjectReader reader) {
			try {
				Class<?> clazz = Class.forName(this.entityType);
				Y obj = reader.forType(clazz).readValue(this.entityValue);
				return obj;
			} catch (ClassNotFoundException | IOException e) {
				LOG.error("Failed deserializing object from JSON: {}", this.entityValue, e);
				return null;
			}
		}

		public String getEntityType() {
			return entityType;
		}

		public void setEntityType(String type) {
			this.entityType = type;
		}

		public String getEntityValue() {
			return entityValue;
		}

		public void setValue(String value) {
			this.entityValue = value;
		}
	}
}