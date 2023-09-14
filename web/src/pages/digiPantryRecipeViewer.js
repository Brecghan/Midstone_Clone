import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";


/**
 * Logic needed for the view recipes page of the website.
 */
class DigiPantryRecipeViewer extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addRecipeToPage', 'addIngredientsToPage', 'addMealPlansToPage',
             'addRecipeToMealPlan', 'letsGoShopping', 'addPantriesToPage'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addRecipeToPage);
        this.dataStore.addChangeListener(this.addMealPlansToPage);
        this.dataStore.addChangeListener(this.addPantriesToPage);
        this.dataStore.addChangeListener(this.addIngredientsToPage);
        this.header = new Header(this.dataStore);
        console.log("view recipes constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const recipeId = urlParams.get('id');
        document.getElementById('recipe-name').innerText = "Loading Recipe ...";
        const recipe = await this.client.getRecipe(recipeId);
        this.dataStore.set('recipe', recipe);
        const pantries = await this.client.getPantryList();
        this.dataStore.set('pantries', pantries);
        const ingredients = recipe.neededIngredients;
        this.dataStore.set('ingredients', ingredients);
        const userName = await this.client.getUserName();
        const mealPlans = await this.client.getMealPlanList();
        this.dataStore.set('mealPlans', mealPlans);
    }

/**
     * When the recipe is updated in the datastore, update the recipe metadata on the page.
     */
    addRecipeToPage() {
        const recipe = this.dataStore.get('recipe');
        if (recipe == null) {
            return;
        }

        document.getElementById('recipe-name').innerText = recipe.recipeName;
        document.getElementById('recipe-description').innerText = recipe.recipeDescription
    }

    addIngredientsToPage() {
        const ingredients = this.dataStore.get('ingredients');
        if (ingredients == null) {
            return;
        }
        let ingredientHtml = '';
        let ingredient;
        for (ingredient of ingredients) {
            ingredientHtml += `
                <li class="ingredient">
                    <span class="ingredientName">${ingredient.ingredientName}</span>
                    <span class="ingredientQuantity">${ingredient.quantity}</span>
                    <span class="unitOfMeasure">${ingredient.unitOfMeasure}</span>
                </li>
            `;
        }

        document.getElementById('ingredients').innerHTML = ingredientHtml;
    }

   addMealPlansToPage() {
       const mealPlans = this.dataStore.get('mealPlans');
       if (mealPlans == null) {
           return;
       }

       document.getElementById("mealPlanSelect").size = mealPlans.length;
       let optionList = document.getElementById('mealPlanSelect').options;
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

       mealPlans.forEach(mealPlans =>
         optionList.add(
           new Option(mealPlans.mealPlanName, mealPlans.mealPlanId)
         ));
   }

    addPantriesToPage() {
         const pantries = this.dataStore.get('pantries');
         if (pantries == null) {
             return;
         }

         document.getElementById("pantrySelect").size = pantries.length;
         let optionList = document.getElementById('pantrySelect').options;
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

         pantries.forEach(pantries =>
           optionList.add(
             new Option(pantries.pantryName, pantries.pantryId)
           ));
         }

    async addRecipeToMealPlan() {
         const mealPlanId = document.getElementById('mealPlanSelect').value;
         const urlParams = new URLSearchParams(window.location.search);
         const recipeId = urlParams.get('id');
         const results = await this.client.addRecipeToMealPlan(mealPlanId, recipeId, (error) => {
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                });
    }

    async letsGoShopping() {
         const pantryId = document.getElementById('pantrySelect').value;
         const urlParams = new URLSearchParams(window.location.search);
         const recipeId = urlParams.get('id');
         const ingredients = await this.client.compareIngredients(pantryId, recipeId, (error) => {
                    errorMessageDisplay.innerText = `Error: ${error.message}`;
                    errorMessageDisplay.classList.remove('hidden');
                });
         if (ingredients == null) {
            return;
         } else if (ingredients.length == 0) {
            document.getElementById('all-good-here').innerText = "You Have Everything You Need!";
         } else {
         let ingredientHtml = '';
         let ingredient;
         for (ingredient of ingredients) {
             ingredientHtml += `
                <li class="ingredient">
                    <span class="ingredientName">${ingredient.ingredientName}</span>
                    <span class="ingredientQuantity">${ingredient.quantity}</span>
                    <span class="unitOfMeasure">${ingredient.unitOfMeasure}</span>
                </li>
            `;
         }
         document.getElementById('shoppingList').innerHTML = ingredientHtml;
         }
    }

/**
     * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
        document.getElementById('mealPlanSelect').addEventListener('click', this.addRecipeToMealPlan);
        document.getElementById('pantrySelect').addEventListener('click', this.letsGoShopping);

        this.header.addHeaderToPage();

        this.client = new DigiPantryClient();
        this.clientLoaded();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewRecipes = new DigiPantryRecipeViewer();
    viewRecipes.mount();
};

window.addEventListener('DOMContentLoaded', main);


