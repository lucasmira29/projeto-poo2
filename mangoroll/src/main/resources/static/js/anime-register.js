document.querySelector('.header-title').addEventListener('click', () => window.location = '/');

const form = document.querySelector('.form');
const titleInput = document.querySelector('input[placeholder="Title"]');


form.addEventListener('submit', async (e) => {
  e.preventDefault();

  const title = titleInput.value.trim();


  if (!title) {
    alert('O título é obrigatório.');
    return;
  }

  try {
    const response = await createAnime(title);

    if (response.success) {
      console.log('Anime registrado com sucesso!');
      alert(response.message || 'Anime cadastrado com sucesso!');
      window.location = '/';  
    } else {
      console.error('Erro no cadastro do anime: ', response.message || 'Erro ao cadastrar o anime.');
      alert(response.message || 'Erro ao registrar o anime. Tente novamente.');
    }
  } catch (error) {
    console.error('Erro na requisição:', error.message);
    alert(error.message || 'Ocorreu um erro inesperado.');
  }
});

async function createAnime(title) {
  const response = await fetch('http://localhost:8080/api/animes', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(title),
  });

  if (!response.ok) {
    const errorText = await response.json();
    throw new Error(errorText.message || `Erro HTTP: ${response.status}`);
  }

  const data = await response.json();
  return data;
}
