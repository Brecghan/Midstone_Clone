import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view recipes page of the website.
 */
 class DigiPantryMealPlanDetails extends BindingClass {
     constructor() {
         super();
         this.bindClassMethods(['clientLoaded', 'mount', 'addRecipesToPage', 'redirectToRecipeViewer', 'changeMealPlanName',
         'addMealPlanToPage'], this);
         this.dataStore = new DataStore();
         this.dataStore.addChangeListener(this.addRecipesToPage);
         this.header = new Header(this.dataStore);
         console.log("viewRecipes constructor");
     }


///**
//     * Once the client is loaded, get the recipe metadata and song list.
//     */
    async clientLoaded() {
        const mealPlans = await this.client.getMealPlanList();
        console.log("mealPlans should be here" + mealPlans);
        const userName = await this.client.getUserName();
        document.getElementById('user-id').innerText = "Created By: " + userName;
        const urlParams = new URLSearchParams(window.location.search);
        const mealPlanId = urlParams.get('id');
        console.log(mealPlanId)
        for (const mealPlan of mealPlans){
            if (mealPlan.mealPlanId == mealPlanId){
                this.dataStore.set('mealPlan', mealPlan);
            }
        }
        this.addMealPlanToPage(this.dataStore.get('mealPlan'));
        document.getElementById('recipesSelect').innerText = "Loading Recipe ...";
        const recipes = await this.client.getMealPlanRecipeSet(mealPlanId);
        console.log(recipes);
        this.dataStore.set('recipes', recipes);
}

/**
 * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
        document.getElementById('recipesSelect').addEventListener('click', this.redirectToRecipeViewer);
        document.getElementById('new-name-button').addEventListener('click', this.changeMealPlanName);

        this.header.addHeaderToPage();

        this.client = new DigiPantryClient();
        this.clientLoaded();
    }
/**
     * When the recipe is updated in the datastore, update the recipe metadata on the page.
     */
    addRecipesToPage() {
       const recipes = this.dataStore.get('recipes');
       console.log("are the recipes here? " + recipes);
       if (recipes == null) {
           return;
       }

       document.getElementById("recipesSelect").size = recipes.length;
       let optionRecipeList = document.getElementById('recipesSelect').options;
       optionRecipeList.length = 0;
       let options = [
         {
           text: 'Option 1',
           value: 'Value 1'
         },
         {
           text: 'Option 2',
           value: 'Value 2',
           selected: true
         },
         {
           text: 'Option 3',
           value: 'Value 3'
         }
       ];
       recipes.forEach(recipes =>
         optionRecipeList.add(
           new Option(recipes.recipeName, recipes.recipeId)
         ));
}



/**
     * When the recipe is updated in the datastore, redirect to the view recipe page.
     */
    redirectToRecipeViewer() {
        const recipeId = document.getElementById('recipesSelect').value;

        if (recipeId != null) {
            window.location.href = `/digiPantryRecipeViewer.html?id=${recipeId}`;
        }
    }



    async changeMealPlanName() {
        const mealPlan = this.dataStore.get('mealPlan');
        if (mealPlan == null) {
            return;
        }
        const mealPlanId = mealPlan.mealPlanId;
        const newMealPlanName = document.getElementById('new-meal-plan-name').value;
        const ingredientList = await this.client.changeMealPlanName(mealPlanId, newMealPlanName, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        document.getElementById('new-name-button').innerText = 'Submit';
        document.getElementById("mealPlan-field-form").reset();
        location.reload();
    }



    addMealPlanToPage() {
        const mealPlan = this.dataStore.get('mealPlan');
        if (mealPlan == null) {
            return;
        }
        console.log("LOOK HERE");
        console.log(mealPlan.mealPlanName);
        document.getElementById('meal-plan-name').innerText = mealPlan.mealPlanName;
    }



async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

//        const region = document.getElementById('regionsSelect').value;
//        console.log("selected region: " + region);
        const recipeId = document.getElementById('recipesSelect').value;
        console.log("selected recipeID: " + recipeId);

//        if (recipeId != null) {
            this.dataStore.set('recipeId', recipeId);
//        } else {
//         this.dataStore.set('region', region);
//        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewRecipes = new DigiPantryMealPlanDetails();
    viewRecipes.mount();
};

window.addEventListener('DOMContentLoaded', main);









