document.addEventListener('DOMContentLoaded', () => {
  fetchPedidos();
});

const host = "https://" + window.location.host;

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sComprador = document.querySelector('#comprador');
const sData = document.querySelector("#m-Data");
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
      fetch(`${host}/pedido/${index}`)
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

function editarPedido(index){
    window.location.href = `${host}/pages/detalhes-pedido.html?id=${index}`;
}

function fetchPedidos() {
  fetch(`${host}/pedidos/naoPagos`)
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
                  <td>R$ ${pedido.valorTotal.toFixed(2)}</td>
                  <td class="acao">
                      <button onclick="editarPedido(${pedido.id})" class="botao" >Detalhes</button>
                  </td>
              `;
              tbody.appendChild(tr);
          });
      })
      .catch(error => console.error('Error:', error));
}

btnSalvarPedido.onclick = e => {
  e.preventDefault();

  if (sComprador.value === '') {
      return;
  }

  const method = id ? 'PUT' : 'POST';
  const url = id ? `${host}/pedido/${id}` : `${host}/pedido`;
  const isPago = id ? true : false;

    fetch(`${host}/pedido/`, {
      method: 'POST',
      headers: {
          'Content-Type': 'application/json',
      },
      body: JSON.stringify({
          id : 0,
          comprador: sComprador.value,
          dataPedido: sData.value,
          valorTotal: 0,
          pago: isPago
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
  fetch(`${host}/pedido/${id}`, {
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
}
