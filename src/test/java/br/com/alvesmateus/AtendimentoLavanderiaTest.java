package br.com.alvesmateus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AtendimentoLavanderiaTest {
    private CatalogoLavanderia catalogo;
    private CicloLavagem moldeDelicado;

    @BeforeEach
    void configurarCenario() {
        catalogo = new CatalogoLavanderia();
        moldeDelicado = new CicloLavagem("Roupas Delicadas", 30, 45, true);
        moldeDelicado.adicionarProduto("Sabão Neutro");

        catalogo.registrarModelo("DELICADO", moldeDelicado);
    }

    @Test
    @DisplayName("Deve clonar o ciclo criando uma nova instância com os mesmos dados")
    void deveClonarComMesmosDadosMasInstanciaDiferente() {
        CicloLavagem clone = catalogo.criarAPartirDoModelo("DELICADO");

        assertNotSame(moldeDelicado, clone, "O clone deve ser um objeto distinto na memória");
        assertEquals(moldeDelicado.obterResumo(), clone.obterResumo(), "Os dados iniciais devem ser idênticos");
    }

    @Test
    @DisplayName("Alterações no clone não devem afetar o molde original (Deep Copy)")
    void alteracoesNoCloneNaoDevemAfetarOriginal() {
        CicloLavagem pedidoCliente = catalogo.criarAPartirDoModelo("DELICADO");
        pedidoCliente.setNomePlano("Delicado VIP");
        pedidoCliente.setTemperaturaAgua(20);
        pedidoCliente.adicionarProduto("Tira-manchas sem cloro");

        String resumoOriginal = moldeDelicado.obterResumo();
        String resumoClone = pedidoCliente.obterResumo();

        assertTrue(resumoOriginal.contains("Plano: Roupas Delicadas"));
        assertTrue(resumoOriginal.contains("Temp: 30°C"));
        assertFalse(resumoOriginal.contains("Tira-manchas sem cloro"), "A lista do molde original não pode ser alterada");

        assertTrue(resumoClone.contains("Plano: Delicado VIP"));
        assertTrue(resumoClone.contains("Temp: 20°C"));
        assertTrue(resumoClone.contains("Tira-manchas sem cloro"));
    }

    @Test
    @DisplayName("Deve lançar exceção ao solicitar um modelo não cadastrado")
    void deveLancarExcecaoParaModeloInexistente() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> catalogo.criarAPartirDoModelo("LAVAGEM_ESPACIAL")
        );

        assertEquals("Modelo de lavagem não encontrado: LAVAGEM_ESPACIAL", exception.getMessage());
    }

    @Test
    @DisplayName("Deve processar o fluxo completo de atendimento retornando os resumos corretos")
    void deveProcessarPedidosExemploCorretamente() {
        AtendimentoLavanderia atendimento = new AtendimentoLavanderia();
        List<String> resultados = atendimento.processarPedidosExemplo();

        assertEquals(4, resultados.size());
        assertEquals("MOLDE ORIGINAL: Plano: Roupas Delicadas | Temp: 30°C | Duração: 45 min | Amaciante: Sim | Extras: Nenhum", resultados.get(0));
        assertEquals("PEDIDO CLONADO A: Plano: Delicado - Cliente A | Temp: 30°C | Duração: 45 min | Amaciante: Sim | Extras: Tira-manchas sem cloro", resultados.get(1));
    }
}
