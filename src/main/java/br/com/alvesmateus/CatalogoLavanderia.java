package br.com.alvesmateus;

import java.util.HashMap;
import java.util.Map;

public class CatalogoLavanderia {
    private final Map<String, CicloLavagem> prototipos = new HashMap<>();

    public void registrarModelo(String chave, CicloLavagem prototipo) {
        prototipos.put(chave.toUpperCase(), prototipo);
    }

    public CicloLavagem criarAPartirDoModelo(String chave) {
        CicloLavagem prototipo = prototipos.get(chave.toUpperCase());
        if (prototipo == null) {
            throw new IllegalArgumentException("Modelo de lavagem não encontrado: " + chave);
        }
        return prototipo.clone();
    }
}
