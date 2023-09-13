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
         this.bindClassMethods(['clientLoaded', 'mount', 'addRecipesToPage', 'redirectToRecipeViewer', 'selectRecipeRegion', 'updateRecipeView'], this);
         this.dataStore = new DataStore();
         this.dataStore.addChangeListener(this.addRecipesToPage);
         this.dataStore.addChangeListener(this.redirectToRecipeViewer);
         this.dataStore.addChangeListener(this.selectRecipeRegion);
         this.dataStore.addChangeListener(this.updateRecipeView);
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
        const recipes = await this.client.getRecipes(null);
        this.dataStore.set('recipes', recipes);
        document.getElementById('region').innerText = "(loading region...)";
//        const songs = await this.client.getPlaylistSongs(recipeId);
//        this.dataStore.set('songs', songs);

//    }

/**
 * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
        document.getElementById('recipeRegions').addEventListener('click', this.submit);
        document.getElementById('recipesSelect').addEventListener('click', this.submit);

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
               let optionList = document.getElementById('pantriesSelect').options;
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
                   console.log("are the regions here? " + regions);
                   if (regions == null) {
                       return;
                   }

                   document.getElementById("regionsSelect").size = regions.length;
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

                   regions.forEach(region =>
                     optionList.add(
                       new Option(region, region)
                     ));
        }

   updateRecipeView() {
           const region = this.dataStore.get('region');
                   console.log("are the regions here? " + regions);
           const recipes = await this.client.getRecipes(region);
           this.dataStore.set('recipes', recipes);

   }


/**
     * When the recipe is updated in the datastore, redirect to the view recipe page.
     */
    redirectToRecipeViewer() {
        const recipeId = this.dataStore.get('recipeId');

        if (recipeId != null) {
            window.location.href = `/digiPantryRecipeViewer.html?id=${recipeId}`;
        }
    }



async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const region = document.getElementById('regionsSelect').value;
        console.log("selected region: " + region);
        const recipeId = document.getElementById('recipesSelect').value;
        console.log("selected recipe: " + recipeId);

        if (recipeId != null) {
            this.dataStore.set('recipeId', recipeId);
        } else {
         this.dataStore.set('region', region);
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









