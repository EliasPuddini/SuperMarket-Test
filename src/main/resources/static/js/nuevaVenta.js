const { createApp } = Vue;

createApp({

    data() {
        return {

            sucursales: [],
            productos: [],
            productosFiltrados: [],

            sucursalSeleccionada: null,
            busquedaProducto: "",

            productoSeleccionado: null,

            cantidad: 1,
            descuento: 0,

            items: [],

            mostrarModalProducto: false,

            // PAGINACIÓN
            paginaActual: 1,
            productosPorPagina: 5

        }
    },

    mounted() {
        this.cargarSucursales();
    },

    methods: {

        /* =========================
           SUCURSALES
        ========================= */

        cargarSucursales() {

            fetch('/api/sucursales')
                .then(response => response.json())
                .then(data => {

                    this.sucursales = data;

                    console.log("Sucursales:", this.sucursales);

                });

        },


        /* =========================
           PRODUCTOS POR SUCURSAL
        ========================= */

        cargarProductosPorSucursal() {

            if (!this.sucursalSeleccionada) return;

            fetch(`/api/productos/sucursal/${this.sucursalSeleccionada.id}`)
                .then(response => response.json())
                .then(data => {

                    this.productos = data;

                    console.log("Productos sucursal:", data);

                });

        },


        /* =========================
           BUSQUEDA PRODUCTO
        ========================= */

        abrirBusquedaProducto() {

            if (!this.sucursalSeleccionada) {
                alert("Debe seleccionar una sucursal primero");
                return;
            }

            this.mostrarModalProducto = true;

        },


        buscarProducto() {

            if (this.busquedaProducto.length < 2) {

                this.productosFiltrados = [];
                return;

            }

            this.productosFiltrados = this.productos.filter(p =>
                p.nombre.toLowerCase()
                    .includes(this.busquedaProducto.toLowerCase())
            );

            this.paginaActual = 1;

        },


        seleccionarProducto(producto) {

            this.productoSeleccionado = producto;
            this.busquedaProducto = producto.nombre;
            this.mostrarModalProducto = false;

        },


        /* =========================
           ITEMS
        ========================= */

        agregarItem() {

            if (!this.productoSeleccionado) return;

            const precioUnitario = Number(this.productoSeleccionado.precioActual);
            const cantidad = Number(this.cantidad);
            const descuento = Number(this.descuento);

            if (isNaN(precioUnitario) || isNaN(cantidad)) {

                alert("Precio o cantidad inválidos");
                return;

            }

            const subtotalBase = precioUnitario * cantidad;
            const subtotal = subtotalBase * (1 - descuento / 100);

            this.items.push({

                producto: this.productoSeleccionado,
                cantidad,
                descuento,
                precioUnitario,
                subtotal

            });

            // reset
            this.busquedaProducto = "";
            this.productoSeleccionado = null;
            this.cantidad = 1;
            this.descuento = 0;

        },


        eliminarItem(index) {

            this.items.splice(index, 1);

        },


        recalcularItem(item) {

            const cantidad = item.cantidad || 0;
            const precio = item.precioUnitario || 0;
            const descuento = item.descuento || 0;

            const subtotalBase = cantidad * precio;

            item.subtotal = subtotalBase - (subtotalBase * (descuento / 100));

        },


        /* =========================
           PAGINACIÓN
        ========================= */

        siguientePagina() {

            if (this.paginaActual < this.totalPaginas) {
                this.paginaActual++;
            }

        },


        paginaAnterior() {

            if (this.paginaActual > 1) {
                this.paginaActual--;
            }

        },


        /* =========================
           GUARDAR VENTA
        ========================= */

        guardarVenta() {

            console.log("Confirmar venta clickeado");

            console.log("Sucursal seleccionada:", this.sucursalSeleccionada);
            console.log("Items a vender:", this.items);

            venta = {
                sucursal: this.sucursalSeleccionada,
                items: this.items.map(item => ({
                    producto: item.producto,
                    cantidad: item.cantidad,
                    precio: (item.precioUnitario || 0) * (1 - (item.descuento || 0) / 100)
                })),
                fecha: new Date().toISOString()
            }
            console.log(JSON.stringify(venta, null, 2));

            fetch('/api/ventas/' + this.sucursalSeleccionada.id, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(venta)
            })
            .then(async response => {

                const mensaje = await response.text(); // <-- leer body

                console.log("Status:", response.status);
                console.log("Mensaje servidor:", mensaje);

                if (response.ok) {

                    alert(mensaje); // usa mensaje del servidor

                    this.sucursalSeleccionada = null;
                    this.items = [];

                } else {

                    alert("Error: " + mensaje);

                }

            })
            .catch(error => {

                console.error("Error al guardar la venta:", error);
                alert("Error de conexión con el servidor");

            });
        }

    },


    /* =========================
       COMPUTED
    ========================= */

    computed: {

        puedeAgregar() {

            return (
                this.productoSeleccionado &&
                this.cantidad > 0 &&
                !isNaN(this.cantidad)
            );

        },


        total() {

            return this.items.reduce((acc, item) => {

                return acc + Number(item.subtotal || 0);

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

            const inicio = (this.paginaActual - 1) * this.productosPorPagina;
            const fin = inicio + this.productosPorPagina;

            return this.productosFiltrados.slice(inicio, fin);

        },


        totalPaginas() {

            return Math.ceil(
                this.productosFiltrados.length / this.productosPorPagina
            );

        },

        totalProducto(){
            if(!this.productoSeleccionado) return "0.00"

            const precio = Number(this.productoSeleccionado.precioActual || 0)
            const cantidad = Number(this.cantidad || 0)
            const descuento = Number(this.descuento || 0)

            const subtotal = precio * cantidad
            const total = subtotal - (subtotal * descuento / 100)

            return total.toFixed(2)
        }

    },


    /* =========================
       WATCH
    ========================= */

    watch: {

        sucursalSeleccionada() {

            this.productoSeleccionado = null;
            this.busquedaProducto = "";
            this.productosFiltrados = [];

            this.cargarProductosPorSucursal();

        }

    }

}).mount('#appNuevaVenta');