package com.pokemon.dto.tipo;

import java.io.Serializable;
import java.util.List;

import com.pokemon.domain.tipooposicao.TipoOposicao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoTipoOposicaoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer codigo;
    private String nome;
    private List<TipoOposicao> tipoOposto;

}