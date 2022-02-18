import {
    getParameterByName,
    setTextNode
} from '../js/utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayRaceParameters();
});

function fetchAndDisplayRaceParameters() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayRaceParameters(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", 'http://localhost:8080/api/races/' + getParameterByName('race'), true);
    xhttp.send();
}

function displayRaceParameters(race) {
    setTextNode('name', race.name);
    setTextNode('description', race.description);
}


