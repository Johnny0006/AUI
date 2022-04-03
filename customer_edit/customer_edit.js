import {getParameterByName} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    infoForm.addEventListener('submit', event => updateInfoAction(event));
    fetchAndDisplayCustomer();
});

function fetchAndDisplayCustomer() {
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
    xhttp.open("GET", getBackendUrl() + '/api/customers/' + getParameterByName('customer'), true);
    xhttp.send();
}


function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayCustomer();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/customers/' + getParameterByName('customer'), true);

    const request = {
        'name': document.getElementById('name').value,
        'surname': document.getElementById('surname').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}



