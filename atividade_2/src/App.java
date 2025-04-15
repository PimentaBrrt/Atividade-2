import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class App {

    private static ListaDupla<Cidade> listaPrin = new ListaDupla<>();

    public static void main(String[] args) throws Exception {
        
        String menu = "1. Cadastrar cidade\n2. Cadastrar ligação direta\n3. Listar todas cidades e ligações\n4. Verificar ligação\n5. Exibir ligações em tempo limite x\n6. Finalizar";
        int opcao;
        
            while(true) {

                try {
                    opcao = parseInt(showInputDialog(menu));
                    switch(opcao) {
                        case 1:
                            listaPrin.inserir(cadastrarCidade());
                            break;
                        case 2:
                            cadastrarLigacao();
                            break;
                        case 3:
                            listar();
                            break;
                        case 4:
                            verificar();
                            break;
                        case 5:
                            ligacoesTempo();
                            break;
                        case 6:
                            showMessageDialog(null, "Programa finalizado.");
                            return;
                        default:
                            showMessageDialog(null, "Comando inválido.");
                    }
                } catch (Exception e) {
                    if (e.getMessage() == "Cannot parse null string") {
                        return;
                    }
                    showMessageDialog(null, e.getMessage());
                }

            }
        }

    private static Cidade cadastrarCidade() {

        String nome = showInputDialog(null, "Insira o nome da cidade a ser inserida");
        return new Cidade(nome);

    }

    private static void cadastrarLigacao() {

        String nome = showInputDialog(null, "Insira o nome da cidade que terá uma nova ligação adicionada");
        if (listaPrin.pesquisar(new Cidade(nome)) != null) {
            String destino = showInputDialog(null, "Insira a cidade de destino");
            double distancia = parseDouble(showInputDialog(null, "Insira a distância até a cidade de destino em quilômetros"));
            double trafego = parseDouble(showInputDialog(null, "Insira o indicador de tráfego (valor de 0 a 2)"));
            while (trafego < 0 || trafego > 2) {
                trafego = parseDouble(showInputDialog(null, "Insira um indicador de tráfego válido (valor de 0 a 2)"));
            }
            int pedagios = parseInt(showInputDialog(null, "Insira o número de pedágios entre as cidades"));

            listaPrin.pesquisar(new Cidade(nome)).getDado().addLigacao(new Ligacao(destino, distancia, trafego, pedagios));
            
        }
        else {
            showMessageDialog(null, "Cidade não cadastrada.");
        }

    }

    private static void listar() {
        int cont = 1;
        for (No<Cidade> cidade = listaPrin.getInicio(); cidade != null; cidade = cidade.getProximo()) {
            showMessageDialog(null, cont + "- " + cidade.getDado());
            cont++;
        }
    }

    private static void verificar() {

        String origem = showInputDialog("Insira a cidade de origem");
        if (listaPrin.pesquisar(new Cidade(origem)) == null) {
            showMessageDialog(null, "Cidade de origem não cadastrada.");
            return;
        }
        String destino = showInputDialog("Insira a cidade de destino");

        if (listaPrin.pesquisar(new Cidade(origem)).getDado().getLigacoes().pesquisar(new Ligacao(destino)) == null) {
            showMessageDialog(null, "Não existe ligação direta entre essas cidades.");
        }
        else {
            showMessageDialog(null, "Existe ligação direta entre essas cidades. \nTempo estimado: " +
            listaPrin.pesquisar(new Cidade(origem)).getDado().getLigacoes().pesquisar(new Ligacao(destino)).getDado().tempoEntrega() +
            " minutos");
        }
        
    }

    private static void ligacoesTempo() {

        double tempo = parseDouble(showInputDialog("Insira o tempo limite que deseja checar"));
        String resp = "";

        for (No<Cidade> cidade = listaPrin.getInicio(); cidade != null; cidade = cidade.getProximo()) {
            for (No<Ligacao> l = cidade.getDado().getLigacoes().getInicio(); l != null; l = l.getProximo()) {
                if (l.getDado().tempoEntrega() <= tempo) {
                    resp += cidade.getDado().getNome() + " -> " + l.getDado().getDestino() + " (" + l.getDado().tempoEntrega() + " min)\n";
                }
            }
        }

        if (resp.equals("")) {
            showMessageDialog(null, "Nenhuma ligação está dentro do limite de " + tempo + " minutos.");
            return;
        }
        showMessageDialog(null, "Entregas possíveis com tempo <= " + tempo + " minutos:\n" + resp);

    }

}