document.addEventListener('DOMContentLoaded', () => {
    fetchFuncionarios();
});

const host = window.location.protocol + "//" + window.location.host;


const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sNome = document.querySelector('#m-nome');
const sCpf = document.querySelector('#m-cpf');
const sEmail = document.querySelector('#m-email');
const sSenha = document.querySelector('#m-senha');
const btnSalvar = document.querySelector('#btnSalvar');
const sId = document.querySelector('#m-id');

function openModal(edit = false, index = 0) {
    modal.classList.add('active');

    modal.onclick = e => {
        if (e.target.className.indexOf('modal-container') !== -1) {
            modal.classList.remove('active');
        }
    };

    if (edit) {
        sId.value = index;
        fetch(`${host}/funcionarios/${index}`)
            .then(response => response.json())
            .then(data => {
                sId.value = index;
                sNome.value = data.nome;
                sCpf.value = data.cpf;
                sEmail.value = data.login;
                sSenha.value = data.senha;
            })
            .catch(error => console.error('Error:', error));
    } else {
        clearForm();
    }
}

function fetchFuncionarios() {
    fetch(`${host}/funcionarios`)
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = '';
            data.forEach(funcionario => {
                let tr = document.createElement('tr');
                tr.dataset.id = funcionario.id;
                tr.innerHTML = `
                    <td>${funcionario.nome}</td>
                    <td>${funcionario.cpf}</td>
                    <td>${funcionario.login}</td>
                    <td class="acao">
                        <button onclick="openModal(true, ${funcionario.id})"><i class='bx bx-edit'></i></button>
                    </td>
                    <td class="acao">
                        <button onclick="deleteFuncionario(${funcionario.id})"><i class='bx bx-trash'></i></button>
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => console.error('Error:', error));
}

btnSalvar.onclick = e => {
    e.preventDefault();

    if (sNome.value === '' || sCpf.value === '' || sEmail.value === '' || sSenha.value === '') {
        return;
    }

    const method = sId.value ? 'PUT' : 'POST';
    const url = sId.value ? `${host}/funcionarios/${sId.value}` : `${host}/funcionarios`;

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            id : sId.value,
            nome: sNome.value,
            cpf: sCpf.value,
            login: sEmail.value,
            senha: sSenha.value,
        }),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            alert('Cadastro realizado com sucesso!');
            modal.classList.remove('active');
            fetchFuncionarios();
            clearForm();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Erro ao realizar o cadastro.' + error);
        });
};

function deleteFuncionario(id) {
    fetch(`${host}/funcionarios/${id}`, {
        method: 'DELETE',
    })
        .then(data => {
            console.log('Success:', data);
            alert('Cadastro excluído com sucesso!');
            fetchFuncionarios();
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Erro ao excluir o cadastro.' + error);
        });
}

function clearForm() {
    sId.value = 0;
    sNome.value = '';
    sCpf.value = '';
    sEmail.value = '';
    sSenha.value = '';
}

