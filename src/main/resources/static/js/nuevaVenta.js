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
            items: [],
            mostrarModalProducto: false,
            productoSeleccionado: null,
            // PAGINACIÓN
            paginaActual: 1,
            productosPorPagina: 5
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
        },
        abrirBusquedaProducto(){
            this.mostrarModalProducto = true
        },
        seleccionarProducto(producto){
            this.productoSeleccionado = producto
            this.busquedaProducto = producto.nombre
            this.mostrarModalProducto = false
        },
        recalcularItem(item) {
            const cantidad = item.cantidad || 0;
            const precio = item.precioUnitario || 0;
            const descuento = item.descuento || 0;

            item.subtotal = (cantidad * precio) - ((cantidad * precio) * (descuento / 100));
        },
        siguientePagina() {
            if(this.paginaActual < this.totalPaginas){
                this.paginaActual++
            }
        },

        paginaAnterior() {
            if(this.paginaActual > 1){
                this.paginaActual--
            }
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
        },
        productosPaginados() {
            const inicio = (this.paginaActual - 1) * this.productosPorPagina
            const fin = inicio + this.productosPorPagina
            return this.productosFiltrados.slice(inicio, fin)
        },

        totalPaginas() {
            return Math.ceil(this.productosFiltrados.length / this.productosPorPagina)
        }
    }
}).mount('#appNuevaVenta');