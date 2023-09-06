package com.nashss.se.musicplaylistservice.activity.requests;

public class GetPantryInventoryRequest {
    private final String userId;
    private final String pantryId;

    private GetPantryInventoryRequest(String userId, String pantryId) {
        this.userId = userId;
        this.pantryId = pantryId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPantryId() {
        return pantryId;
    }

    @Override
    public String toString() {
        return "GetPlaylistSongsRequest{" +
                "userId='" + userId + '\'' +
                ", pantryId='" + pantryId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String userId;
        private String pantryId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder withPantryId(String pantryId) {
            this.pantryId = pantryId;
            return this;
        }

        public GetPantryInventoryRequest build() {
            return new GetPantryInventoryRequest(userId, pantryId);
        }
    }
}
