// Config
const ports = [8087, 8088, 8089]
let BASE_URL = `http://${window.location.hostname}:${ports[0]}`;
//http://ec2-54-166-93-83.compute-1.amazonaws.com:8087/

// Round Robin
let actualPort = 0;

// Elements
const button = document.querySelector('#button');
const fetchDataBtn = document.querySelector('#fetchDataBtn');
const textArea = document.querySelector('#textarea');
const list = document.querySelector('#list');

// Functions
const rotateServer = () => {
    actualPort = (actualPort + 1) % ports.length;

    BASE_URL = `http://${window.location.hostname}:${ports[actualPort]}`;

    // $
    console.log(`Port #${actualPort} -> URL: ${BASE_URL}`);
}


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
        data.length = 10;
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

    // Round Robin
    rotateServer();
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

    // Round Robin
    rotateServer();
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