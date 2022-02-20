import {
    getBackendURL,
    createButtonField,
    createLinkField,
    createTextField,
    clearElementChildren} from '../js/utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayRaces();
});

function fetchAndDisplayRaces() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayRaces(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET",'http://localhost:8080/api/races/', true);
    xhttp.send();
}

function displayRaces(races) {
    console.log(races);
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    races.races.forEach(race => {
        tableBody.appendChild(createTableRow(race));
    })
}

function createTableRow(race) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextField(race.name));
    tr.appendChild(createLinkField('view', '../race_view/race_view.html?race=' + race.name));
    tr.appendChild(createButtonField('delete', () => deleteRace(race)));
    return tr;
}

function deleteRace(race) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayRaces();
        }
    };
    xhttp.open("DELETE",getBackendURL() + '/races/' + race.name, true);
    xhttp.send();
}