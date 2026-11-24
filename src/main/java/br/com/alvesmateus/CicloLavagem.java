package br.com.alvesmateus;

import java.util.ArrayList;
import java.util.List;

public class CicloLavagem implements Cloneable {
    private String nomePlano;
    private int temperaturaAgua;
    private int tempoMinutos;
    private boolean usarAmaciante;
    private List<String> produtosAdicionais;

    public CicloLavagem(String nomePlano, int temperaturaAgua, int tempoMinutos, boolean usarAmaciante) {
        this.nomePlano = nomePlano;
        this.temperaturaAgua = temperaturaAgua;
        this.tempoMinutos = tempoMinutos;
        this.usarAmaciante = usarAmaciante;
        this.produtosAdicionais = new ArrayList<>();
    }

    @Override
    public CicloLavagem clone() {
        try {
            CicloLavagem copia = (CicloLavagem) super.clone();

            copia.produtosAdicionais = new ArrayList<>(this.produtosAdicionais);

            return copia;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("A classe implementa Cloneable, erro inesperado.", e);
        }
    }

    public void adicionarProduto(String produto) {
        this.produtosAdicionais.add(produto);
    }

    public void setTemperaturaAgua(int temperaturaAgua) {
        this.temperaturaAgua = temperaturaAgua;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String obterResumo() {
        return String.format(
                "Plano: %s | Temp: %d°C | Duração: %d min | Amaciante: %s | Extras: %s",
                nomePlano,
                temperaturaAgua,
                tempoMinutos,
                usarAmaciante ? "Sim" : "Não",
                produtosAdicionais.isEmpty() ? "Nenhum" : String.join(", ", produtosAdicionais)
        );
    }
}
