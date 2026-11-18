package br.com.starlog.main;

import br.com.starlog.exception.CapacidadeExcedidaException;
import br.com.starlog.model.Carga;
import br.com.starlog.model.ModuloCarga;
import br.com.starlog.model.BaseLancamento;
import java.util.HashSet;
import java.util.Set;

public class MainResgate {
    public static void main(String[] args) {
        System.out.println("=== INICIO DO RESGATE STARLOG-ZERO ===");

        // ATAQUE 1: Injeção de carga com dados espúrios
        try {
            Carga fantasma = new Carga("    ", "CRIOGENICA", 10.0, 500.0);
            System.out.println("FALHA: Aceitou codigo vazio!");
        } catch (IllegalArgumentException e) {
            System.out.println("SUCESSO ATAQUE 1 (Fail-Fast ativado): " + e.getMessage());
        }

        // ATAQUE 2: Clone das sombras (integridade do HashSet)
        Set<Carga> esteiraTriagem = new HashSet<>();
        Carga c1 = new Carga("ORB-999", "ALIMENTOS", 10.0, 100.0);
        Carga c1Clone = new Carga("ORB-999", "DADOS_ALTERADOS", 999.0, 8000.0);

        esteiraTriagem.add(c1);
        esteiraTriagem.add(c1Clone);

        if (esteiraTriagem.size() == 1) {
            System.out.println("SUCESSO ATAQUE 2 (Hash imune a clones): Tamanho do Set = 1");
        } else {
            System.out.println("FALHA: O clone entrou no Set! Tamanho = " + esteiraTriagem.size());
        }

        // ATAQUE 3: Ruptura do limite (Checked Exception)
        ModuloCarga moduloTeste = new ModuloCarga("MOD-TESTE", 2);
        try {
            moduloTeste.carregarCarga(new Carga("C-01", "GERAL", 5.0, 50.0));
            moduloTeste.carregarCarga(new Carga("C-02", "GERAL", 5.0, 50.0));
            moduloTeste.carregarCarga(new Carga("C-03", "GERAL", 5.0, 50.0));
            System.out.println("FALHA: Deixou ultrapassar a capacidade fisica!");
        } catch (CapacidadeExcedidaException e) {
            System.out.println("SUCESSO ATAQUE 3 (Trava de Limite operante): " + e.getMessage());
        }

        // ATAQUE 4: Processamento analítico funcional (Streams API)
        double seguroCalculado = moduloTeste.calcularSeguroTotal();
        System.out.println("SUCESSO ATAQUE 4 (Streams Calculado): R$ " + seguroCalculado);

        // Demonstração complementar do cadastro e busca O(1)
        BaseLancamento base = new BaseLancamento();
        base.cadastrarModulo(moduloTeste);
        System.out.println("Busca do modulo MOD-TESTE: "
                + (base.buscarModulo("MOD-TESTE") != null ? "encontrado" : "nao encontrado"));

        System.out.println("=== FIM DOS TESTES ===");
    }
}
