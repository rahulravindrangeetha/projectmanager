package com.projectmanagerapp.util;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate>
{
		@Override
		public LocalDate deserialize(JsonParser parser, DeserializationContext arg1)
				throws IOException, JsonProcessingException 
		{
			// TODO Auto-generated method stub
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		      LocalDate localDate = null;
		      localDate = LocalDate.parse(parser.getText(), formatter);
		      return localDate;
		}
		}


