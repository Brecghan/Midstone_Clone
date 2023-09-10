package com.nashss.se.musicplaylistservice.lambda;

import com.nashss.se.musicplaylistservice.activity.requests.UpdateMealPlanRequest;
import com.nashss.se.musicplaylistservice.activity.results.UpdateMealPlanResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateMealPlanLambda
        extends LambdaActivityRunner<UpdateMealPlanRequest, UpdateMealPlanResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateMealPlanRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateMealPlanRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateMealPlanRequest unauthenticatedRequest = input.fromBody(UpdateMealPlanRequest.class);
                return input.fromUserClaims(claims ->
                        UpdateMealPlanRequest.builder()
                                .withMealPlanId(unauthenticatedRequest.getMealPlanId())
                                .withMealPlanName(unauthenticatedRequest.getMealPlanName())
                                .withUserId(claims.get("email"))
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideUpdateMealPlanActivity().handleRequest(request)
        );
    }
}

