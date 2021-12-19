import {
    getParameterByName,
    setTextNode
} from '../js/utils.js';

window.addEventListener('load', () => {
    fetchAndDisplayChampionParameters();
});

function fetchAndDisplayChampionParameters() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayChampionParameters(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", 'http://localhost:8080/api/champions/' + getParameterByName('champion'), true);
    xhttp.send();
}

function displayChampionParameters(champion) {
    setTextNode('name', champion.name);
    setTextNode('race', champion.race.name);
    setTextNode('backstory', champion.backstory);
    setTextNode('level', champion.level);
    setTextNode('experience', champion.experience);
    setTextNode('healthPoints', champion.healthPoints);
    setTextNode('manaPoints', champion.manaPoints);
    setTextNode('inteligence', champion.inteligence);
    setTextNode('strength', champion.strength);
}



