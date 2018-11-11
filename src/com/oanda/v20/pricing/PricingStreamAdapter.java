package com.oanda.v20.pricing;

import com.google.gson.*;

import java.lang.reflect.Type;

// GSON Deserializer for StreamingData implementors
public class PricingStreamAdapter implements JsonDeserializer<StreamingData> {
    @Override
    public StreamingData deserialize(JsonElement json, Type arg1,
                                     JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get("type");
        String typestring = prim.getAsString();
        switch (typestring) {
            case "PRICE":
                return context.deserialize(json, ClientPrice.class);
            case "HEARTBEAT":
                return context.deserialize(json, PricingHeartbeat.class);
            default:
                return null;
        }
    }
}
