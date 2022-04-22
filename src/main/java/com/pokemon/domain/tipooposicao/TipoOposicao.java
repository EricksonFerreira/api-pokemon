package com.pokemon.domain.tipooposicao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import com.pokemon.domain.tipo.Tipo;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TipoOposicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    private String nomeOposicao;

    @ManyToOne
    @JoinColumn(name = "tipo_representacao")
    private Tipo tipo_representacao;

    @ManyToOne
    @JoinColumn(name = "tipo_oposto")
    private Tipo tipo_oposto;
}

