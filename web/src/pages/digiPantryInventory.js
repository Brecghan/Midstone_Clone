import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class DigiPantryInventory extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addPantryToPage', 'addIngredientsToPage', 'addIngredient'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addPantryToPage);
        this.dataStore.addChangeListener(this.addIngredientsToPage);
        this.header = new Header(this.dataStore);
        console.log("viewPantry constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const pantryId = urlParams.get('pantryId');
        document.getElementById('pantry-name').innerText = "Loading Pantry ...";
        const pantry = await this.client.getPantry(pantryId);
        this.dataStore.set('pantry', pantry);
        document.getElementById('ingredients').innerText = "(loading inventory...)";
        const ingredients = await this.client.getPantryIngredients(pantryId);
        this.dataStore.set('ingredients', ingredients);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-inventory-btn').addEventListener('click', this.addIngredient);

        this.header.addHeaderToPage();

        this.client = new DigiPantryClient();
        this.clientLoaded();
    }


    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addPantryToPage() {
        const pantry = this.dataStore.get('pantry');
        if (pantry == null) {
            return;
        }

        document.getElementById('pantry-name').innerText = pantry.name;
        document.getElementById('user-id').innerText = pantry.userId;

        /*let tagHtml = '';
        let tag;
        for (tag of playlist.tags) {
            tagHtml += '<div class="tag">' + tag + '</div>';
        }
        document.getElementById('tags').innerHTML = tagHtml;*/
    }

    /**
     * When the songs are updated in the datastore, update the list of songs on the page.
     */
    addIngredientsToPage() {
        const ingredients = this.dataStore.get('ingredients')

        if (ingredients == null) {
            return;
        }

        let ingredientHtml = '';
        let ingredient;
        for (ingredient of ingredients) {
            ingredientHtml += `
                <li class="ingredient">
                    <span class="ingredientName">${ingredient.ingredientName}</span>
                    <span class="unitOfMeasure">${ingredient.unitOfMeasure}</span>
                </li>
            `;
        }
        document.getElementById('ingredients').innerHTML = itemHtml;
    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
     * playlist.
     */
    async addIngredient() {

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const pantry = this.dataStore.get('pantry');
        if (playlist == null) {
            return;
        }

        document.getElementById('add-ingredient').innerText = 'Adding...';
        const ingredientNumber = document.getElementById('ingredient-number').value;
        const pantryId = pantry.pantryId;

        const ingredientList = await this.client.addItemToPantry(pantryId, ingredientNumber, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('ingredients', ingredientList);

        document.getElementById('add-ingredient').innerText = 'Add Ingredient';
        document.getElementById("add-ingredient-form").reset();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewPantry = new DigiPantryInventory();
    viewPantry.mount();
};

window.addEventListener('DOMContentLoaded', main);
