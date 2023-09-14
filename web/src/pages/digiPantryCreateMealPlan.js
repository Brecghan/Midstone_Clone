import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreateMealPlan extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewMealPlan'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewMealPlan);
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

        const mealPlanName = document.getElementById('new-meal-plan-name').value;
        console.log(mealPlanName);


        const mealPlan = await this.client.createMealPLan(mealPlanName, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });

        this.dataStore.set('mealPlan', mealPlan);


    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewMealPlan() {
        const mealPlan = this.dataStore.get('mealPlan');

        if (mealPlan != null) {
            window.location.href = `/digiPantryMealPlan.html?id=${mealPlan.mealPlanId}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createMealPlan = new CreateMealPlan();
    createMealPlan.mount();
};

window.addEventListener('DOMContentLoaded', main);