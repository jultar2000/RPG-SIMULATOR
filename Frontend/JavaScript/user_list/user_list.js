import {getBackendUrl} from '../js/configuration';
import { createButtonField, createLinkField, createTextField } from '../js/utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayUsers();
});

function fetchAndDisplayUsers() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayUsers(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/users', true);
    xhttp.send();
}

function displayUsers(users) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    users.users.forEach(user => {
        tableBody.appendChild(createTableRow(user));
    })
}

function createTableRow(user) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextField(user));
    tr.appendChild(createLinkField('view', '../user_view/user_view.html?user=' + user));
    tr.appendChild(createButtonField('delete', () => deleteUser(user)));
    return tr;
}

function deleteUser(user) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayUsers();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/users/' + user, true);
    xhttp.send();
}
