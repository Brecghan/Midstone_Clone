import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class DigiPantryClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getPantry', 'getPantryIngredients',
            'createPantry', 'getPantryList', 'getUserName', 'getRecipes', 'addIngredientToPantry', 'changePantryName',
            'getMealPlanList', 'createMealPlan', 'getRecipe', 'changeMealPlanName', 'getMealPlanRecipeSet', 'compareIngredients'];

        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getUserName(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            const userName = await this.authenticator.getCurrentUserName();
            console.log(userName);
            return userName;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the playlist for the given ID.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The playlist's metadata.
     */
    async getPantry(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get pantries.");
            const response = await this.axiosClient.get(`digitalPantry/${id}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.pantry;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getRecipes(region) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get recipes.");
            const response = await this.axiosClient.get(`recipes/${region}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.recipes;
        } catch (error) {
            this.handleError(error)
        }
    }

    async getMealPlanRecipeSet(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get meal plans.");
            const response = await this.axiosClient.get(`mealPlan/${id}/recipeSet`, {
                  headers: {
                      Authorization: `Bearer ${token}`
                  }
            });
            return response.data.recipeSet;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }
  
    async getRecipe(recipeId) {
        try{
            const token = await this.getTokenOrThrow("Only authenticated users can get pantries.");
            const response = await this.axiosClient.get(`recipe/${recipeId}`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.recipe;
        } catch (error) {
            this.handleError(error)
        }
    }

        /**
         * Gets the playlist for the given ID.
         * @param id Unique identifier for a playlist
         * @param errorCallback (Optional) A function to execute if the call fails.
         * @returns The playlist's metadata.
         */
        async getPantryList() {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can get pantries.");
                console.log("token loaded");
                const response = await this.axiosClient.get(`digitalPantry`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                return response.data.pantries;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }

        async getMealPlanList() {
            try {
                const token = await this.getTokenOrThrow("Only authenticated users can get meal plans.");
                console.log("token loaded");
                const response = await this.axiosClient.get(`mealPlan`, {
                      headers: {
                          Authorization: `Bearer ${token}`
                      }
                  });
                return response.data.mealPlans;
            } catch (error) {
                this.handleError(error, errorCallback)
            }
        }
    /**
     * Get the songs on a given playlist by the playlist's identifier.
     * @param id Unique identifier for a playlist
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The list of songs on a playlist.
     */
    async getPantryIngredients(id, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can get pantry inventories.");
            const response = await this.axiosClient.get(`digitalPantry/${id}/inventory`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data.inventory;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Create a new pantry owned by the current user.
     * @param name The name of the pantry to create.
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The pantry that has been created.
     */
    async createPantry(pantryName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create pantries.");
            const response = await this.axiosClient.post(`digitalPantry`, {
                pantryName: pantryName
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            return response.data.pantry;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async createMealPlan(mealPlanName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can create meal plans.");
            const response = await this.axiosClient.post(`mealPlan`, {
                mealPlanName: mealPlanName
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            return response.data.mealPlan;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async changePantryName(pantryId, newPantryName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can change pantry names.");
            const response = await this.axiosClient.put(`digitalPantry/${pantryId}`, {
                pantryName: newPantryName,
                pantryId: pantryId
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            return response.data.pantry;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async addRecipeToMealPlan(mealPlanId, recipeId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can change add Recipes to Meal Plans.");
            const response = await this.axiosClient.put(`mealPlan/${mealPlanId}/recipeSet`, {
                mealPlanId: mealPlanId,
                recipeId: recipeId
              }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            return response.data.mealPlan;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async compareIngredients(pantryId, recipeId, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can do stuff.");
            console.log('AM I EVEN GETTING HERE?')
            const response = await this.axiosClient.get(`compare/${pantryId}/${recipeId}`, {
            headers: {
                Authorization: `Bearer ${token}`
            }
            });
            return response.data.missingIngredients;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async changeMealPlanName(mealPlanId, newMealPlanName, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can change Meal Plan names.");
            const response = await this.axiosClient.put(`mealPlan/${mealPlanId}`, {
                mealPlanName: newMealPlanName,
                mealPlanId: mealPlanId
              }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            return response.data.mealPlan;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Add a song to a playlist.
     * @param id The id of the playlist to add a song to.
     * @param asin The asin that uniquely identifies the album.
     * @param trackNumber The track number of the song on the album.
     * @returns The list of songs on a playlist.
     */
    async addIngredientToPantry(pantryId, ingredientName, ingredientQuantity, unitOfMeasure, errorCallback) {
        try {
            const token = await this.getTokenOrThrow("Only authenticated users can add an ingredient to a pantry");
            console.log(unitOfMeasure);
            console.log(ingredientQuantity);
            const response = await this.axiosClient.put(`digitalPantry/${pantryId}/inventory`, {
                pantryId: pantryId,
                ingredientName: ingredientName,
                ingredientQuantity: ingredientQuantity,
                unitOfMeasure: unitOfMeasure
            }, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            console.log(response);
            return response.data.inventory;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for a soong.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The playlists that match the search criteria.
     */
    async search(criteria, errorCallback) {
        try {
            const queryParams = new URLSearchParams({ q: criteria })
            const queryString = queryParams.toString();

            const response = await this.axiosClient.get(`digitalPantry/search?${queryString}`);

            return response.data.digitalPantry;
        } catch (error) {
            this.handleError(error, errorCallback)
        }

    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
        console.error(error);

        const errorFromApi = error?.response?.data?.error_message;
        if (errorFromApi) {
            console.error(errorFromApi)
            error.message = errorFromApi;
        }

        if (errorCallback) {
            errorCallback(error);
        }
    }
}