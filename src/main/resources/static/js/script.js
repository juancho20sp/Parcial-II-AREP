// Config
const PORT = 8087;
const BASE_URL = `http://localhost:${PORT}`;

// Elements
const button = document.querySelector('#button');
const textArea = document.querySelector('#textarea');
const list = document.querySelector('#list');


// Functions
const render = (elements) => {
    list.innerHTML = '';

    elements.forEach(element => {
        const newListItem = document.createElement('li');
        newListItem.classList.add('list-group-item');
        newListItem.innerText = element;

        list.appendChild(newListItem);
    })
}

const sendData = async(data) => {    
    const options = {
        method: 'POST',
        // mode: 'cors',
        // cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
        // credentials: 'same-origin', // include, *same-origin, omit
        headers: {
          'Content-Type': 'application/json'
        },
        // redirect: 'follow', // manual, *follow, error
        // referrerPolicy: 'no-referrer', 
        body: JSON.stringify({
            text: data
        }) 
    }

    const response = await fetch(`${BASE_URL}/api/v1/messages`, options);
    const receivedData = await response.json();

    render(receivedData);
    // render([data]);
}




// Event listeners
button.addEventListener('click', (event) => {
    event.preventDefault();

    sendData(textArea.value);   
    textArea.value = '';
});