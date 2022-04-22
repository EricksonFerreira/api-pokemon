package com.pokemon.service.tipo;

import com.pokemon.dto.tipo.TipoDTO;
import com.pokemon.dto.tipo.TipoFormDTO;
import com.pokemon.dto.tipo.TipoTipoOposicaoDTO;

import java.util.List;

public interface TipoService {
    public List<TipoDTO> listarTipos();
    public TipoDTO buscarTipoPorCodigo(Integer codigo) throws Exception;

    public TipoTipoOposicaoDTO adicionaTipo(TipoFormDTO tipoFormDTO) throws Exception;
    public TipoDTO editaTipo(Integer codigo,TipoFormDTO tipoFormDTO) throws Exception;

    public void deletaTipo(Integer codigo) throws Exception;
}
