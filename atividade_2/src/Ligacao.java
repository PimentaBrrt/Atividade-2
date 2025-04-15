public class Ligacao {

    private String destino;
    private double distancia;
    private double trafego;
    private int num_pedagios;

    public Ligacao(String destino) {
        this.destino = destino;
    }

    public Ligacao(String destino, double distancia, double trafego, int num_pedagios) {

        this.destino = destino;
            
        if (distancia > 0) {
            this.distancia = distancia;
        }
        else {
            throw new ExpressLineException("Erro 01: Distância entre cidades inválida.");
        }

        if (trafego >= 0 && trafego <= 2) {
            this.trafego = trafego;
        }
        else {
            throw new ExpressLineException("Erro 02: Indicador de tráfego inválido. (Deve ser entre 0 e 2).");
        }

        if (num_pedagios >= 0) {
            this.num_pedagios = num_pedagios;
        }
        else {
            throw new ExpressLineException("Erro 03: O número de pedágios deve ser 0 ou maior.");
        }

    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String nome) {
        this.destino = nome;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getTrafego() {
        return trafego;
    }

    public void setTrafego(double trafego) {
        this.trafego = trafego;
    }

    public int getPedagios() {
        return num_pedagios;
    }

    public void setPedagios(int num_pedagios) {
        this.num_pedagios = num_pedagios;
    }

    public double tempoEntrega() {
        return this.distancia * trafego + num_pedagios * 2;
    }

    @Override
    public String toString() {
        return "-> " + destino + " | Distância: " + distancia + " km | Tráfego: " + trafego +
         " | Pedágios: " + num_pedagios + " | Tempo: " + tempoEntrega() + " min";
    }

    @Override
    public boolean equals(Object obj) {
        return (obj != null) && (obj instanceof Ligacao) &&
        ((Ligacao) obj).getDestino().equals(destino);
    }

}
