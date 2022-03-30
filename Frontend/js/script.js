// Config
const port = 4567;
let BASE_URL = `http://ec2-54-164-181-130.compute-1.amazonaws.com:${port}`;
//http://ec2-54-166-93-83.compute-1.amazonaws.com:8087/

// Round Robin
let actualPort = 0;

const HIDDEN = 'hidden';
const WARNING = 'warning';
const INPUT = 'input';
const SELECT = 'select';

const button = document.querySelector('.main--button');
const valueInput = document.querySelector('.input--container__input');
const operationSelect = document.querySelector('.input-units--select');
const inputError = document.querySelector('.input--container__error');
const resultContainer = document.querySelector('.main--result__container');
const resultText = document.querySelector('.main--result');
const resultValue = document.querySelector('.main--result__value');
const resultLoader = document.querySelector('.main--result__loader');

// Functions
// const rotateServer = () => {
//     actualPort = (actualPort + 1) % ports.length;

//     BASE_URL = `http://${window.location.hostname}:${ports[actualPort]}`;

//     console.log(`Port #${actualPort} -> URL: ${BASE_URL}`);
// }

const validateFields = () => {
    const value = valueInput.value;
    const option = operationSelect.value; 
    
    let areFieldsValid = false;

    // Input
    if (value.length === 0) {
        valueInput.classList.add(WARNING);
    } else {
        valueInput.classList.remove(WARNING);
    }

    // Select
    if (option < 0) {
        operationSelect.classList.add(WARNING);
    } else {
        operationSelect.classList.remove(WARNING);
    }

    // Error label
    if (value.length === 0 || option < 0) {
        inputError.classList.remove(HIDDEN);
        resultContainer.classList.add(HIDDEN);
    } else {
        inputError.classList.add(HIDDEN);
        areFieldsValid = true;
    }

    return areFieldsValid;

}

const sendRequest = async () => {
    const areFieldsValid = validateFields();

    if (areFieldsValid) {
        // 0: COS || 1: Fahrenheit
        const operation = operationSelect.value === '0' ? 'cos' : 'exp';
        const value = valueInput.value;

        const url = `${BASE_URL}/api/v1/${operation}?value=${value}`;

        try {
            // Show loader
            resultContainer.classList.remove(HIDDEN);
            resultLoader.classList.remove(HIDDEN);
            resultText.classList.add(HIDDEN); 

            const response = await fetch(url, {
                method: 'GET',
                headers: {
                  'Content-Type': 'application/json'
                }
            });
    
            const data = await response.json();

            if (data) {
                resultText.classList.remove(HIDDEN); 

                resultValue.innerHTML = `${data?.output}`
            }

        } catch(err) {
            console.error(err.message);

        } finally {
            resultLoader.classList.add(HIDDEN);
        }

        // Rotate server
        // rotateServer();
        
    }
}


button.addEventListener('click', sendRequest);