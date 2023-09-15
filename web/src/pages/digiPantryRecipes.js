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
            'updateRecipeView'], this);
         this.dataStore = new DataStore();
         this.dataStore.addChangeListener(this.addRecipesToPage);
//         this.dataStore.addChangeListener(this.redirectToRecipeViewer);
//         this.dataStore.addChangeListener(this.selectRecipeRegion);
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
        const recipesAll = await this.client.getRecipes("ALL");
//        const regions = await this.getRecipeRegions(recipes);
//        this.dataStore.set('regions', regions);
//        for (const region of regions) {
//        console.log(region);
//        }
//        console.log("Show me the regions" + regions)
        this.dataStore.set('recipesAll', recipesAll);
        this.dataStore.set('recipes', recipesAll);
}
//    async getRecipeRegions(recipes) {
//                const regions = new Set();
//                for (const recipe of recipes){
//                regions.add(recipe.region);
//                }
//                return regions;
//        }

/**
 * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
        document.getElementById('filter-btn').addEventListener('click', this.updateRecipeView);
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
//
//    selectRecipeRegion() {
//           const regions = this.dataStore.get('regions');
//           console.log("Regions in selectRecipeRegion" + regions);
//           if (regions == null) {
//               return;
//           }
//           let optionRegionList = document.getElementById('regionsSelect').options;
//           optionRegionList.length = 0;
//           var options = ["ALL"];
//           for (const region of regions) {
//           options.push(region);
//           };
//           for(var i = 0; i < options.length; i++) {
//               var opt = options[i];
//               var el = document.createElement("option");
//               el.textContent = opt;
//               el.value = opt;
//               optionRegionList.add(el);
//           }
//        }

   async updateRecipeView() {
           const region = document.getElementById('regionsSelect').value;
           console.log("Region in updateReviewView: " + region);
           const recipesAll= this.dataStore.get('recipesAll');
           const recipes = new Set();
           for (const recipe of recipesAll) {
            if (recipe.region == region) {
               recipes.add(recipe);
            }
           }
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









