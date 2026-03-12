const {createApp} = Vue;

createApp({
    data() {
        return {
            ventas: []
        }
    },
    mounted() {
        fetch('/api/ventas')
            .then(response => response.json())
            .then(data => {
                this.ventas = data;
            })
            .catch(error => {
                console.error('Error buscando las ventas:', error);
            });
    }


}).mount('#app');