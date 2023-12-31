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
        this.bindClassMethods(['clientLoaded', 'mount', 'addPantryToPage', 'addIngredientsToPage', 'addIngredient', 'changePantryName'], this);
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
        const pantryId = urlParams.get('id');
        document.getElementById('pantry-name').innerText = "Loading Pantry ...";
        const pantry = await this.client.getPantry(pantryId);
        this.dataStore.set('pantry', pantry);
        document.getElementById('ingredients').innerText = "(loading inventory...)";
        const ingredients = await this.client.getPantryIngredients(pantryId);
        this.dataStore.set('ingredients', ingredients);
        const userName = await this.client.getUserName();
        document.getElementById('user-id').innerText = "Created By: " + userName;
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('add-inventory-btn').addEventListener('click', this.addIngredient);
        document.getElementById('new-name-button').addEventListener('click', this.changePantryName);
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
        document.getElementById('pantry-name').innerText = pantry.pantryName;
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
                    <span class="ingredientQuantity">${ingredient.quantity}</span>
                    <span class="unitOfMeasure">${ingredient.unitOfMeasure}</span>
                </li>
            `;
        }
        document.getElementById('ingredients').innerHTML = ingredientHtml;
    }

    async changePantryName() {

        const pantry = this.dataStore.get('pantry');
        if (pantry == null) {
            return;
        }

        const pantryId = pantry.pantryId;
        const newPantryName = document.getElementById('new-pantry-name').value;

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');


        const ingredientList = await this.client.changePantryName(pantryId, newPantryName, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        document.getElementById('new-name-button').innerText = 'Submit';
        document.getElementById("pantryName-field-form").reset();
        location.reload();



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
        if (pantry == null) {
            return;
        }

        document.getElementById('add-inventory-btn').innerText = 'Adding...';
        const ingredientName = document.getElementById('iname').value;
        const ingredientMeasurement = document.getElementById('measurements').value;
        const ingredientNumber = document.getElementById('inum').value;
        const pantryId = pantry.pantryId;

        const ingredientList = await this.client.addIngredientToPantry(pantryId, ingredientName, ingredientNumber, ingredientMeasurement, (error) => {
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('ingredients', ingredientList);

        document.getElementById('add-inventory-btn').innerText = 'Add Ingredient';
        document.getElementById("inventory-fields-form").reset();
        location.reload();
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
