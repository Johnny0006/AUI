import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    createImageCell,
    setTextNode
} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayCar();
});


function fetchAndDisplayCar() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCar(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/customers/' + getParameterByName('customer') + '/cars/' + getParameterByName('car'), true);
    xhttp.send();
}


function displayCar(car) {
    setTextNode('registration', car.registration);=
    setTextNode('mark', car.mark);
    setTextNode('productionYear', car.productionYear);
    setTextNode('owner', car.customer);
}
