var swiper = new Swiper(".home", {
  centeredSlides: true,
  autoplay: {
    delay: 12000,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
});


const divHidden = document.querySelector('.hidden-div');
const userIcon = document.querySelector('.bi-person');
const registerBtn = document.querySelector('.register-btn');
const loginBtn = document.querySelector('.login-btn');
const catalogContainer = document.querySelector('.catalog-container');

loginBtn.addEventListener('click', () => window.location = '/login')

registerBtn.addEventListener('click', () => window.location = '/register')

userIcon.addEventListener('click', () => {
  divHidden.classList.toggle('hidden');
  divHidden.classList.add('show');
});


document.addEventListener('DOMContentLoaded', async () => {
  try {
    const response = await fetch('http://localhost:8080/api/animes');
    const data = await response.json(); 
    
    if (data.length === 0) {
     
      catalogContainer.innerHTML = '<p class="message-error">Nenhum anime cadastrado.</p>';
    } else {
      
      data.forEach(item => {
        insertElement(item);
      });
    }
  } catch (error) {
    console.error(error)
    catalogContainer.innerHTML = '<p class="message-error">Erro ao carregar os animes.</p>';
  }
});



function insertElement(item) {
  catalogContainer.innerHTML += 
  `
     <div class="anime-card">
                <div class="anime-image">
                    <img src="${item.imagemUrl}" alt="${item.titulo}">
                    <div class="overlay"></div>
                </div>
                <h3 class="anime-title">${item.titulo}</h3>
                <h3 class="anime-genre">${item.genero}</h3>
                <div class="anime-info">
                    <h3 class="anime-title">${item.titulo}</h3>
                    <p class="anime-description">"${item.descricao}"</p>
                    <span class="anime-rating">
                        Nota: ${item.nota}
                        <img src="/img/star-icon.svg" alt="" width="18px">
                    </span>
                </div>
  `
}