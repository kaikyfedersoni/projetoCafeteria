document.addEventListener('DOMContentLoaded', () => {
    fetchFuncionarios();
});

const modal = document.querySelector('.modal-container');
const tbody = document.querySelector('tbody');
const sNome = document.querySelector('#m-nome');
const sCpf = document.querySelector('#m-cpf');
const sEmail = document.querySelector('#m-email');
const sSenha = document.querySelector('#m-senha');
const btnSalvar = document.querySelector('#btnSalvar');
let id;

function openModal(edit = false, index = 0) {
    modal.classList.add('active');

    modal.onclick = e => {
        if (e.target.className.indexOf('modal-container') !== -1) {
            modal.classList.remove('active');
        }
    };

    if (edit) {
        fetch(`http://localhost:8080/funcionarios/${index}`)
            .then(response => response.json())
            .then(data => {
                document.getElementById('id').value = data.id;
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
    fetch('http://localhost:8080/funcionarios')
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
    const id = document.getElementById('id').value;

    if (sNome.value === '' || sCpf.value === '' || sEmail.value === '' || sSenha.value === '') {
        return;
    }

    const method = id ? 'PUT' : 'POST';
    const url = id ? `http://localhost:8080/funcionarios/${id}` : 'http://localhost:8080/funcionarios';

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
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
    fetch(`http://localhost:8080/funcionarios/${id}`, {
        method: 'DELETE',
    })
        .then(response => response.json())
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
    document.getElementById('id').value = '';
    sNome.value = '';
    sCpf.value = '';
    sEmail.value = '';
    sSenha.value = '';
}
