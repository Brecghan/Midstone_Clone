package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.PantryModel;

public class GetPantryResult {
    private final PantryModel pantry;

    private GetPantryResult(PantryModel pantry) {
        this.pantry = pantry;
    }

    public PantryModel getPantry() {
        return pantry;
    }

    @Override
    public String toString() {
        return "GetPantryResult{" +
                "pantry=" + pantry +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PantryModel pantry ;

        public Builder withPantry(PantryModel pantry) {
            this.pantry = pantry;
            return this;
        }

        public GetPantryResult build() {
            return new GetPantryResult(pantry);
        }
    }
}
