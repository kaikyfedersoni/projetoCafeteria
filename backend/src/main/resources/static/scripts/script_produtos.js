document.addEventListener('DOMContentLoaded', () => {
    fetchProdutos();
});

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sNome = document.querySelector('#m-nome');
const sDescricao = document.querySelector('#m-descricao');
const sPreco = document.querySelector('#m-preco');
const btnSalvarProduto = document.querySelector('#btnSalvarProduto');
let id;

function openModal(edit = false, index = 0) {
    modal.classList.add('active');

    modal.onclick = e => {
        if (e.target.className.indexOf('modal-container') !== -1) {
            modal.classList.remove('active');
        }
    };

    if (edit) {
        fetch(`http://localhost:8080/produtos/${index}`)
            .then(response => response.json())
            .then(data => {
                id = data.id;
                sNome.value = data.nome;
                sDescricao.value = data.descricao;
                sPreco.value = data.preco;
            })
            .catch(error => console.error('Error:', error));
    } else {
        clearForm();
    }
}

function fetchProdutos() {
    fetch('http://localhost:8080/produtos')
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = '';
            data.forEach(produto => {
                let tr = document.createElement('tr');
                tr.dataset.id = produto.id;
                tr.innerHTML = `
                    <td>${produto.nome}</td>
                    <td>${produto.descricao}</td>
                    <td>R$ ${produto.preco.toFixed(2)}</td>
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

btnSalvarProduto.onclick = e => {
    e.preventDefault();

    if (sNome.value === '' || sDescricao.value === '' || sPreco.value === '') {
        return;
    }

    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8080/produtos/${id}` : 'http://localhost:8080/produtos';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            nome: sNome.value,
            descricao: sDescricao.value,
            preco: parseFloat(sPreco.value),
        }),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            alert('Produto cadastrado com sucesso!');
            modal.classList.remove('active');
            fetchProdutos();
            clearForm();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Erro ao cadastrar o produto.' + error);
        });
};

function deleteProduto(id) {
    fetch(`http://localhost:8080/produtos/${id}`, {
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            alert('Produto excluído com sucesso!');
            fetchProdutos();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Erro ao excluir o produto.' + error);
        });
}

function clearForm() {
    id = undefined;
    sNome.value = '';
    sDescricao.value = '';
    sPreco.value = '';
}
