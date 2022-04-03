import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayCar();
});


function fetchAndDisplayCar() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/customers/' + getParameterByName('customer') + '/cars/'
        + getParameterByName('car'), true);
    xhttp.send();
}


function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCar();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/cars/' + getParameterByName('car'), true);

    const request = {
        'mark': document.getElementById('mark').value,
        'productionYear': parseInt(document.getElementById('productionYear').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}


