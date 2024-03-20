function loadGetMsg(event) {
    event.preventDefault();

    let msgVar = document.getElementById("msg").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {

        const logs = JSON.parse(this.responseText);

        let tableHtml = '<table border="1"><tr><th>Log</th><th>Date</th></tr>';
        logs.forEach(log => {
            const date = new Date(log.timestamp.$date).toLocaleString();
            tableHtml += `<tr><td>${log.message}</td><td>${date}</td></tr>`;
        });
        tableHtml += '</table>';

        document.getElementById("getrespmsg").innerHTML = tableHtml;
    }
    xhttp.open("GET", "/log?msg="+msgVar);
    xhttp.send();
}
