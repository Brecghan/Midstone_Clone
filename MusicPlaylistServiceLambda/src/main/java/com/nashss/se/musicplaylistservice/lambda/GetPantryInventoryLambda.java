package com.nashss.se.musicplaylistservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.CreatePantryRequest;
import com.nashss.se.musicplaylistservice.activity.requests.GetPantryInventoryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetPlaylistSongsResult;

public class GetPantryInventoryLambda
        extends LambdaActivityRunner<GetPantryInventoryRequest, GetPlaylistSongsResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPantryInventoryRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPantryInventoryRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    GetPantryInventoryRequest unauthenticatedRequest = input.fromBody(GetPantryInventoryRequest.class);
                    return input.fromUserClaims(claims ->
                            GetPantryInventoryRequest.builder()
                                    .withPantryId(unauthenticatedRequest.getPantryId())
                                    .withUserId(claims.get("email"))
                                    .build());
                },
            (request, serviceComponent) ->
                    serviceComponent.provideGetPantryInventoryActivity().handleRequest(request)
        );
    }
}

