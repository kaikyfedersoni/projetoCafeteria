    package com.example.backend.model;

    import jakarta.persistence.*;

    import java.util.Date;

    @Entity
    public class Pedido {
        @Id @GeneratedValue
        private Long id;

        private Date dataPedido = new Date();

        private double valorTotal;

        private boolean isPago;

        @OneToOne
        private Atendente atendente;

        public Pedido() {
        }

        public Pedido(Long id, Date dataPedido, double valorTotal, boolean isPago, Atendente atendente) {
            this.id = id;
            this.dataPedido = dataPedido;
            this.valorTotal = valorTotal;
            this.isPago = isPago;
            this.atendente = atendente;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Date getDataPedido() {
            return dataPedido;
        }

        public void setDataPedido(Date dataPedido) {
            this.dataPedido = dataPedido;
        }

        public double getValorTotal() {
            return valorTotal;
        }

        public void setValorTotal(double valorTotal) {
            this.valorTotal = valorTotal;
        }

        public boolean isPago() {
            return isPago;
        }

        public void setPago(boolean pago) {
            isPago = pago;
        }

        public Atendente getAtendente() {
            return atendente;
        }

        public void setAtendente(Atendente atendente) {
            this.atendente = atendente;
        }
    }
