package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.GetMealPlanRecipeSetRequest;
import com.nashss.se.musicplaylistservice.activity.requests.GetPantryRequest;
import com.nashss.se.musicplaylistservice.activity.results.GetMealPlanRecipeSetResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetMealPlanRecipeSetLambda
        extends LambdaActivityRunner<GetMealPlanRecipeSetRequest, GetMealPlanRecipeSetResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetMealPlanRecipeSetRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMealPlanRecipeSetRequest> input,
                                        Context context) {
        return super.runActivity(
            () -> {
                GetMealPlanRecipeSetRequest unauthenticatedRequest = input.fromPath(path ->
                        GetMealPlanRecipeSetRequest.builder()
                                .withMealPlanId(path.get("mealPlanId"))
                                .build());
                return input.fromUserClaims(claims ->
                        GetMealPlanRecipeSetRequest.builder()
                                .withMealPlanId(unauthenticatedRequest.getMealPlanId())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetMealPlanRecipeSetActivity().handleRequest(request)
        );
    }
}

