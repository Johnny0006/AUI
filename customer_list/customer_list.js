import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/dom_utils.js';
import {getBackendUrl} from '../js/configuration.js';

window.addEventListener('load', () => {
    fetchAndDisplayCustomers();
});



function fetchAndDisplayCustomers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayCustomers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/customers', true);
    xhttp.send();
}


function displayCustomers(customers) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    customers.customers.forEach(customer => {
        tableBody.appendChild(createTableRow(customer));
    })
}


function createTableRow(customer) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(customer.id));
    tr.appendChild(createTextCell(customer.name));
    tr.appendChild(createLinkCell('view', '../customer_view/customer_view.html?customer=' + customer.id));
    tr.appendChild(createLinkCell('edit', '../customer_edit/customer_edit.html?customer=' + customer.id));
    tr.appendChild(createButtonCell('delete', () => deleteCustomer(customer)));
    return tr;
}


function deleteCustomer(customer) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayCustomers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/customers/' + customer.id, true);
    xhttp.send();
}
