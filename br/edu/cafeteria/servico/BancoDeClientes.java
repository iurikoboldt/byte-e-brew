package br.edu.cafeteria.servico;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.modelo.ClienteStandard;
import br.edu.cafeteria.modelo.ClienteVIP;

public class BancoDeClientes implements Banco<Cliente> {

    private final List<Cliente> clientes = new ArrayList<>();
    private static final String ARQUIVO = "data/clientes.json";

    public BancoDeClientes() {
        carregar();
    }

    // CREATE
    @Override
    public boolean adicionar(Cliente cliente) {
        if (cliente == null || buscarPorCpf(cliente.getCpf()) != null) {
            return false;
        }

        clientes.add(cliente);
        salvar();
        return true;
    }

    // READ
    @Override
    public Cliente buscar(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        return buscarPorCpf(cliente.getCpf());
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    // DELETE
    @Override
    public boolean remover(Cliente cliente) {
        if (cliente == null) {
            return false;
        }

        boolean removido = clientes.removeIf(
                c -> c.getCpf().equals(cliente.getCpf())
        );

        if (removido) {
            salvar();
        }

        return removido;
    }

    // UPDATE
    public boolean atualizarXP(String cpf, double valor) {
        Cliente cliente = buscarPorCpf(cpf);

        if (cliente == null) {
            return false;
        }

        cliente.adicionarXP(valor);
        salvar();
        return true;
    }

    // LISTAR
    @Override
    public List<Cliente> listar() {
        return new ArrayList<>(clientes);
    }

    public void mostrarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(
                    "Nome: " + cliente.getNome() +
                            " | CPF: " + cliente.getCpf() +
                            " | XP: " + cliente.getXp() +
                            " | Tipo: " + cliente.getClass().getSimpleName()
            );
        }
    }

    // SALVAR JSON
    private void salvar() {
        try {
            Path caminho = Path.of(ARQUIVO);
            caminho.getParent().toFile().mkdirs();

            StringBuilder json = new StringBuilder();
            json.append("[\n");

            for (int i = 0; i < clientes.size(); i++) {
                Cliente cliente = clientes.get(i);

                json.append("  {");
                json.append("\"tipo\":\"").append(cliente.getClass().getSimpleName()).append("\",");
                json.append("\"nome\":\"").append(cliente.getNome()).append("\",");
                json.append("\"cpf\":\"").append(cliente.getCpf()).append("\",");
                json.append("\"xp\":").append(cliente.getXp());
                json.append("}");

                if (i < clientes.size() - 1) {
                    json.append(",");
                }

                json.append("\n");
            }

            json.append("]");

            Files.writeString(
                    caminho,
                    json.toString(),
                    StandardCharsets.UTF_8
            );

        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes.");
        }
    }

    // CARREGAR JSON
    private void carregar() {
        try {
            Path caminho = Path.of(ARQUIVO);

            if (!Files.exists(caminho)) {
                return;
            }

            String conteudo = Files.readString(
                    caminho,
                    StandardCharsets.UTF_8
            );

            clientes.clear();

            conteudo = conteudo.replace("[", "")
                    .replace("]", "")
                    .trim();

            if (conteudo.isEmpty()) {
                return;
            }

            String[] objetos = conteudo.split("\\},");

            for (String obj : objetos) {
                obj = obj.replace("{", "")
                        .replace("}", "")
                        .trim();

                String[] campos = obj.split(",");

                String tipo = "";
                String nome = "";
                String cpf = "";
                double xp = 0;

                for (String campo : campos) {
                    String[] partes = campo.split(":");

                    String chave = partes[0]
                            .replace("\"", "")
                            .trim();

                    String valor = partes[1]
                            .replace("\"", "")
                            .trim();

                    switch (chave) {
                        case "tipo":
                            tipo = valor;
                            break;
                        case "nome":
                            nome = valor;
                            break;
                        case "cpf":
                            cpf = valor;
                            break;
                        case "xp":
                            xp = Double.parseDouble(valor);
                            break;
                    }
                }

                Cliente cliente;

                if (tipo.equals("ClienteVip")) {
                    cliente = new ClienteVIP(nome, cpf, xp);
                } else {
                    cliente = new ClienteStandard(nome, cpf, xp);
                }

                clientes.add(cliente);
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes.");
        }
    }
}
