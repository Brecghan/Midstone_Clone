import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreatePantry extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewInventory'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewInventory);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new DigiPantryClient();
    }

    /**
     * Method to run when the create playlist submit button is pressed. Call the MusicPlaylistService to create the
     * playlist.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const pantryName = document.getElementById('pantry-name').value;

/*

        let tags;
        if (tagsText.length < 1) {
            tags = null;
        } else {
            tags = tagsText.split(/\s*,\s*//*
);
        }
*/

        const pantry = await this.client.createPantry(pantryName, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('pantry', pantry);
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewInventory() {
        const pantry = this.dataStore.get('pantry');
        if (pantry != null) {
            window.location.href = `/digiPantryInventory.html?id=${pantry.pantryId}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createPantry = new CreatePantry();
    createPantry.mount();
};

window.addEventListener('DOMContentLoaded', main);