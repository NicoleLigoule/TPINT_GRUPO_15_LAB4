function toggleSubmenu(event) {
    event.preventDefault();
    const parent = event.target.closest('.menu-item');

    // Cerrar otros submenús
    document.querySelectorAll('.menu-item').forEach(item => {
        if (item !== parent) {
            item.classList.remove('active');
        }
    });

    // Alternar el submenú seleccionado
    parent.classList.toggle('active');
}

function toggleSidebar() {
    const sidebar = document.querySelector('.sidebar');
    if (sidebar) {
        sidebar.classList.toggle('active');
        console.log("Sidebar activado:", sidebar.classList.contains("active"));
    } else {
        console.error("No se encontró el elemento sidebar.");
    }
}
