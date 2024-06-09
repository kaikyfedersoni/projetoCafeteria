document.addEventListener('DOMContentLoaded', () => {
    fetchProdutos();
});

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sId = document.querySelector('#m-id');
const sNome = document.querySelector('#m-nome');
const sDescricao = document.querySelector('#m-descricao');
const sPreco = document.querySelector('#m-preco');


function openModal(edit = false, index = 0) {
    modal.classList.add('active');

    modal.onclick = e => {
        if (e.target.className.indexOf('modal-container') !== -1) {
            modal.classList.remove('active');
        }
    };

    if (edit) {
        sId.value = index;
        fetch(`http://localhost:8080/produtos/${index}`)
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

function salvarEditar(){
    //e.preventDefault();

    if (sNome.value === '' || sDescricao.value === '' || sPreco.value === '') {
        return;
    }

    const method = sId.value ? 'PUT' : 'POST';
    const url = sId.value ? `http://localhost:8080/produtos/${sId.value}` : 'http://localhost:8080/produtos';

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


function deleteProduto(id) {
    fetch(`http://localhost:8080/produtos/${id}`, {
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
    sDescricao.value = '';
    sPreco.value = '';
}