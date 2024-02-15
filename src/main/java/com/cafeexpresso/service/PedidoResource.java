package com.cafeexpresso.service;

import com.cafeexpresso.pedido.Pedido;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    private static Map<Integer, Pedido> pedidos = new HashMap<>();
    private static int nextId = 1;

    @GET
    public List<Pedido> getPedidos() {
        return new ArrayList<>(pedidos.values());
    }

    @POST
    public Response addPedido(Pedido pedido) {
        pedido.setId(nextId++);// Garante que o ID do pedido seja mantido
        pedidos.put(pedido.getId(), pedido);
        return Response.ok(pedido).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePedido(@PathParam("id") int id, Pedido pedido) {
        if (pedidos.containsKey(id)) {
            pedido.setId(id);
            pedidos.put(id, pedido);
            return Response.ok("Pedido atualizado com sucesso").build();
        } else {
            throw new NotFoundException("Pedido não encontrado");
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePedido(@PathParam("id") int id) {
        if (pedidos.containsKey(id)) {
            pedidos.remove(id);
            return Response.ok("Pedido excluído com sucesso").build();

        } else {
            throw new NotFoundException("Pedido não encontrado");
        }
    }
}
