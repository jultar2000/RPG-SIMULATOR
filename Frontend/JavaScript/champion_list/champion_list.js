import {
    getBackendURL,
    createButtonField,
    createLinkField,
    createTextField,
    clearElementChildren} from '../js/utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayChampions();
});

function fetchAndDisplayChampions() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayChampions(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET",getBackendURL() + '/champions/', true);
    xhttp.send();
}

function displayChampions(champions) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    champions.champions.forEach(champion => {
        tableBody.appendChild(createTableRow(champion));
    })
}

function createTableRow(champion) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextField(champion.name));
    tr.appendChild(createLinkField('view', '../champion_view/champion_view.html?champion=' + champion.id));
    tr.appendChild(createButtonField('delete', () => deleteChampion(champion)));
    return tr;
}

function deleteChampion(champion) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayChampions();
        }
    };
    xhttp.open("DELETE",getBackendURL() + '/champions/' + champion.id, true);
    xhttp.send();
}