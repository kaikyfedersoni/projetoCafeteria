    package com.example.prjCafeteria.model;

    import jakarta.persistence.*;

    import java.time.LocalDate;
    import java.util.List;

    @Entity
    public class Pedido {
        @Id @GeneratedValue
        private Long id;

        private String comprador;

        private LocalDate dataPedido;

        private double valorTotal;

        private boolean pago;

        @OneToMany
        private List<Produto_Pedido> produtoPedidos;

        public Pedido() {
        }

        public Pedido(Long id, String comprador, LocalDate dataPedido, double valorTotal, boolean pago) {
            this.id = id;
            this.comprador = comprador;
            this.dataPedido = dataPedido;
            this.valorTotal = valorTotal;
            this.pago = pago;
        }

        public Pedido(Long id, String comprador, LocalDate dataPedido, double valorTotal, boolean pago, Funcionario atendente) {
            this.id = id;
            this.comprador = comprador;
            this.dataPedido = dataPedido;
            this.valorTotal = valorTotal;
            this.pago = pago;
            //this.funcionario = funcionario;
        }

        public String getComprador() {
            return comprador;
        }

        public void setComprador(String comprador) {
            this.comprador = comprador;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDate getDataPedido() {
            return dataPedido;
        }

        public void setDataPedido(){
            this.dataPedido = LocalDate.now();
        }

        public void setDataPedido(LocalDate dataPedido) {
            this.dataPedido = dataPedido;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }

        public boolean isPago() {
            return pago;
        }

        public void setPago(boolean pago) {
            this.pago = pago;
        }

        /*public Atendente getAtendente() {
            return atendente;
        }

        public void setAtendente(Atendente atendente) {
            this.atendente = atendente;
        }*/


    }
