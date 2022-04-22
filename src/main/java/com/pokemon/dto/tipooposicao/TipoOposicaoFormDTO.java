package com.pokemon.dto.tipooposicao;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoOposicaoFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String nomeOposicao;

    private List<Integer> tipoRepresentacaoId;
    private List<Integer> TipoOpostoId;
}