package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.PantryModel;

import java.util.ArrayList;
import java.util.List;

public class GetPantryListResult {
    private final List<PantryModel> pantries;

    private GetPantryListResult(List<PantryModel> pantries) {
        this.pantries = pantries;
    }

    public List<PantryModel> getPantries() {
        return pantries;
    }

    @Override
    public String toString() {
        return "GetPantryListResult{" +
                "pantries=" + pantries +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<PantryModel> pantries ;

        public Builder withPantries(List<PantryModel> pantries) {
            this.pantries = new ArrayList<>(pantries);
            return this;
        }

        public GetPantryListResult build() {
            return new GetPantryListResult(pantries);
        }
    }
}
