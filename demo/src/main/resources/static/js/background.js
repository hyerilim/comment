const images = ["0.jpg", "1.jpg", "2.jpg"];

const chosenImage = images[Math.floor(Math.random()*images.length)];

const bgImage = document.createElement("img");

bgImage.src = `/resources/img/${chosenImage}`;

document.body.appendChild(bgImage);

let bg = document.querySelector("img");
bg.classList.add("bgImage");
