package com.pokemon.dto.tipo;

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
public class TipoFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String nome;
    private List<Integer> tipoOpostoStrongId;
    private List<Integer> TipoOpostoWeakId;

}