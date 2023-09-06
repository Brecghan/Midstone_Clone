package com.nashss.se.musicplaylistservice.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = CreatePantryRequest.Builder.class)
public class CreatePantryRequest {
    private final String pantryName;
    private final String userId;

    private CreatePantryRequest(String pantryName, String userId) {
        this.pantryName = pantryName;
        this.userId = userId;
    }

    public String getPantryName() {
        return pantryName;
    }


    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "CreatePantryRequest{" +
                "pantryName='" + pantryName + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String pantryName;
        private String userId;

        public Builder withPantryName(String pantryName) {
            this.pantryName = pantryName;
            return this;
        }

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }
        public CreatePantryRequest build() {
            return new CreatePantryRequest(pantryName, userId);
        }
    }
}
