import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // ===== Teste Dormente =====
        Dormente dormente = new Dormente("DM-001", "QR-001", 1L, 1L,
                "Eucalipto", "A", 2800.0, "ATIVO");

        // Sem banco, ninguém gera o id. No teste, colocamos na mão:
        dormente.setId(10L);

        System.out.println("=== DORMENTE ===");
        System.out.println("Id: " + dormente.getId());
        System.out.println("Número de série: " + dormente.getNumeroSerie());
        System.out.println("Madeira: " + dormente.getTipoMadeira() + " - classe " + dormente.getClasseMadeira());
        System.out.println("Comprimento: " + dormente.getComprimento());
        System.out.println("Status: " + dormente.getStatus());
        System.out.println("Criado em: " + dormente.getCriadoEm());

        // ===== Teste Inspecao =====
        Inspecao inspecao = new Inspecao(dormente.getId(), 5L,
                LocalDate.of(2026, 10, 15), "APROVADO");

        System.out.println();
        System.out.println("=== INSPEÇÃO ===");
        System.out.println("Dormente inspecionado: " + inspecao.getDormenteId());
        System.out.println("Inspetor (usuário): " + inspecao.getUsuarioId());
        System.out.println("Data: " + inspecao.getDataInspecao());
        System.out.println("Resultado: " + inspecao.getResultadoGeral());

        // ===== Teste de setter =====
        dormente.setStatus("EM MANUTENÇÃO");
        System.out.println();
        System.out.println("Status atualizado: " + dormente.getStatus());
    }
}