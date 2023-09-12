import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class DigiPantryViewPantries extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'addPantriesToPage', 'redirectToViewInventory', 'submit'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addPantriesToPage);
        this.dataStore.addChangeListener(this.redirectToViewInventory);
        this.header = new Header(this.dataStore);
        console.log("viewPantries constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        document.getElementById('pantriesSelect').innerText = "(Loading Pantries...)";
        const pantries = await this.client.getPantryList();
        console.log("pantries should be here" + pantries);
        this.dataStore.set('pantries', pantries);
        console.log("pantries have been set")
        const userName = await this.client.getUserName();
        document.getElementById('user-name').innerText = userName + "'s Pantries";
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('pantriesSelect').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new DigiPantryClient();
        this.clientLoaded();
    }


    /**
     * When the playlist is updated in the datastore, update the playlist metadata on the page.
     */
    addPantriesToPage() {
        const pantries = this.dataStore.get('pantries');
        console.log("are the pantries here? " + pantries);
        if (pantries == null) {
            return;
        }

        document.getElementById("pantriesSelect").size = pantries.length;
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

        pantries.forEach(pantries =>
          optionList.add(
            new Option(pantries.pantryName, pantries.pantryId)
          ));
        }

    redirectToViewInventory() {
        const pantryId = this.dataStore.get('pantryId');
        console.log("here is the supposed pantryId" + pantryId)
        if (pantryId != null) {
            window.location.href = `/digiPantryInventory.html?id=${pantryId}`;
        }
    }

    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const pantryId = document.getElementById('pantriesSelect').value;
        console.log("selected pantry's id: " + pantryId);

        this.dataStore.set('pantryId', pantryId);


    }


    /**
     * When the songs are updated in the datastore, update the list of songs on the page.
     */
//    addIngredientsToPage() {
//        const ingredients = this.dataStore.get('ingredients')
//
//        if (ingredients == null) {
//            return;
//        }
//
//        let ingredientHtml = '';
//        let ingredient;
//        for (ingredient of ingredients) {
//            ingredientHtml += `
//                <li class="ingredient">
//                    <span class="ingredientName">${ingredient.ingredientName}</span>
//                    <span class="unitOfMeasure">${ingredient.unitOfMeasure}</span>
//                </li>
//            `;
//        }
//        document.getElementById('ingredients').innerHTML = itemHtml;
//    }

    /**
     * Method to run when the add song playlist submit button is pressed. Call the MusicPlaylistService to add a song to the
     * playlist.
     */
//    async changePantryName() {
//
//        const errorMessageDisplay = document.getElementById('error-message');
//        errorMessageDisplay.innerText = ``;
//        errorMessageDisplay.classList.add('hidden');
//
//        const pantry = this.dataStore.get('pantry');
//        if (playlist == null) {
//            return;
//        }
//
//        document.getElementById('add-ingredient').innerText = 'Adding...';
//        const ingredientNumber = document.getElementById('ingredient-number').value;
//        const pantryId = pantry.pantryId;
//
//        const ingredientList = await this.client.addItemToPantry(pantryId, ingredientNumber, (error) => {
//            errorMessageDisplay.innerText = `Error: ${error.message}`;
//            errorMessageDisplay.classList.remove('hidden');
//        });
//
//        this.dataStore.set('ingredients', ingredientList);
//
//        document.getElementById('add-ingredient').innerText = 'Add Ingredient';
//        document.getElementById("add-ingredient-form").reset();
//    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewPantry = new DigiPantryViewPantries();
    viewPantry.mount();
};

window.addEventListener('DOMContentLoaded', main);
