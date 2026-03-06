const {createApp} = Vue;

createApp({
    data() {
        return {
            productos: [],
            loading: true,
            menuAbierto: false,
            menuAddAbierto: false,
            nuevoProducto: {
                nombre: '',
                precioActual: 0,
                stock: 0
            }

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
    },
    methods: {
        toggleMenu(){
            this.menuAbierto = !this.menuAbierto;
        },
        toggleAddMenu(){
            this.menuAddAbierto = !this.menuAddAbierto;
        },
        agregarProducto(){

            if (!this.nuevoProducto.nombre || this.nuevoProducto.precioActual <= 0 || this.nuevoProducto.stock < 0) {
                alert('Por favor, completa todos los campos correctamente.');
                return;
            }

            fetch('/api/productos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(this.nuevoProducto)
            });
            this.nuevoProducto = {
                nombre: '',
                precio: 0,
                stock: 0
            };
        }
    }
}).mount('#app');
    