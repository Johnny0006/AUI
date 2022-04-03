import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => createInfoAction(event));
    fetchAndDisplayCar();
    document.getElementById('owner').value = parseInt(getParameterByName('customer'))
});




function createInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCar();
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/cars/', true);

    const request = {
        'registration': document.getElementById('registration').value,
        'mark': document.getElementById('mark').value,
        'productionYear': parseInt(document.getElementById('productionYear').value),
        'customer': parseInt(getParameterByName('customer'))
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}



