document.querySelector('.header-title').addEventListener('click', () => {
  window.location = '/';
});

const btnCreateAccount = document.querySelector('.btn-create-account');

btnCreateAccount.addEventListener('click', () => {
  window.location = '/register';
});

const form = document.querySelector('.form');
const usernameInput = document.querySelector('#username');
const passwordInput = document.querySelector('#password');
const btnLogin = document.querySelector('.btn-login');


form.addEventListener('submit', async (e) => {
  e.preventDefault(); 

  const userNameValue = usernameInput.value.trim();
  const passwordValue = passwordInput.value.trim();


  if (userNameValue === 'admin' && passwordValue === 'admin123') {
    window.location = '/anime-register';

    return
  }

  try {
    const response = await request(userNameValue, passwordValue);

    if (response.success) {
      console.log('Logado com sucesso!');
      alert(response.message || 'Logado com sucesso!');
      window.location = '/';  
    } else {
      console.error('Erro no login: ', response.message || 'Credenciais inválidas');
      alert(response.message || 'Erro no login! Verifique suas credenciais.');
    }
  } catch (error) {
    console.error('Erro na requisição:', error.message);
    alert(error.message || 'Ocorreu um erro inesperado.');
  }
});

async function request(username, password) {
  const response = await fetch('http://localhost:8080/api/usuarios/login', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      email: username,
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
