document.addEventListener("DOMContentLoaded", () => {
  const lista = document.getElementById("listaEmpleos");
  const btnLogout = document.getElementById("btnLogout");
  const modal = document.getElementById("modalCurriculum");
  const formCurriculum = document.getElementById("formCurriculum");
  const btnCancelar = document.getElementById("cancelarCurriculum");
  const tituloCurriculum = document.getElementById("tituloCurriculum");
  let empleoSeleccionado = null;

  const userData = localStorage.getItem("usuarioActivo");

  if (!userData) {
    alert("Acceso denegado. Debes iniciar sesión como candidato.");
    window.location.href = "login.html";
    return;
  }

  const user = JSON.parse(userData);
  if (!user.tipo || user.tipo.toUpperCase() !== "CANDIDATO") {
    alert("Acceso denegado. Debes iniciar sesión como candidato.");
    window.location.href = "login.html";
    return;
  }

  btnLogout.addEventListener("click", () => {
    localStorage.removeItem("usuarioActivo");
    alert("Has cerrado sesión correctamente.");
    window.location.href = "login.html";
  });

  // Cargar vacantes
  async function cargarEmpleos() {
    try {
      const res = await fetch("http://localhost:8080/empleos");
      if (!res.ok) throw new Error("No se pudo obtener la lista de empleos");

      const data = await res.json();
      lista.innerHTML = "";

      if (data.length === 0) {
        lista.innerHTML = `<p>No hay vacantes disponibles por el momento.</p>`;
        return;
      }

      data.forEach(emp => {
        const card = document.createElement("div");
        card.classList.add("oferta-card");
        card.innerHTML = `
          <h3>${emp.titulo}</h3>
          <p>${emp.descripcion}</p>
          <small>📍 ${emp.ubicacion || "Ubicación no especificada"}</small><br>
          <small>Publicado por: <strong>${emp.empresa}</strong></small><br><br>
          <button class="btnPostular" data-id="${emp.id}" data-titulo="${emp.titulo}">Postularme</button>
        `;
        lista.appendChild(card);
      });

      // Evento click → mostrar formulario
      document.querySelectorAll(".btnPostular").forEach(btn => {
        btn.addEventListener("click", e => {
          empleoSeleccionado = e.target.dataset.id;
          const titulo = e.target.dataset.titulo;
          tituloCurriculum.textContent = `Currículum para: ${titulo}`;
          modal.classList.remove("hidden"); // 🔥 Aquí se muestra solo al dar clic
        });
      });

    } catch (error) {
      console.error("❌ Error al cargar empleos:", error);
      lista.innerHTML = `<p>Error al conectar con el servidor.</p>`;
    }
  }

  // Enviar currículum
  formCurriculum.addEventListener("submit", async e => {
    e.preventDefault();
    if (!empleoSeleccionado) return alert("No se seleccionó ninguna vacante.");

    const dataCurriculum = {
      id_usuario: user.id,
      telefono: document.getElementById("telefono").value.trim(),
      educacion: document.getElementById("educacion").value.trim(),
      experiencia: document.getElementById("experiencia").value.trim(),
      habilidades: document.getElementById("habilidades").value.trim(),
      carta_presentacion: document.getElementById("carta_presentacion").value.trim()
    };

    try {
      // Guardar currículum
      const res = await fetch("http://localhost:8080/curriculum", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dataCurriculum)
      });
      const data = await res.json();
      alert(data.message || "✅ Currículum enviado correctamente");

      // Registrar postulación
      await fetch("http://localhost:8080/postulaciones", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          id_usuario: user.id,
          id_empleo: empleoSeleccionado,
          carta_presentacion: dataCurriculum.carta_presentacion
        })
      });

      formCurriculum.reset();
      modal.classList.add("hidden");
      empleoSeleccionado = null;

    } catch (error) {
      console.error("❌ Error al enviar currículum:", error);
      alert("Error al enviar el currículum.");
    }
  });

  // Cerrar formulario
  btnCancelar.addEventListener("click", () => {
    modal.classList.add("hidden");
    formCurriculum.reset();
  });

  cargarEmpleos();
});
