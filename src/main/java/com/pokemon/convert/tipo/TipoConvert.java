package com.pokemon.convert.tipo;

import com.pokemon.domain.tipo.Tipo;
import com.pokemon.domain.tipooposicao.TipoOposicao;
import com.pokemon.dto.tipo.TipoDTO;
import com.pokemon.dto.tipo.TipoFormDTO;
import com.pokemon.dto.tipo.TipoTipoOposicaoDTO;

import java.util.List;
import java.util.stream.Collectors;

public class TipoConvert {
  	private TipoConvert() {
	}
	
	public static List<TipoDTO> tipoDomainListToDTOlist(List<Tipo> domainList) {
			return domainList.stream().map(TipoConvert::tipoDomainToDTO).collect(Collectors.toList());
	}

	public static TipoDTO tipoDomainToDTO(Tipo domain) {
		return TipoDTO.builder().codigo(domain.getId()).nome(domain.getNome()).build();
	}
	
	public static Tipo tipoFormToTipoEntity(TipoFormDTO tipoForm) {
		return Tipo.builder().nome(tipoForm.getNome()).build();
	}

	public static TipoTipoOposicaoDTO tipoDomainWithTipoOposicaoToDTO(Tipo domain,List<TipoOposicao> tipoOposicao) {
		
		return TipoTipoOposicaoDTO.builder().codigo(domain.getId())
											.nome(domain.getNome())
											.tipoOposto(tipoOposicao)
											.build();
		// return null;
	}

}
