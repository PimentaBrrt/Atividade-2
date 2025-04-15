public class Cidade {
    
    private String nome;
    private ListaDupla<Ligacao> ligacoes = new ListaDupla<>();

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ListaDupla<Ligacao> getLigacoes() {
        return ligacoes;
    }

    public void addLigacao(Ligacao ligacao) {
        ligacoes.inserir(ligacao);
    }

    public void removeLigacao(Ligacao ligacao) {
        ligacoes.remover(ligacao);
    }

    @Override
    public String toString() {
        String ligs = "";
        for (No<Ligacao> l = ligacoes.getInicio(); l != null ; l = l.getProximo()) {
            ligs += l.getDado() + "\n";
        }
        return "Cidade: " + nome + "\n" + ligs;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && (obj instanceof Cidade) && ((Cidade) obj).getNome().equals(nome);
    }

}

