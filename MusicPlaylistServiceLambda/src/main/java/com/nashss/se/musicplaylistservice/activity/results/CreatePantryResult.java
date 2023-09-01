package com.nashss.se.musicplaylistservice.activity.results;

import com.nashss.se.musicplaylistservice.models.PantryModel;

public class CreatePantryResult {
    private final PantryModel pantry;

    private CreatePantryResult(PantryModel pantry) {
        this.pantry = pantry;
    }

    public PantryModel getPantry() {
        return pantry;
    }

    @Override
    public String toString() {
        return "CreatePantryResult{" +
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

        public CreatePantryResult build() {
            return new CreatePantryResult(pantry);
        }
    }
}
