
// Otevření modálního okna s fotkou
var modal = document.querySelector('.modal');
var modalImg = document.querySelector('#modal-img');
var galleryImgs = document.querySelectorAll('.gallery img');
var modalClose = document.querySelector('.modal-close');

// Index aktuální otevřené fotografie
var currentImgIndex = 0;

function openModal(imgSrc) {
    modalImg.src = imgSrc;
    modal.style.display = 'flex';
    for (var i = 0; i < galleryImgs.length; i++) {
        if (galleryImgs[i].src === imgSrc) {
            currentImgIndex = i;
            break;
        }
    }
}

// Zavření modálního okna
function closeModal() {
    modal.style.display = 'none';
}

// Přidání událostí na fotky v galerii
galleryImgs.forEach(function (img) {
    img.addEventListener('click', function () {
        openModal(img.src);
        currentImgIndex = index;
    });
});

// Přidání události na zavření modálního okna
modalClose.addEventListener('click', function () {
    closeModal();
});
// Přidání události na klik mimo modální okno pro zavření
modal.addEventListener('click', function (event) {
    if (event.target === modal) {
        closeModal();
    }
});

// Přidání události na klávesu "Esc" pro zavření modálního okna
document.addEventListener('keydown', function (event) {
    if (event.key === 'Escape' && modal.style.display === 'flex') {
        closeModal();
    }
});
document.addEventListener('keydown', function (event) {
    if (event.key === 'Escape' && modal.style.display === 'flex') {
        closeModal();
    } else if (event.key === 'ArrowLeft' && modal.style.display === 'flex') {
        // Předchozí fotografie
        currentImgIndex = (currentImgIndex - 1 + galleryImgs.length) % galleryImgs.length;
        modalImg.src = galleryImgs[currentImgIndex].src;
    } else if (event.key === 'ArrowRight' && modal.style.display === 'flex') {
        // Následující fotografie
        currentImgIndex = (currentImgIndex + 1) % galleryImgs.length;
        modalImg.src = galleryImgs[currentImgIndex].src;
    }
});
