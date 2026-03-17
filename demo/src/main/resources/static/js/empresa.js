document.addEventListener("DOMContentLoaded", () => {

const form = document.getElementById("formEmpleo");
const lista = document.getElementById("listaEmpleos");

const userData = localStorage.getItem("usuarioActivo");

if (!userData) {
alert("Debes iniciar sesión");
window.location.href="login.html";
return;
}

const user = JSON.parse(userData);

// cargar empleos
async function cargarEmpleos(){

try{

const res = await fetch("http://localhost:8080/empleos");
const data = await res.json();

lista.innerHTML="";

// filtrar empleos de esta empresa
const empleosEmpresa = data.filter(
(emp)=> emp.idEmpresa == user.id
);

if(empleosEmpresa.length === 0){
lista.innerHTML=`<p class="placeholder">Aún no has publicado vacantes.</p>`;
return;
}

empleosEmpresa.forEach(emp=>{

const card = document.createElement("div");
card.classList.add("oferta-card");

card.innerHTML=`
<h3>${emp.titulo}</h3>
<p>${emp.descripcion}</p>
<small>📍 ${emp.ubicacion}</small><br>

<button class="btnActualizar" data-id="${emp.id}">
✏️ Actualizar
</button>

<button class="btnEliminar" data-id="${emp.id}">
🗑️ Eliminar
</button>
`;

lista.appendChild(card);

});

// actualizar
document.querySelectorAll(".btnActualizar").forEach(btn=>{

btn.addEventListener("click",async(e)=>{

const id=e.target.dataset.id;

const nuevoTitulo=prompt("Nuevo título");
const nuevaDescripcion=prompt("Nueva descripción");
const nuevaUbicacion=prompt("Nueva ubicación");

if(!nuevoTitulo || !nuevaDescripcion) return;

await fetch(`http://localhost:8080/empleos/${id}`,{
method:"PUT",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify({
titulo:nuevoTitulo,
descripcion:nuevaDescripcion,
ubicacion:nuevaUbicacion,
idEmpresa:user.id
})
});

alert("Vacante actualizada");

cargarEmpleos();

});

});

// eliminar
document.querySelectorAll(".btnEliminar").forEach(btn=>{

btn.addEventListener("click",async(e)=>{

const id=e.target.dataset.id;

if(!confirm("¿Eliminar vacante?")) return;

await fetch(`http://localhost:8080/empleos/${id}`,{
method:"DELETE"
});

alert("Vacante eliminada");

cargarEmpleos();

});

});

}catch(error){

console.error("Error al cargar empleos:",error);

lista.innerHTML=`<p>Error al cargar vacantes</p>`;

}

}

// publicar
form.addEventListener("submit",async(e)=>{

e.preventDefault();

const nuevoEmpleo={

titulo:document.getElementById("titulo").value,
descripcion:document.getElementById("descripcion").value,
ubicacion:document.getElementById("ubicacion").value,
idEmpresa:user.id

};

await fetch("http://localhost:8080/empleos",{

method:"POST",
headers:{
"Content-Type":"application/json"
},
body:JSON.stringify(nuevoEmpleo)

});

form.reset();

cargarEmpleos();

});

cargarEmpleos();

});