document.addEventListener('DOMContentLoaded', () => {
    fetchProdutos();
    fetchPedido();
});

const host = "http://" + window.location.host;

const urlParams = new URLSearchParams(window.location.search);
const pedidoId = urlParams.get('id');

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('#tbody');
const m_tbody = document.querySelector('#m-tbody');
const sId = document.querySelector('#m-id');
const sNome = document.querySelector('#m-nome');
const btnBuscar = document.querySelector("#btnBuscar");

const modal2 = document.querySelector(".modal-container2");
const editarIdProduto = document.querySelector("#m-idProduto");
const editarNomeProduto = document.querySelector("#m-nomeProduto");
const editarDescricao = document.querySelector("#m-Descricao");
const editarValor = document.querySelector("#m-Preco");
const editarQuantidade = document.querySelector("#m-Quantidade");

const thead = document.querySelector("#thead");
const valorTotal = document.querySelector("#TotalPedido");


function openModal(edit = false, index = 0) {
    modal.classList.add('active');

    modal.onclick = e => {
        if (e.target.className.indexOf('modal-container') !== -1) {
            modal.classList.remove('active');
        }
    };

    fetch(`${host}/produtos`)
        .then(response => response.json())
        .then(data => {
            m_tbody.innerHTML = '';
            data.forEach(produto => {
                let tr = document.createElement('tr');
                tr.dataset.id = produto.id;
                tr.innerHTML = `
                    <form>
                    <td>${produto.nome}</td>
                    <td>${produto.descricao}</td>
                    <td>R$ ${produto.preco.toFixed(2)}</td>
                    <td class="acao">
                        <input type="number" maxlength="999" required/>
                    </td>
                    <td class="acao" width="15%">
                        <button onclick="incluirProduto(event)">Adicionar</button>
                    </td>
                    </form>
                `;
                m_tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));

    if (edit) {
        sId.value = index;
        fetch(`${host}/produtos/${index}`)
            .then(response => response.json())
            .then(data => {
                sId.value = data.id;
                sNome.value = data.nome;
                sDescricao.value = data.descricao;
                sPreco.value = data.preco;
            })
            .catch(error => console.error('Error:', error));
    } else {
        clearForm();
    }
}

function openModal2(index = 0) {
    modal2.classList.add('active');

    modal2.onclick = e => {
        if (e.target.className.indexOf('modal-container') !== -1) {
            modal2.classList.remove('active');
        }
    };

    fetch(`${host}/produtosPedido/${index}`)
        .then(response => response.json())
        .then(data => {
            console.log(data);
                editarIdProduto.value = index;
                editarNomeProduto.value = data.nome;
                editarValor.value = data.preco;
                editarDescricao.value = data.descricao;
                editarQuantidade.value = data.quantidade;
        })
        .catch(error => console.error('Error:', error));

}

async function fetchProdutos() {

    await atualizarTotalPedido();

    await fetch(`${host}/produtosPedido/pedidos/${pedidoId}`)
        .then(response => response.json())
        .then(data => {
            let total = 0.0;
            tbody.innerHTML = '';
            data.forEach(produto => {
                let tr = document.createElement('tr');
                total = total + produto.valorTotal;
                tr.dataset.id = produto.id;
                tr.innerHTML = `
                    <td>${produto.nome}</td>
                    <td>${produto.descricao}</td>
                    <td>R$ ${produto.preco.toFixed(2)}</td>
                    <td>${produto.quantidade}</td>
                    <td>R$ ${produto.valorTotal.toFixed(2)}</td>
                    <td class="acao">
                        <button onclick="openModal2(${produto.id})"><i class='bx bx-edit'></i></button>
                    </td>
                    <td class="acao">
                        <button onclick="deleteProduto(${produto.id})"><i class='bx bx-trash'></i></button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));
}

function fetchPedido() {
    fetch(`${host}/pedidos/${pedidoId}`)
        .then(response => response.json())
        .then(data => {
            document.querySelector("#nomeCliente").innerHTML = "Pedido " + data.id.toString().padStart(3, '0') + " - Comprador: " + data.comprador;
        })
        .catch(error => console.error('Error:', error));
}

async function atualizarTotalPedido() {
    await fetch(`${host}/pedidos/calcularTotal/${pedidoId}`)
        .then(response => response.json())
        .then(data => {
            valorTotal.innerHTML = "R$ " + data.toFixed(2);
        })
        .catch(error => console.error('Error:', error));
}


function buscarProduto(event){

    event.preventDefault();

    if (sNome.value === '') {
        return;
    }

    fetch(`${host}/produtos/nome/${sNome.value}`)
        .then(response => response.json())
        .then(data => {
            m_tbody.innerHTML = '';
            data.forEach(produto => {
                let tr = document.createElement('tr');
                tr.dataset.id = produto.id;
                tr.innerHTML = `
                    <form method="get">
                    <td>${produto.nome}</td>
                    <td>${produto.descricao}</td>
                    <td>R$ ${produto.preco.toFixed(2)}</td>
                    <td class="acao">
                        <input type="number" maxlength="999" required/>
                    </td>
                    <td class="acao" width="15%">
                        <button onclick="incluirProduto(event)">Adicionar</button>
                    </td>
                    </form>
                `;
                m_tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));

}

async function incluirProduto(event){
    let button = event.target.closest('button');
    let tr = button.closest('tr');
    let input = tr.querySelector('input[type="number"]');

    if (input.value === '')
        return;

    let quantidade = input.value;
    let produtoId = tr.dataset.id;

    let requestBody = {
        idProduto: produtoId,
        idPedido: pedidoId,
        quantidade: quantidade
    };

    await fetch(`${host}/produtosPedido/`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => response.json())
        .then(data => {
            console.log(data);
        })
        .catch(error => {
            console.error('Erro:', error);
        });
    await closeModal();
}

async function closeModal() {
    modal.classList.remove('active');
    await fetchProdutos();
}

async function deleteProduto(id) {
    await fetch(`${host}/produtosPedido/${id}`, {
        method: 'DELETE',
    })
        .then(data => {
            console.log('Success:', data);
            alert('Cadastro excluído com sucesso!');
            fetchProdutos();
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Erro ao excluir o cadastro.');
        });
}

async function editarProduto() {

    document.getElementById('editarForm').addEventListener('submit', function(event) {
        event.preventDefault();
    });

    if (editarQuantidade.value === '')
        return;

    let requestBody = {
        idProduto: editarIdProduto.value,
        idPedido: pedidoId,
        quantidade: editarQuantidade.value
    };

    await fetch(`${host}/produtosPedido/${editarIdProduto.value}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    })
        .then(response => response.json())
        .catch(error => {
            console.error('Erro:', error);
        });

    await closeModal2();

}

async function pagarPedido(){

    if (!confirm("Deseja Confirmar o Pagamento?"))
        return;

    await fetch(`${host}/pedidos/pagarPedido/${pedidoId}`)
        .catch(error => {
            console.error('Erro:', error);
        });

    window.location.href = `${host}/pages/pedidos.html`;

}

async function closeModal2() {
    modal2.classList.remove('active');
    await fetchProdutos();
}

function clearForm() {
    sId.value = 0;
    sNome.value = '';
}
