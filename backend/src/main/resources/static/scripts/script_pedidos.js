document.addEventListener('DOMContentLoaded', () => {
  fetchPedidos();
});

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sComprador = document.querySelector('#comprador');
const sProdutos = document.querySelector('#produtos');
const sValorTotal = document.querySelector('#valorTotal');
const btnSalvarPedido = document.querySelector('#btnSalvarPedido');
const sId = document.querySelector("#m-id");

function openModal(edit = false, index = 0) {
  modal.classList.add('active');

  modal.onclick = e => {
      if (e.target.className.indexOf('modal-container') !== -1) {
          modal.classList.remove('active');
      }
  };

  if (edit) {
      fetch(`http://localhost:8080/pedido/${index}`)
          .then(response => response.json())
          .then(data => {
              sId.value = data.id;
              sComprador.value = data.comprador;
              sProdutos.value = data.produtos;
              sValorTotal.value = data.valorTotal;
          })
          .catch(error => console.error('Error:', error));
  } else {
      clearForm();
  }
}

function formatDate(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0'); // Months are zero-based
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

function fetchPedidos() {
  fetch('http://localhost:8080/pedidos/naoPagos')
      .then(response => response.json())
      .then(data => {
          tbody.innerHTML = '';
          data.forEach(pedido => {
              let tr = document.createElement('tr');
              tr.dataset.id = pedido.id;
              tr.innerHTML = `
                  <td>${pedido.id}</td>
                  <td>${pedido.comprador}</td>
                  <td>${formatDate(pedido.dataPedido)}</td>
                  <td>${pedido.valorTotal}</td>
                  <td class="acao">
                      <button onclick="openModal(true, ${pedido.id})"><i class='bx bx-edit'></i></button>
                  </td>
                  <td class="acao">
                      <button onclick="deletePedido(${pedido.id})"><i class='bx bx-trash'></i></button>
                  </td>
              `;
              tbody.appendChild(tr);
          });
      })
      .catch(error => console.error('Error:', error));
}

btnSalvarPedido.onclick = e => {
  e.preventDefault();

  if (sComprador.value === '' || sProdutos.value === '' || sValorTotal.value === '') {
      return;
  }

  const method = id ? 'PUT' : 'POST';
  const url = id ? `http://localhost:8080/pedido/${id}` : 'http://localhost:8080/pedido';

  fetch(url, {
      method: method,
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({
          comprador: sComprador.value,
          produtos: sProdutos.value,
          valorTotal: sValorTotal.value,
      }),
  })
      .then(response => response.json())
      .then(data => {
          console.log('Success:', data);
          alert('Pedido salvo com sucesso!');
          modal.classList.remove('active');
          fetchPedidos();
          clearForm();
      })
      .catch(error => {
          console.error('Error:', error);
          alert('Erro ao salvar o pedido.' + error);
      });
};

function deletePedido(id) {
  fetch(`http://localhost:8080/pedido/${id}`, {
      method: 'DELETE',
  })
      .then(response => response.json())
      .then(data => {
          console.log('Success:', data);
          alert('Pedido excluído com sucesso!');
          fetchPedidos();
      })
      .catch(error => {
          console.error('Error:', error);
          alert('Erro ao excluir o pedido.' + error);
      });
}

function clearForm() {
  id = undefined;
  sComprador.value = '';
  sProdutos.value = '';
  sValorTotal.value = '';
}
