document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    form.addEventListener('submit', fetchItems);
});

const host = "http://" + window.location.host;
const tbody = document.querySelector('tbody');
const sStartDate = document.querySelector("#startDate");
const sEndDate = document.querySelector("#endDate");
const valorTotal = document.querySelector("#TotalPedido");

function fetchItems(event) {
    event.preventDefault();

    const startDate = sStartDate.value;
    const endDate = sEndDate.value;

    if (!startDate || !endDate) {
        alert("Por favor, selecione ambas as datas.");
        return;
    }

    let total = 0;

    const url = `${host}/pedidos/buscarPeriodo?startDate=${startDate}&endDate=${endDate}`;

    fetch(url)
        .then(response => {
            console.log('Status da resposta:', response.status);
            console.log('Tipo de conteúdo:', response.headers.get('content-type'));
            return response.json();
        })
        .then(data => {
            console.log('Resposta da API:', data);
            if (!Array.isArray(data)) {
                data = [data]; // Converte a resposta em uma lista se não for uma
            }
            tbody.innerHTML = '';
            data.forEach(pedido => {
                let tr = document.createElement('tr');
                tr.dataset.id = pedido.id;
                tr.innerHTML = `
                    <td>${formatDate(pedido.dataPedido)}</td>
                    <td>${pedido.comprador}</td>
                    <td>R$ ${pedido.valorTotal.toFixed(2)}</td>
                `;
                tbody.appendChild(tr);
                total = total + pedido.valorTotal;

            });
            valorTotal.innerHTML = "R$ " + total.toFixed(2);
        })
        .catch(error => console.error('Erro:', error));


}

function formatDate(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

function editarPedido(index){
    window.location.href = `http://localhost:8080/pages/detalhes-pedido.html?id=${index}`;
}