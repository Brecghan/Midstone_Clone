package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.PantryModel;

public class UpdatePantryResult {
    private final PantryModel pantry;

    private UpdatePantryResult(PantryModel pantry) {
        this.pantry = pantry;
    }

    public PantryModel getPantry() {
        return pantry;
    }

    @Override
    public String toString() {
        return "UpdatePantryResult{" +
                "pantry=" + pantry +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PantryModel pantry;

        public Builder withPantry(PantryModel pantry) {
            this.pantry = pantry;
            return this;
        }

        public UpdatePantryResult build() {
            return new UpdatePantryResult(pantry);
        }
    }
}
