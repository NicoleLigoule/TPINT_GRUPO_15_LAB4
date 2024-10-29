function toggleSubmenu(event) {
    event.preventDefault();
    const parent = event.target.closest('.menu-item');


    document.querySelectorAll('.menu-item').forEach(item => {
        if (item !== parent) {
            item.classList.remove('active');
        }
    });


    parent.classList.toggle('active');
}

function toggleSidebar() {
    const sidebar = document.getElementById('sidebar');
    sidebar.classList.toggle('active');
}
