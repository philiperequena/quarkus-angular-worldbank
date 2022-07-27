package com.philiperequena.domain.dto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@Schema(name="WorldBankIndicator", description="Represents content count on header and list of contry indicator data by year.")
public class WorldBankIndicator {
    class Content {
        @Schema(description="Year of indicator.", example = "2022")
        public Integer year;
        @Schema(description="Value description.", example = "Poverty headcount ratio at $1.90 a day (2011 PPP) (% of population)")
        public String value;
    }

    public WorldBankHeader header;
    public List<Content> data = new ArrayList<Content>();

    public WorldBankIndicator fromJson(String str) {

        Gson g = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        JsonArray result = g.fromJson(str, JsonArray.class);
        this.header = g.fromJson(result.get(0).getAsJsonObject(), WorldBankHeader.class);
        JsonArray content = result.get(1).getAsJsonArray();
        g = new GsonBuilder()
                .create();
        Iterator<JsonElement> it = content.iterator();
        while (it.hasNext()) {
            Content c = new Content();
            JsonObject next = it.next().getAsJsonObject();
            c.value = next.get("indicator").getAsJsonObject().get("value").getAsString();
            c.year = next.get("date").getAsInt();
            this.data.add(c);
        }
        return this;
    }
}
