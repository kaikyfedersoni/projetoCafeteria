document.addEventListener('DOMContentLoaded', () => {
    fetchProdutos();
    fetchPedido();
});

const host = "https://" + window.location.host;

const urlParams = new URLSearchParams(window.location.search);
const pedidoId = urlParams.get('id');

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('#tbody');
const m_tbody = document.querySelector('#m-tbody');
const sId = document.querySelector('#m-id');
const sNome = document.querySelector('#m-nome');
const btnBuscar = document.querySelector("#btnBuscar");

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
                        <button onclick="openModal(true, ${produto.id})"><i class='bx bx-edit'></i></button>
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
    let button = event.target.closest('button'); // Find the closest button ancestor
    let tr = button.closest('tr'); // Find the closest tr ancestor
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

function salvarEditar(){
    //e.preventDefault();

    if (sNome.value === '' || sDescricao.value === '' || sPreco.value === '') {
        return;
    }

    const method = sId.value ? 'PUT' : 'POST';
    const url = sId.value ? `${host}/produtos/${sId.value}` : `${host}/produtos`;

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ id: sId.value, nome: sNome.value, descricao: sDescricao.value, preco: sPreco.value}),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            if(method.match("POST"))
                alert('Cadastro realizado com sucesso!');
            else
                alert("Cadastro editado com sucesso!")
            fetchProdutos();
            clearForm();
            modal.classList.remove('active');
        })
        .catch((error) => {
            console.error('Error:', error);
            alert('Erro ao realizar o cadastro.' + error);
        });
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

function clearForm() {
    sId.value = 0;
    sNome.value = '';
    //sDescricao.value = '';
    //sPreco.value = '';
}
