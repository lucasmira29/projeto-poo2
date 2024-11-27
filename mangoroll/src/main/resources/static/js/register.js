document.querySelector('.header-title').addEventListener('click', () => window.location = '/');

const form = document.querySelector('.form');
const nameInput = document.querySelector('input[placeholder="Name"]');
const emailInput = document.querySelector('input[placeholder="Email"]');
const usernameInput = document.querySelector('input[placeholder="Username"]');
const passwordInput = document.querySelector('input[placeholder="Password"]');
const repeatPasswordInput = document.querySelector('input[placeholder="Repeat Password"]');


form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const name = nameInput.value.trim();
  const email = emailInput.value.trim();
  const username = usernameInput.value.trim();
  const password = passwordInput.value.trim();
  const repeatPassword = repeatPasswordInput.value.trim();

  if (!name || !email || !username || !password || !repeatPassword) {
    alert('Todos os campos são obrigatórios.');
    return;
  }

  if (password !== repeatPassword) {
    alert('As senhas não coincidem!');
    return;
  }

  try {
    const response = await registerUser(name, email, username, password);

    if (response.success) {
      console.log('Usuário registrado com sucesso!');
      alert(response.message || 'Cadastro realizado com sucesso!');
      window.location = '/login';
    } else {
      console.error('Erro no registro: ', response.message || 'Erro no cadastro.');
      alert(response.message || 'Erro ao registrar. Tente novamente.');
    }
  } catch (error) {
    console.error('Erro na requisição:', error.message);
    alert(error.message || 'Ocorreu um erro inesperado.');
  }
});

async function registerUser(name, email, username, password) {
  const response = await fetch('http://localhost:8080/api/usuarios/cadastro', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      nome: name,
      email: email,
      nomeUsuario: username,
      senha: password,
    }),
  });

  if (!response.ok) {
    const errorText = await response.json();
    throw new Error(errorText.message || `Erro HTTP: ${response.status}`);
  }

  const data = await response.json();
  return data;
}
