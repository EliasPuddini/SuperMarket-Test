const {createApp} = Vue;
createApp({
    data() {
        return {
            sucursales: [],
            productos: [],
            productosFiltrados: [],
            sucursalSeleccionada: "",
            busquedaProducto: "",
            productoSeleccionado: null,
            cantidad: 1,
            items: []
        }
    },
    mounted() {
        this.cargarProductos();
        this.cargarSucursales();
    },
    methods: {
        cargarProductos() {
            fetch('/api/productos')
                .then(Response => Response.json())
                .then(data => {
                    this.productos = data;
                    console.log(this.productos);
                });},
        cargarSucursales() {
            fetch('/api/sucursales')
                .then(response => response.json())
                .then(data => {
                    this.sucursales = data;
                    this.console.log(this.sucursales);
                })
            },
        buscarProducto() {
            if (this.busquedaProducto.length < 2) {
                this.productosFiltrados = [];
                return;
            }

            this.productosFiltrados = this.productos.filter(p =>
                p.nombre.toLowerCase().includes(this.busquedaProducto.toLowerCase())
            );
        },
        seleccionarProducto(producto) {
            this.productoSeleccionado = producto;
            this.busquedaProducto = producto.nombre;
            this.productosFiltrados = [];
        },
        agregarItem() {
            const precioUnitario = Number(this.productoSeleccionado.precioActual);
            const cantidad = Number(this.cantidad);
            const descuento = 0;

            if (isNaN(precioUnitario) || isNaN(cantidad)) {
                alert("Precio o cantidad inválidos");
                return;
            }

            const subtotal = precioUnitario * cantidad;
            const precioFinal = subtotal * (1 - descuento / 100);

            this.items.push({
                producto: this.productoSeleccionado,
                cantidad,
                descuento,
                precioUnitario,
                subtotal,
                precioFinal
            });

            // reset
            this.busquedaProducto = "";
            this.productoSeleccionado = null;
            this.cantidad = 1;
        },
        eliminarItem(index) {
            this.items.splice(index, 1);
        },
        guardarVenta() {
            console.log("Confirmar venta clickeado");

            //TODO
        }

    },
    computed: {
        puedeAgregar() {
            return (
                this.productoSeleccionado &&
                this.cantidad > 0 &&
                !isNaN(this.cantidad)
            );
        },total() {
            return this.items.reduce((acc, item) => {
                return acc + Number(item.precioFinal || 0);
            }, 0).toFixed(2);
        },
        puedeGuardar() {
            return (
                this.items.length > 0 &&
                this.sucursalSeleccionada !== null &&
                this.sucursalSeleccionada !== ""
            );
        }
    }
}).mount('#appNuevaVenta');