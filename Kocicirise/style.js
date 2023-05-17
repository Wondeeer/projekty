const imageContainer = document.querySelector(".wrapper");

imageContainer.addEventListener("click", function () {
  this.classList.toggle("active");
});