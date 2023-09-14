import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class digiPantryMealPlan extends BindingClass {
       constructor() {
           super();
           this.bindClassMethods(['clientLoaded', 'mount', 'addMealPlansToPage', 'redirectToViewMealPlanRecipeList', 'submit'], this);
           this.dataStore = new DataStore();
           this.dataStore.addChangeListener(this.addMealPlansToPage);
           this.dataStore.addChangeListener(this.redirectToViewMealPlanRecipeList);
           this.header = new Header(this.dataStore);
           console.log("viewPantries constructor");
       }

       /**
        * Once the client is loaded, get the playlist metadata and song list.
        */
       async clientLoaded() {
           const mealPlans = await this.client.getMealPlanList();
           console.log("mealPlans should be here" + mealPlans);
           this.dataStore.set('mealPlans', mealPlans);
           console.log("mealPlans have been set")
           const userName = await this.client.getUserName();
           document.getElementById('user-name').innerText = userName + "'s Meal Plans";
       }

       /**
        * Add the header to the page and load the MusicPlaylistClient.
        */
       mount() {
           document.getElementById('mealPlanSelect').addEventListener('click', this.submit);

           this.header.addHeaderToPage();

           this.client = new DigiPantryClient();
           this.clientLoaded();
       }


       /**
        * When the playlist is updated in the datastore, update the playlist metadata on the page.
        */
       addMealPlansToPage() {
           const mealPlans = this.dataStore.get('mealPlans');
           console.log("are the mealPlans here? " + mealPlans);
           if (mealPlans == null) {
               return;
           }

           document.getElementById("mealPlanSelect").size = mealPlans.length;
           let optionList = document.getElementById('mealPlanSelect').options;
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

           mealPlans.forEach(mealPlans =>
             optionList.add(
               new Option(mealPlans.mealPlanName, mealPlans.mealPlanId)
             ));
           }

       redirectToViewMealPlanRecipeList() {
           const mealPlanId = this.dataStore.get('mealPlanId');
           console.log("here is the supposed mealPlanId" + mealPlanId)
           if (mealPlanId != null) {
               window.location.href = `/digiPantryMealPlan.html?id=${mealPlanId}`;
           }
       }

       async submit(evt) {
           evt.preventDefault();

           const errorMessageDisplay = document.getElementById('error-message');
           errorMessageDisplay.innerText = ``;
           errorMessageDisplay.classList.add('hidden');

           const mealPlanId = document.getElementById('mealPlanSelect').value;
           console.log("selected mealPlan's id: " + mealPlanId);

           this.dataStore.set('mealPlanId', mealPlanId);


       }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createPantry = new digiPantryMealPlan();
    createPantry.mount();
};

window.addEventListener('DOMContentLoaded', main);