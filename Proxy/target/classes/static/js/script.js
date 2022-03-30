// Config
const PORT = 8087;
const BASE_URL = `http://localhost:${PORT}`;

// Elements
const button = document.querySelector('#button');
const fetchDataBtn = document.querySelector('#fetchDataBtn');
const textArea = document.querySelector('#textarea');
const list = document.querySelector('#list');

// Functions
const render = (elements) => {
    list.innerHTML = '';

    elements.forEach(element => {
        const newListItem = document.createElement('li');
        newListItem.classList.add('list-group-item');
        newListItem.innerText = `${element.text} : ${element.date}`;

        list.appendChild(newListItem);
    })
}

const cleanData = (data) => {
    data = data.reverse();

    if (data.length > 10) {
        data.splice(0, 10);
    }

    const cleanedData = data.map(data => {
        return {
            ...JSON.parse(data.text),
            date: new Date(data.date.$date)
        }
    })

    return cleanedData;
}

const sendData = async(data) => {    
    const options = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            text: data
        }) 
    }

    const response = await fetch(`${BASE_URL}/api/v1/messages`, options);
    const receivedData = await response.json();

    const cleanedData = cleanData(receivedData);

    render(cleanedData);
}

const getAllElements = async() => {
    const options = {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json'
        },
    }

    const response = await fetch(`${BASE_URL}/api/v1/messages`, options);
    const receivedData = await response.json();
  
    const cleanedData = cleanData(receivedData);
    render(cleanedData);
}


// Event listeners
button.addEventListener('click', (event) => {
    event.preventDefault();

    sendData(textArea.value);   
    textArea.value = '';
});

fetchDataBtn.addEventListener('click', (event) => {
    event.preventDefault();

    getAllElements();
})