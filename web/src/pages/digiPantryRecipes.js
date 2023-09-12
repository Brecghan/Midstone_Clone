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
         this.bindClassMethods(['clientLoaded', 'mount', 'addRecipesToPage', 'redirectToRecipeViewer'], this);
         this.dataStore = new DataStore();
         this.dataStore.addChangeListener(this.addRecipesToPage); // <- do I need dataStore?
         this.header = new Header(this.dataStore);
         console.log("viewrecipes constructor");
     }


///**
//     * Once the client is loaded, get the recipe metadata and song list.
//     */
//    async clientLoaded() {
//        const urlParams = new URLSearchParams(window.location.search);
//        const recipeId = urlParams.get('recipeId');
//        document.getElementById('recipe-name').innerText = "Loading Recipe ...";
//        const recipe = await this.client.getRecipe(recipeId);
//        this.dataStore.set('recipe', recipe);
//        document.getElementById('region').innerText = "(loading songs...)";
//        const songs = await this.client.getPlaylistSongs(recipeId);
//        this.dataStore.set('songs', songs);
//    }

/**
     * When the recipe is updated in the datastore, update the recipe metadata on the page.
     */
    addRecipesToPage() {
        const recipe = this.dataStore.get('recipe');
        if (recipe == null) {
            return;
        }

        document.getElementById('recipe-name').innerText = recipe.name;
        document.getElementById('recipe-owner').innerText = recipe.customerName;

//        let tagHtml = '';
//        let tag;
//        for (tag of recipe.tags) {
//            tagHtml += '<div class="tag">' + tag + '</div>';
//        }
        document.getElementById('tags').innerHTML = tagHtml;
    }


/**
     * When the recipe is updated in the datastore, redirect to the view recipe page.
     */
    redirectToRecipeViewer() {
        const recipe = this.dataStore.get('recipe');

        if (recipe != null) {
            window.location.href = `/digiPantryRecipeViewer.html?id=${recipe.recipeId}`; // <- is recipe.recipeId right?
        }
    }

/**
     * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
        document.getElementById('view-recipe-btn').addEventListener('click', this.addSong);
        document.getElementById('view-region-btn').addEventListener('click', this.addSong);


        this.header.addHeaderToPage();

        this.client = new DigiPantryClient();
        this.clientLoaded();
    }


/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewRecipes = new DigiPantryRecipes();
    viewRecipes.mount();
};

window.addEventListener('DOMContentLoaded', main);









