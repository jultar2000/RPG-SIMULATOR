import {
    getParameterByName,
    clearElementChildren,
    createLinkField,
    createButtonField,
    createTextField,
    setTextNode
} from '../js/utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayUser();
    fetchAndDisplayChampions();
});

function fetchAndDisplayChampions() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayChampions(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET",'http://localhost:8080/api/users/' + getParameterByName('user') + '/champions', true);
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
    tr.appendChild(createLinkField('edit', '../champion_edit/champion_edit.html?user='
        + getParameterByName('user') + '&champion=' + champion.id));
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
    xhttp.open("DELETE", 'http://localhost:8080/api/users/' + getParameterByName('user')
        + '/champions/' + champion.id, true);

    xhttp.send();
}

function fetchAndDisplayUser() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayUser(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET",'http://localhost:8080/api/users/' + getParameterByName('user'), true);
    xhttp.send();
}

function displayUser(user) {
    setTextNode('login', user.login);
    setTextNode('name', user.name);
    setTextNode('surname', user.surname);
}
