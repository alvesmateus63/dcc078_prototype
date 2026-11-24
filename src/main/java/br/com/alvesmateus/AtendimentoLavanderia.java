package br.com.alvesmateus;

import java.util.ArrayList;
import java.util.List;

public class AtendimentoLavanderia {
    public List<String> processarPedidosExemplo() {
        CatalogoLavanderia catalogo = new CatalogoLavanderia();

        CicloLavagem delicadoBase = new CicloLavagem("Roupas Delicadas", 30, 45, true);
        CicloLavagem pesadaBase = new CicloLavagem("Edredons e Pesadas", 60, 90, true);
        pesadaBase.adicionarProduto("Higienizador Bactericida");

        catalogo.registrarModelo("DELICADO", delicadoBase);
        catalogo.registrarModelo("PESADA", pesadaBase);

        CicloLavagem pedidoClienteA = catalogo.criarAPartirDoModelo("DELICADO");
        pedidoClienteA.setNomePlano("Delicado - Cliente A");
        pedidoClienteA.adicionarProduto("Tira-manchas sem cloro");

        CicloLavagem pedidoClienteB = catalogo.criarAPartirDoModelo("PESADA");
        pedidoClienteB.setNomePlano("Pesada - Cliente B");
        pedidoClienteB.setTemperaturaAgua(40);

        List<String> resultados = new ArrayList<>();
        resultados.add("MOLDE ORIGINAL: " + delicadoBase.obterResumo());
        resultados.add("PEDIDO CLONADO A: " + pedidoClienteA.obterResumo());
        resultados.add("MOLDE ORIGINAL: " + pesadaBase.obterResumo());
        resultados.add("PEDIDO CLONADO B: " + pedidoClienteB.obterResumo());

        return resultados;
    }
}
