import {getParameterByName} from '../js/utils.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayChampion();
});

function fetchAndDisplayChampion() {
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
    xhttp.open("GET",'http://localhost:8080/api/users/' + getParameterByName('user') + '/champions/'
        + getParameterByName('champion'), true);
    xhttp.send();
}

function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayChampion();
        }
    };

    xhttp.open("PUT",'http://localhost:8080/api/users/' + getParameterByName('user') + '/champions/'
        + getParameterByName('champion'), true);

    const request = {
        'name': document.getElementById('name').value,
        'backstory': document.getElementById('backstory').value

    };

    xhttp.setRequestHeader('Content-Type', 'application/json');
    xhttp.send(JSON.stringify(request));
}
