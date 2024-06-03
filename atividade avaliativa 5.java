import java.util.ArrayList;
import java.util.List;

// Classe Cliente
class Cliente {
    private String nome;
    private int id;

    public Cliente(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public void procurarProduto(Produto produto) {
        System.out.println("Cliente " + nome + " está procurando o produto: " + produto.getNome());
    }

    public void pedirAjuda(Funcionario funcionario) {
        System.out.println("Cliente " + nome + " pediu ajuda ao funcionário " + funcionario.getNome());
        funcionario.ajudarCliente(this);
    }

    public boolean decidirCompra() {
        // Lógica para decidir compra (aqui assumimos que sempre decide comprar)
        return true;
    }

    public void pagarProduto(Caixa caixa, Produto produto) {
        if (decidirCompra()) {
            caixa.processarPagamento(this, produto);
        } else {
            System.out.println("Cliente " + nome + " decidiu não comprar o produto.");
        }
    }

    public String getNome() {
        return nome;
    }
}

// Classe Produto
class Produto {
    private String nome;
    private int id;
    private double preco;
    private boolean disponibilidade;

    public Produto(String nome, int id, double preco, boolean disponibilidade) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.disponibilidade = disponibilidade;
    }

    public boolean getDisponibilidade() {
        return disponibilidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

// Classe Funcionario
class Funcionario {
    private String nome;
    private int id;

    public Funcionario(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public void ajudarCliente(Cliente cliente) {
        System.out.println("Funcionário " + nome + " está ajudando o cliente " + cliente.getNome());
    }

    public String getNome() {
        return nome;
    }
}

// Classe Loja
class Loja {
    private String nome;
    private List<Produto> produtos;
    private List<Funcionario> funcionarios;

    public Loja(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public void receberCliente(Cliente cliente) {
        System.out.println("Loja " + nome + " recebeu o cliente " + cliente.getNome());
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
}

// Classe Caixa
class Caixa {
    private int id;

    public Caixa(int id) {
        this.id = id;
    }

    public void processarPagamento(Cliente cliente, Produto produto) {
        System.out.println("Caixa " + id + " processou o pagamento do produto " + produto.getNome() + " para o cliente " + cliente.getNome());
    }
}

// Classe Principal para executar o código
public class Main {
    public static void main(String[] args) {
        // Criando objetos de exemplo
        Cliente cliente = new Cliente("João", 1);
        Produto produto = new Produto("Notebook", 101, 3500.00, true);
        Funcionario funcionario = new Funcionario("Maria", 201);
        Loja loja = new Loja("Loja de Tecnologia");
        Caixa caixa = new Caixa(301);

        // Adicionando produtos e funcionários à loja
        loja.adicionarProduto(produto);
        loja.adicionarFuncionario(funcionario);

        // Simulando o processo de atendimento
        loja.receberCliente(cliente);
        cliente.procurarProduto(produto);

        if (!produto.getDisponibilidade()) {
            cliente.pedirAjuda(funcionario);
        }

        cliente.pagarProduto(caixa, produto);
        System.out.println("Cliente saiu da loja.");
    }
}