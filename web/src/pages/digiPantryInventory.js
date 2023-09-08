import DigiPantryClient from '../api/digiPantryClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class CreatePantry extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);

    mount() {
            document.getElementById('create').addEventListener('click', this.submit);

            this.header.addHeaderToPage();

            this.client = new DigiPantryClient();

const main = async () => {
    const createPantry = new CreatePantry();
    createPantry.mount();
};

window.addEventListener('DOMContentLoaded', main);