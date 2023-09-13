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
         this.bindClassMethods(['clientLoaded', 'mount', 'addRecipeToPage'], this);
         this.dataStore = new DataStore();
         this.dataStore.addChangeListener(this.addRecipeToPage); // <- do I need dataStore?
         this.header = new Header(this.dataStore);
         console.log("viewrecipes constructor");
     }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
//        const urlParams = new URLSearchParams(window.location.search);
//        const pantryId = urlParams.get('id');
//        document.getElementById('pantry-name').innerText = "Loading Pantry ...";
//        const pantry = await this.client.getPantry(pantryId);
//        this.dataStore.set('pantry', pantry);
//        document.getElementById('ingredients').innerText = "(loading inventory...)";
//        const ingredients = await this.client.getPantryIngredients(pantryId);
//        this.dataStore.set('ingredients', ingredients);
//        const userName = await this.client.getUserName();
//        document.getElementById('user-id').innerText = "Created By: " + userName;
    }

/**
     * When the recipe is updated in the datastore, update the recipe metadata on the page.
     */
    addRecipeToPage() {
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
     * Add the header to the page and load the DigiPantryClient.
     */
    mount() {
//        document.getElementById('create').addEventListener('click', this.addSong);

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


