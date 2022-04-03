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
    fetchAndDisplayCustomer();
    fetchAndDisplayCars();
});

document.getElementById("addButton").onclick = function () {
    window.location='../car_add/car_add.html?customer=' + getParameterByName('customer');
};


function fetchAndDisplayCars() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCars(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/customers/' + getParameterByName('customer') + '/cars', true);
    xhttp.send();
}


function displayCars(cars) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    cars.cars.forEach(car => {
        tableBody.appendChild(createTableRow(car));
    })
}


function createTableRow(car) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(car.registration));
    tr.appendChild(createTextCell(car.mark));
    tr.appendChild(createLinkCell('view', '../car_view/car_view.html?customer='
            + getParameterByName('customer') + '&car=' + car.registration));
    tr.appendChild(createLinkCell('edit', '../car_edit/car_edit.html?customer='
            + getParameterByName('customer') + '&car=' + car.registration));
    tr.appendChild(createButtonCell('delete', () => deleteCar(car)));
    return tr;
}

function deleteCar(car) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCars();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/cars/' + car.registration, true);
    xhttp.send();
}



function fetchAndDisplayCustomer() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCustomer(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/customers/' + getParameterByName('customer'), true);
    xhttp.send();
}


function displayCustomer(customer) {
    setTextNode('id', customer.id);
    setTextNode('name', customer.name);
    setTextNode('surname', customer.surname);
}
