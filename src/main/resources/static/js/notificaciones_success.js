let notificacionBox=document.getElementById('notificacionBox');
let msg='<i class="bx bxs-heart"></i>';

function showNotificacion() {
    let notificacion=document.createElement('div');
    notificacion.classList.add('notificacion');
    notificacion.innerHTML = msg;
    notificacionBox.appendChild(notificacion);

    setTimeout( ()=> {
        notificacion.remove();
    }, 6000)
}