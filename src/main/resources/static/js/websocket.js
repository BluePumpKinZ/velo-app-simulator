let stompClient = null;
let consoleOutput;

function connect() {
    const socket = new SockJS("/simulation-socket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/simulationupdates', function (greeting) {
            showSimulationUpdate(JSON.parse(greeting.body));
        });
    });
}

function showSimulationUpdate(update) {

    if (consoleOutput.innerHTML === "Loading...")
        consoleOutput.innerHTML = "";

    const outputLineHTML = `<span class="text-success">${update.timestamp.substring(0, 22)}</span>
<span class="text-info">${update.message}</span>
<span>${update.progressPercentage}%</span><br>`;
    consoleOutput.innerHTML = outputLineHTML + consoleOutput.innerHTML;
}

document.addEventListener("DOMContentLoaded", function (event) {
    consoleOutput = document.getElementById("consoleOutput");
    consoleOutput.innerHTML = "Loading...";
    connect();
});