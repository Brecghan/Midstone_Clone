package com.nashss.se.musicplaylistservice.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.musicplaylistservice.activity.requests.UpdatePantryIngredientRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdatePantryIngredientResult;

public class UpdatePantryIngredientLambda
        extends LambdaActivityRunner<UpdatePantryIngredientRequest, UpdatePantryIngredientResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePantryIngredientRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePantryIngredientRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdatePantryIngredientRequest unauthenticatedRequest = input.fromBody(UpdatePantryIngredientRequest.class);
                return input.fromUserClaims(claims ->
                        UpdatePantryIngredientRequest.builder()
                                .withPantryId(unauthenticatedRequest.getPantryId())
                                .withIngredientName(unauthenticatedRequest.getIngredientName())
                                .withUserId(claims.get("email"))
                                .withIngredientQuantity(unauthenticatedRequest.getIngredientQuantity())
                                .withUnitOfMeasure(unauthenticatedRequest.getUnitOfMeasure())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdatePantryIngredientActivity().handleRequest(request)
        );
    }
}

