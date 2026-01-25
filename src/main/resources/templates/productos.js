const {createApp} = Vue;

createApp({
    data() {
        return {
            productos: [],
            loading: true,
        }
    },
    mounted() {
        fetch('/api/productos')
            .then(response => response.json())
            .then(data => {
                this.productos = data;
                this.loading = false;
            })
            .catch(error => {
                console.error('Error buscando los productos:', error);
                this.loading = false;
            });
    }
}).mount('#app');
    