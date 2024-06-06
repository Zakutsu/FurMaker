package tiendaFursuit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class FursuitMain {
    private static ArrayList<Maker> makers = new ArrayList<Maker>();
    private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    public static void main(String[] args) {
        cargarDatos();
        int eleccion;

        do {
            System.out.println("\nBienvenido a FurMaker. ¿Eres un administrador o un usuario?");
            System.out.println(" 0. Salir");
            System.out.println(" 1. Administrador");
            System.out.println(" 2. Usuario");
            eleccion = seleccionInt(0, 2);

            switch (eleccion) {
                case 0:
                    System.out.println("\n Saliendo del programa...");
                    break;

                case 1:
                    sesionAdmin();
                    break;
                
                case 2:
                    sesionUser();
                    break;
            }
        } while (eleccion > 0);
        guardarDatos();
    }
    

    private static int seleccionInt(int opcionMinima, int opcionMaxima) {
        Scanner scInt = new Scanner(System.in);
        int opcion = 0;
        
        do {
            try {
                opcion = scInt.nextInt();
            } catch (Exception e) {
                opcion = opcionMinima - 1;
            }
            if (opcion < opcionMinima || opcion > opcionMaxima) {
                scInt.nextLine();
                System.out.println("El dato introducido no está entre las opciones. Por favor, introduzca uno nuevo:");
            }
        } while (opcion < opcionMinima || opcion > opcionMaxima);
              
        return opcion;
    }
    private static int seleccionInt() {
        Scanner scInt = new Scanner(System.in);
        boolean repetir = false;
        int opcion = 0;
        
        do {
            try {
                opcion = scInt.nextInt();
                repetir = false;
            } catch (Exception e) {
                repetir = true;
                scInt.nextLine();
                System.out.println("El dato introducido no está entre las opciones. Por favor, introduzca uno nuevo:");
            }
        } while (repetir);
              
        return opcion;
    }
    // Si limiteCaracteres = 0,  no hay límite de carácteres
    private static String seleccionString(int limiteCaracteres) {
        Scanner scString = new Scanner(System.in);
        String opcion = "";
        boolean repetir = false;

        do {
            opcion = scString.nextLine();
            opcion = opcion.trim();
            repetir = false;
            
            if (opcion == "") {
                System.out.println("No has introducido ningún dato. Por favor, introduzca algo:");
                repetir = true;
            } else if (limiteCaracteres != 0 && opcion.length() > limiteCaracteres) {
                System.out.println("Has superado el límite de carácteres. Por favor, introduzca otra cosa:");
                repetir = true;
            }
        } while (repetir);

        return opcion;
    }
    private static ArrayList<EnumMetodoPago> seleccionMetodosPago() {
        ArrayList<EnumMetodoPago> metodosPago = new ArrayList<EnumMetodoPago>(Arrays.asList(EnumMetodoPago.values()));
        ArrayList<EnumMetodoPago> metodosPagoUsuario = new ArrayList<EnumMetodoPago>();
        boolean repetir;

        do {
            repetir = false;
            EnumMetodoPago metodoPagoActual = null;
            System.out.println("\nEscriba alguno de los siguientes métodos: " + metodosPago);
            String metodoPago = seleccionString(0).toUpperCase();

            try {
                metodoPagoActual = EnumMetodoPago.valueOf(metodoPago);
            } catch (Exception e) {
                System.out.println("El método de pago introducido no existe, por favor, intruduzca uno nuevo:");
                repetir = true;
            }

            if (!repetir) {
                if (metodosPago.contains(metodoPagoActual)) {
                    metodosPago.remove(metodoPagoActual);
                    metodosPagoUsuario.add(metodoPagoActual);
                    System.out.println("\nSe ha agregado el método de pago con éxito.");
                } else {
                    System.out.println("\nEl método de pago ya fue agregado anteriormente.");
                }

                if (metodosPago.size() != 0) {
                    System.out.println("¿Deseas agregar otro?");
                    repetir = seleccionBoolean();
                } else {
                    repetir = false;
                }

            }
        } while (repetir);

        return metodosPagoUsuario;
    }
    private static String convertirBooleanASiONo(boolean dato) {
        return dato ? "Sí" : "No" ;
    }

    private static EnumMetodoPago seleccionMetodoPago() {
        ArrayList<EnumMetodoPago> metodosPago = new ArrayList<EnumMetodoPago>(Arrays.asList(EnumMetodoPago.values()));
        EnumMetodoPago metodoPagoActual = null;

        do {
            System.out.println("\nEscriba alguno de los siguientes métodos: " + metodosPago);
            String metodoPago = seleccionString(0).toUpperCase();

            try {
                metodoPagoActual = EnumMetodoPago.valueOf(metodoPago);
            } catch (Exception e) {
                    System.out.println("El método de pago introducido no existe, por favor, intruduzca uno nuevo:");
            }
        } while (metodoPagoActual == null);

        return metodoPagoActual;
    }

    private static boolean seleccionBoolean() {
        boolean repetir = false;
        
        do {
            repetir = false;

            System.out.println("Escriba si o no:");
            String respuesta = seleccionString(2).toLowerCase();

            if (respuesta.equals("si")) {
                return true;
            } else if (respuesta.equals("no")) {
                return false;
            } else {
                System.out.println("Lo que has introducido no es una respuesta válida. Por favor, introduzca una de las respuestas válidas.");
                repetir = true;
            }
        } while (repetir);

        return false;
    }

    private static EnumTamano seleccionTamano() {
        System.out.println("  1. No tiene");
        System.out.println("  2. Pequeño");
        System.out.println("  3. Mediano");
        System.out.println("  4. Grande");
        int tamano = seleccionInt(1, 4);

        switch (tamano) {
            case 1:
                return EnumTamano.NO_TIENE;
        
            case 2:
                return EnumTamano.PEQUENO;

            case 3:
                return EnumTamano.MEDIANO;

            case 4:
                return EnumTamano.GRANDE;
        }

        return EnumTamano.NO_TIENE;
    }

    private static EnumTipoPatas seleccionTipoPatas() {
        System.out.println("  1. Plantigrade");
        System.out.println("  2. Digitigrade");
        int tamano = seleccionInt(1, 2);

        switch (tamano) {
            case 1:
                return EnumTipoPatas.PLANTIGRADE;
        
            case 2:
                return EnumTipoPatas.DIGITIGRADE;
        }

        return EnumTipoPatas.PLANTIGRADE;
    }


    private static void sesionAdmin() {
        int eleccion;

        do {
            System.out.println("\nBienvenido a la sesión de administrador. ¿Qué desea realizar?");
            System.out.println(" 0. Salir");
            System.out.println(" 1. Crear una cuenta de maker");
            System.out.println(" 2. Revisar cuentas de makers");
            System.out.println(" 3. Eliminar una cuenta de maker");
            System.out.println(" 4. Revisar pedidos");
            System.out.println(" 5. Asignarle un pedido a un maker");
            System.out.println(" 6. Modificar un pedido");
            eleccion = seleccionInt(0, 6);
    
            switch (eleccion) {
                case 0:
                    System.out.println("\n Cerrando la sesión...");
                    break;
    
                case 1:
                    crearMaker();
                    break;
    
                case 2:
                    revisarMakers();
                    break;
                
                case 3:
                    eliminarMaker();
                    break;

                case 4:
                    revisarPedidos();
                    break;

                case 5:
                    asignarMakerAPedido();
                    break;

                case 6:
                    modificarPedidoAdmin();
                    break;
            }
        } while (eleccion > 0);
    }
    
    private static void crearMaker() {
        System.out.println("\nIntroduzca la información del nuevo usuario.");
        System.out.println(" Nombre:");
        String nombre = seleccionString(0);
        
        System.out.println("\n Trabajo diario en minutos (1440 máx):");
        int minutosTrabajoDiario = seleccionInt(1, 1440);
        
        System.out.println("\n Precio por cabeza de fursuit:");
        int precioHead = seleccionInt(0, Integer.MAX_VALUE);
        
        System.out.println("\n Precio por suit:");
        int precioSuit = seleccionInt(0, Integer.MAX_VALUE);
        
        System.out.println("\n Método de pago:");
        ArrayList<EnumMetodoPago> metodoPagos = seleccionMetodosPago();
        
        Maker maker = new Maker(nombre, minutosTrabajoDiario, precioHead, precioSuit, metodoPagos);
        makers.add(maker);
        
        System.out.println("\nCuenta de maker creada con éxito.");
    }

    private static void revisarMakers() {

        if (makers != null && !makers.isEmpty()) {
            System.out.println("\nEstos son los makers actuales:");
    
            for (int i = 0; i < makers.size(); i++) {
                System.out.println("\n--------------------------------------------------\n");
                System.out.println(makers.get(i).getNombre() + ":");
                System.out.println(" [ Trabajo diario: " + makers.get(i).getMinutosTrabajoDiario() + " min ]");
                System.out.println(" [ Métodos de pago: " + makers.get(i).getMetodoPagos() + " ]");
                System.out.println(" [ Precio por head: " + makers.get(i).getPrecioHead() + " EUR ]");
                System.out.println(" [ Precio por suit: " + makers.get(i).getPrecioSuit() + " EUR ]");
            }
            System.out.println("\n--------------------------------------------------\n");
        } else {
            System.out.println("Actualmente no hay ningún maker creado. Por favor, cree alguno antes de continuar.");
        }
    }

    private static void eliminarMaker() {
        if (makers != null && !makers.isEmpty()) {
            System.out.println("\n¿Qué cuenta de maker deseas eliminar?");
            System.out.println(" 0. Salir");
            
            for (int i = 0; i < makers.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + makers.get(i).getNombre() + " [ Trabajo diario: " + makers.get(i).getMinutosTrabajoDiario() + " min ]");    
            }
            
            int seleccion = seleccionInt(0, makers.size());
            
            if (seleccion > 0) {
                makers.remove(seleccion - 1);
                System.out.println("\n Maker eliminado con éxito.");
            }
        } else {
            System.out.println("\nAún no se ha creado ninguna cuenta de maker. Por favor, cree una antes de continuar.");
        }
    }
    
    private static void revisarPedidos() {
        if (pedidos != null && !pedidos.isEmpty()) {
            Maker makerAsignado;
            System.out.println("Estos son los pedidos actuales:");
            
            for (int i = 0; i < pedidos.size(); i++) {
                makerAsignado = makerAsignado(pedidos.get(i));
                System.out.println("\n--------------------------------------------------\n");
                System.out.println("Pedido ID Nº" + pedidos.get(i).getId());
                System.out.println(" [ Nombre del cliente: " + pedidos.get(i).getNombreCliente() + " ]");
                System.out.println(" [ Dirección del cliente: " + pedidos.get(i).getDireccion() + " ]");
                System.out.println(" [ Fecha de realización del pedido: " + pedidos.get(i).getFechaPedido() + " ]");
                System.out.println(" [ Estado del pedido: " + pedidos.get(i).getEstadoPedido() + " ]");
                if (makerAsignado != null) {
                    System.out.println(" [ Maker asignado: " + makerAsignado.getNombre() + " - " + makerAsignado.getMinutosTrabajoDiario() + "min ]");
                } else {
                    System.out.println(" [ Maker asignado: Ninguno ]");
                }
                if (pedidos.get(i).getFechaInicioMaker() != null) {
                    System.out.println(" [ Fecha de inicio del fursuit: " + pedidos.get(i).getFechaInicioMaker() + " ]");
                    if (pedidos.get(i).getFechaFinalizacionMaker() != null) {
                        System.out.println(" [ Fecha de finalización del fursuit: " + pedidos.get(i).getFechaFinalizacionMaker() + " ]");
                    } else {
                        System.out.println(" [ Fecha aproximada de finalización del fursuit: " + pedidos.get(i).getFechaAproximadaFinalizacionPedido(makerAsignado));
                    }
                } else {
                    System.out.println(" [ Fecha de inicio del fursuit: No hay ]");
                }
                System.out.println(" [ Método de pago utilizado: " + pedidos.get(i).getMetodoPago() + " ]");
                for (Fursuit fursuit : pedidos.get(i).getFursuits()) {
                    System.out.println(" [ INFORMACIÓN DE LOS FURSUITS ]"); 
                    System.out.println("\tRaza: " + fursuit.getRaza());
                    if (fursuit instanceof FursuitHead) {
                        FursuitHead fursuitHead = (FursuitHead) fursuit;
                        System.out.println("\tCP: " + fursuitHead.getColorPrincipal() + " - CS: " + fursuitHead.getColorSecundario());
                        System.out.println("\tCOD: " + fursuitHead.getColorOjoDerecho() + " - COI: " + fursuitHead.getColorOjoIzquierdo());
                        System.out.println("\tTO: " + fursuitHead.getTamanoOrejas() + " - TC: " + fursuitHead.getTamanoCuernos() + " - Vent.: " + convertirBooleanASiONo(fursuitHead.isVentilador()));
                        System.out.println("\t Coment.: " + fursuitHead.getComentario());
                    }
                    if (fursuit instanceof FursuitSuit) {
                        FursuitSuit fursuitSuit = (FursuitSuit) fursuit;
                        System.out.println("\tCP: " + fursuitSuit.getColorPrincipal() + " - CS: " + fursuitSuit.getColorSecundario());
                        System.out.println("\tCB: " + fursuitSuit.getColorBeans() + " - TG: " + convertirBooleanASiONo(fursuitSuit.isGarras()));
                        System.out.println("\t Coment.: " + fursuitSuit.getComentario());
                    }
                }
            }
            System.out.println("\n--------------------------------------------------\n");
        } else {
            System.out.println("Actualmente no hay ningún pedido creado. No querrás uno tú, ¿verdad?");
        }

        
    }
    private static Maker makerAsignado(Pedido pedido) {
        for (Maker maker : makers) {
            if (maker.getPedido() != null && maker.getPedido().getId() == pedido.getId()) {
                return maker;
            }
        }
        
        return null;
    }

    private static void asignarMakerAPedido() {
        if (makers == null || makers.isEmpty()) {
            System.out.println("Aún no se ha creado ningún maker. Por favor, cree uno para poder continuar.");
        } else if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Aún no hay ningún pedido creado. No querrás uno tú, ¿verdad?");
        } else {
            boolean makerDisponible = false;

            ArrayList<Integer> idPedidosAsignados = new ArrayList<Integer>();
            int cantidadPedidosAsignados = 0;
            for (Maker maker : makers) {
                if (maker.getPedido() == null) {
                    makerDisponible = true;
                } else {
                    cantidadPedidosAsignados++;
                    idPedidosAsignados.add(maker.getPedido().getId());
                }
            }

            if (makerDisponible || cantidadPedidosAsignados < pedidos.size()) {
                HashMap<Integer, Integer> posicionesPedido = new HashMap<Integer, Integer>();
                int posicionPedido = 1;

                System.out.println("Elija uno de los siguientes pedidos para ser asignados:");
                System.out.println(" 0. Salir");
                for (int i = 0; i < pedidos.size(); i++) {
                    if (!idPedidosAsignados.contains(pedidos.get(i).getId())) {
                        System.out.println(" " + posicionPedido + ". ID Nº" + pedidos.get(i).getId() + " [ Nombre cliente: " + pedidos.get(i).getNombreCliente() + " ]");
                        posicionesPedido.put(posicionPedido, i);
                        posicionPedido++;
                    }
                }
                
                int seleccionPedido = seleccionInt(0, posicionesPedido.size());
                if (seleccionPedido > 0) {
                    HashMap<Integer, Integer> posicionesMaker = new HashMap<Integer, Integer>();
                    int posicionMaker = 1;
    
                    System.out.println("Elija uno de los siguientes makers para asignar:");
                    System.out.println(" 0. Salir");
                    for (int i = 0; i < makers.size(); i++) {
                        if (makers.get(i).getPedido() == null || makers.get(i).getMetodoPagos().contains(pedidos.get(posicionesPedido.get(seleccionPedido)).getMetodoPago())) {
                            System.out.println(" " + posicionMaker + ". " + makers.get(i).getNombre() + " [ Trabajo diario: " + makers.get(i).getMinutosTrabajoDiario() + " min ]");
                            posicionesMaker.put(posicionMaker, i);
                            posicionMaker++;
                        }
                    }
                    
                    int seleccionMaker = seleccionInt(0, posicionesMaker.size());
                    if (seleccionMaker > 0) {
                        for (Fursuit fursuit : pedidos.get(posicionesPedido.get(seleccionPedido)).getFursuits()) {
                            System.out.println("¿Cuánto tiempo (en minutos) te vas a tirar con el siguiente fursuit?");
                            if (fursuit instanceof FursuitHead) {
                                FursuitHead fursuitHead = (FursuitHead) fursuit;
                                System.out.println(
                                    " Raza: " + fursuitHead.getRaza()
                                    + " - Tamaño de las orejas:" + fursuitHead.getTamanoOrejas()
                                    + " - Tamaño de los cuernos: " + fursuitHead.getTamanoCuernos()
                                    + " - Ventilador: " + fursuitHead.isVentilador()
                                    + "\n - Comentario: " + fursuitHead.getComentario()
                                );
                                fursuit.setTiempoCreacionMin(seleccionInt(0, Integer.MAX_VALUE));
                            } else if (fursuit instanceof FursuitSuit) {
                                FursuitSuit fursuitSuit = (FursuitSuit) fursuit;
                                System.out.println(
                                    " Raza: " + fursuitSuit.getRaza()
                                    + " - Tamaño de la cola: " + fursuitSuit.getTamanoCola()
                                    + " - Tipo de patas: " + fursuitSuit.getTipoPatas()
                                    + "\n - Comentario: " + fursuitSuit.getComentario()
                                );
                                fursuit.setTiempoCreacionMin(seleccionInt(0, Integer.MAX_VALUE));
                            }
                        }

                        makers.get(posicionesMaker.get(seleccionMaker)).setPedido(pedidos.get(posicionesPedido.get(seleccionPedido)));
                        System.out.println("\n Pedido asignado con éxito.");
                    }
                }
            } else {
                System.out.println("No hay ningún maker con pedidos disponibles.");
            }
        }
    }

    private static void modificarPedidoAdmin() {
        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Actualmente no hay ningún pedido creado. No querrás uno tú, ¿verdad?");
        } else {
            HashMap<Integer, Integer> posicionesPedido = new HashMap<Integer, Integer>();
            int posicionPedido = 1;
    
            for (int i = 0; i < pedidos.size(); i++) {
                if (pedidos.get(i).getEstadoPedido() != "Terminado") {
                    posicionesPedido.put(posicionPedido, i);
                    posicionPedido++;
                }
            }
            
            if (posicionesPedido.isEmpty()) {
                System.out.println("Todos los pedidos actuales están terminados.");
            } else {
                System.out.println("¿Qué pedido deseas modificar?");
                System.out.println(" 0. Salir");
                for (int i = 0; i < posicionesPedido.size(); i++) {
                    System.out.println(
                        " " + (i + 1) + ". ID Nº" + pedidos.get(posicionesPedido.get(i)).getId() 
                        + " [ Nombre cliente: " + pedidos.get(posicionesPedido.get(i)).getNombreCliente() 
                        + " - Estado del pedido: " + pedidos.get(posicionesPedido.get(i)).getEstadoPedido() +  " ]"
                    );
                }
                
                int seleccionPedido = seleccionInt(0, posicionesPedido.size());
                if (seleccionPedido > 0) {
                    int indiceSeleccionPedido = seleccionPedido - 1;

                    if (pedidos.get(posicionesPedido.get(indiceSeleccionPedido)).getEstadoPedido() == "No empezado") {
                        pedidos.get(posicionesPedido.get(indiceSeleccionPedido)).setFechaInicioMaker(LocalDate.now());
                        System.out.println("\n Fecha de inicio establecida con éxito.");
                    } else {
                        pedidos.get(posicionesPedido.get(indiceSeleccionPedido)).setFechaFinalizacionMaker(LocalDate.now());
                        System.out.println("\n Fecha de finalización establecida con éxito.");
                    }
                }
            }
        }
    }


    private static void sesionUser() {
        int eleccion;
        
        do {
            System.out.println("\nBienvenido a la sesión de usuario. ¿Qué desea realizar?");
            System.out.println(" 0. Salir");
            System.out.println(" 1. Realizar un pedido");
            System.out.println(" 2. Eliminar un pedido");
            eleccion  = seleccionInt(0, 2);
            
            switch (eleccion) {
                case 0:
                    System.out.println("\n Cerrando la sesión...");
                    break;
                
                case 1:
                    realizarPedido();
                    break;
                
                case 2:
                    eliminarPedido();
                    break;
            }
        } while (eleccion > 0);
    }

    private static void realizarPedido() {
        ArrayList<Fursuit> fursuits = new ArrayList<Fursuit>();

        System.out.println("\nIntroduzca los datos del pedido:");

        System.out.println("\n Nombre:");
        String nombre = seleccionString(0);

        System.out.println("\n Dirección:");
        String direccion = seleccionString(0);

        System.out.println("\n Método de pago:");
        EnumMetodoPago metodoPago = seleccionMetodoPago();

        System.out.println("\nRellena el siguiente formulario sobre tu/s fursuit/s.");
        System.out.println("\n ¿Quieres crear heads?:");
        boolean crearHeads = seleccionBoolean();
        if (crearHeads) {
            fursuits.addAll(crearFursuitHeads());
            System.out.println("\n ¿Quieres crear suits?:");
            boolean crearSuits = seleccionBoolean();
            if (crearSuits) {
                fursuits.addAll(crearFursuitSuits());
            }
        } else {
            System.out.println("\nIntroduzca los datos de tu suit:");
            fursuits.addAll(crearFursuitSuits());
        }

        pedidos.add(new Pedido(nombre, direccion, metodoPago, fursuits));
        System.out.println("Pedido realizado con éxito. :D");
    }
    private static ArrayList<FursuitHead> crearFursuitHeads() {
        ArrayList<FursuitHead> fursuitHeads = new ArrayList<FursuitHead>();
        boolean repetir;

        do {
            repetir = false;

            System.out.println("\n Escriba la raza:");
            String raza = seleccionString(0);

            System.out.println("\n Escriba el color principal:");
            String colorPrincipal = seleccionString(0);

            System.out.println("\n Escriba el color secundario:");
            String colorSecundario = seleccionString(0);

            System.out.println("\n Escriba el color del ojo izquierdo:");
            String colorOjoIzquierdo = seleccionString(0);
            
            System.out.println("\n Escriba el color del ojo derecho");
            String colorOjoDerecho = seleccionString(0);
            
            System.out.println("\n Ingrese el tamaño de las orejas:");
            EnumTamano tamanoOrejas = seleccionTamano();
            
            System.out.println("\n Ingrese el tamaño de los cuernos:");
            EnumTamano tamanoCuernos = seleccionTamano();

            System.out.println("\n ¿Quieres un ventilador?");
            boolean ventilador = seleccionBoolean();

            System.out.println("\n Comentario:");
            String comentario = seleccionString(0);
            
            System.out.println("¿Deseas agregar otro?");
            repetir = seleccionBoolean();

            FursuitHead fursuitHead = new FursuitHead(raza, colorPrincipal, colorSecundario, comentario, colorOjoDerecho, colorOjoIzquierdo, tamanoOrejas, tamanoCuernos, ventilador);
            fursuitHeads.add(fursuitHead);
        } while (repetir);

        return fursuitHeads;
    }

    private static ArrayList<FursuitSuit> crearFursuitSuits() {
        ArrayList<FursuitSuit> fursuitSuits = new ArrayList<FursuitSuit>();
        boolean repetir;

        do {
            repetir = false;

            System.out.println("\n Escriba la raza:");
            String raza = seleccionString(0);

            System.out.println("\n Escriba el color principal:");
            String colorPrincipal = seleccionString(0);

            System.out.println("\n Escriba el color secundario:");
            String colorSecundario = seleccionString(0);

            System.out.println("\n ¿Tiene garras?:");
            boolean garras = seleccionBoolean();

            System.out.println("\n Elija el tipo de patas:");
            EnumTipoPatas tipoPatas = seleccionTipoPatas();

            System.out.println("\n Escriba el color de los beans:");
            String colorBeands = seleccionString(0);

            System.out.println("\n Ingrese el tamaño de la cola:");
            EnumTamano tamanoCola = seleccionTamano();

            System.out.println("\n Comentario:");
            String comentario = seleccionString(0);
            
            System.out.println("¿Deseas agregar otro?");
            repetir = seleccionBoolean();

            FursuitSuit fursuitSuit = new FursuitSuit(raza, colorPrincipal, colorSecundario, comentario, garras, tipoPatas, colorBeands, tamanoCola);
            fursuitSuits.add(fursuitSuit);
        } while (repetir);

        return fursuitSuits;
    }

    private static void eliminarPedido() {
        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Actualmente no hay ningún pedido creado. No querrás uno tú, ¿verdad?");
        } else {
            HashMap<Integer, Integer> posicionesPedido = new HashMap<Integer, Integer>();
            int posicionPedido = 1;

            for (int i = 0; i < pedidos.size(); i++) {
                if (pedidos.get(i).getEstadoPedido() == "No empezado") {
                    posicionesPedido.put(posicionPedido, i);
                    posicionPedido++;
                }
            }

            if (posicionesPedido == null || posicionesPedido.isEmpty()) {
                System.out.println("Todos los pedidos disponibles ya se están creando o ya fueron creados.");
            } else {
                System.out.println("¿Qué pedido deseas eliminar?");
                System.out.println(" 0. Salir");
                for (int i = 1; i <= posicionesPedido.size(); i++) {
                    System.out.println(" " + i + ". ID Nº" + pedidos.get(posicionesPedido.get(i)).getId() + " [ Nombre cliente: " + pedidos.get(posicionesPedido.get(i)).getNombreCliente() + " ]");
                }
    
                int seleccionPedido = seleccionInt(0, posicionesPedido.size());
                if (seleccionPedido > 0) {
                    pedidos.remove(pedidos.get(posicionesPedido.get(seleccionPedido)));
                    System.out.println("\n Pedido eliminado con éxito.");
                }
            }
        }
    }


    private static void guardarDatos() {
        try {
            FileOutputStream fosp = new FileOutputStream("pedidos.dat");
            ObjectOutputStream oosp = new ObjectOutputStream(fosp);
            FileOutputStream fosm = new FileOutputStream("makers.dat");
            ObjectOutputStream oosm = new ObjectOutputStream(fosm);
    
            oosp.writeObject(pedidos);
            oosm.writeObject(makers);
    
            oosp.close();
            oosm.close();
        } catch (Exception e) {
            System.out.println("Ha habido un error al guardar los datos.");
        }
    }

    private static void cargarDatos() {
        try {
            FileInputStream fisp = new FileInputStream("pedidos.dat");
            ObjectInputStream oisp = new ObjectInputStream(fisp);
            FileInputStream fism = new FileInputStream("makers.dat");
            ObjectInputStream oism = new ObjectInputStream(fism);
            
            pedidos = (ArrayList<Pedido>) oisp.readObject();
            makers = (ArrayList<Maker>) oism.readObject();
            
            if (pedidos != null && !pedidos.isEmpty()) {
                int maxId = 0;
                for (Pedido pedido : pedidos) {
                    if (pedido.getId() > maxId) {
                        maxId = pedido.getId();
                    }
                }
                Pedido.setContadorId(maxId + 1);
            }

            oisp.close();
            oism.close();
        } catch (FileNotFoundException e) {
            System.out.println("Los datos de guardado no existen o no fueron encontrados.");
        } catch (Exception e) {
                System.out.println("Ha habido un error al cargar los datos.");
        }
    }
}
