package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateMealPlanRecipeSetRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateMealPlanRecipeSetResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateMealPlanRecipeSetLambda
        extends LambdaActivityRunner<UpdateMealPlanRecipeSetRequest, UpdateMealPlanRecipeSetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateMealPlanRecipeSetRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(
            AuthenticatedLambdaRequest<UpdateMealPlanRecipeSetRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateMealPlanRecipeSetRequest unauthenticatedRequest = input.fromBody(
                        UpdateMealPlanRecipeSetRequest.class);
                return input.fromUserClaims(claims ->
                        UpdateMealPlanRecipeSetRequest.builder()
                                .withMealPlanId(unauthenticatedRequest.getMealPlanId())
                                .withRecipeId(unauthenticatedRequest.getRecipeId())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateMealPlanRecipeSetActivity().handleRequest(request)
        );
    }
}

