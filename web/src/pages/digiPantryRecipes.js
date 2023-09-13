import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view recipes page of the website.
 */
 class DigiPantryRecipes extends BindingClass {
     constructor() {
         super();
         this.bindClassMethods(['clientLoaded', 'mount', 'addRecipesToPage', 'redirectToRecipeViewer',
            'selectRecipeRegion', 'updateRecipeView'], this);
         this.dataStore = new DataStore();
         this.dataStore.addChangeListener(this.addRecipesToPage);
//         this.dataStore.addChangeListener(this.redirectToRecipeViewer);
         this.dataStore.addChangeListener(this.selectRecipeRegion);
//         this.dataStore.addChangeListener(this.updateRecipeView);
         this.header = new Header(this.dataStore);
         console.log("viewRecipes constructor");
     }


///**
//     * Once the client is loaded, get the recipe metadata and song list.
//     */
    async clientLoaded() {
//        const urlParams = new URLSearchParams(window.location.search);
//        const recipeId = urlParams.get('recipeId');
        document.getElementById('recipesSelect').innerText = "Loading Recipe ...";
        const recipes = await this.client.getRecipes("ALL");
        const regions = await this.client.getRecipeRegions(recipes);
        this.dataStore.set('regions', regions);
        for (const region of regions) {
        console.log(region);
        }
        console.log("Show me the regions" + regions)
        this.dataStore.set('recipes', recipes);
        document.getElementById('regionsSelect').innerText = "(loading region...)";
}

/**
 * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
        document.getElementById('regionsSelect').addEventListener('click', this.updateRecipeView);
        document.getElementById('recipesSelect').addEventListener('click', this.redirectToRecipeViewer);

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
               let optionList = document.getElementById('recipesSelect').options;
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
                 optionList.add(
                   new Option(recipes.recipeName, recipes.recipeId)
                 ));
    }

    selectRecipeRegion() {
           const regions = this.dataStore.get('regions');
                   console.log("Regions in selectRecipeRegion" + regions);
                   if (regions == null) {
                       return;
                   }

//                   document.getElementById("regionsSelect").size = regions.size;
                   let optionList = document.getElementById('regionsSelect').options;
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

                   for (const region of regions) {
                   optionList.add(new Option (new String(region), new String(region)))
                   };
//                   regions.forEach(regions =>
//                     optionList.add(
//                       new Option(new String(regions), regions)
//                     ));
        }

   async updateRecipeView() {
           const region = document.getElementById('regionsSelect').value;
           console.log("Region in updateReviewView: " + region);
           const recipes = await this.client.getRecipes(region);
           this.dataStore.set('recipes', recipes);

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
    const viewRecipes = new DigiPantryRecipes();
    viewRecipes.mount();
};

window.addEventListener('DOMContentLoaded', main);









