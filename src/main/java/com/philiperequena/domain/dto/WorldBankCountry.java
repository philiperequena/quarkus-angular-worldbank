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

@Schema(name="WorldBankCountries", description="Represents content count on header and list of contry basic data.")
public class WorldBankCountry {
    class Content {
        @Schema(description="Country Initials.", example = "BRL")
        public String id;
        @Schema(description="Country Code.", example = "BR")
        public String iso2Code;
        @Schema(description="Country Name.", example = "Brasil")
        public String name;
    }

    public WorldBankHeader header;
    public List<Content> data = new ArrayList<Content>();

    public WorldBankCountry fromJson(String str) {

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
            this.data.add(g.fromJson(it.next(), Content.class));
        }
        return this;
    }
}
