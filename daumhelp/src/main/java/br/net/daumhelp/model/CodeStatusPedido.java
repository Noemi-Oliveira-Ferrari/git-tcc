package br.net.daumhelp.model;

import java.util.HashMap;
import java.util.Map;

public enum CodeStatusPedido {
	
	SOLICITADO(1),
	ORCADO(2),
	ACEITO(3),
	REJEITADO(4),
	CANCELADO_CLIENTE(5),
	CANCELADO_PROFISSIONAL(6),
	CONCLUIDO(7);
	

    private int value;
    private static Map map = new HashMap<>();

    private CodeStatusPedido(int value) {
        this.value = value;
    }

    static {
        for (CodeStatusPedido status : CodeStatusPedido.values()) {
            map.put(status.value, status);
        }
    }

    public static CodeStatusPedido valueOf(int status) {
        return (CodeStatusPedido) map.get(status);
    }

    public int getValue() {
        return value;
    }
}
