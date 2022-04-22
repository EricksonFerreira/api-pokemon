package com.pokemon.repository.tipooposicao;

import java.util.List;
import java.util.Optional;

import com.pokemon.domain.tipo.Tipo;
import com.pokemon.domain.tipooposicao.TipoOposicao;

import org.springframework.data.jpa.repository.JpaRepository;


public interface TipoOposicaoRepository extends JpaRepository<TipoOposicao, Integer> {
    Optional<List<TipoOposicao>> findByTipo_representacaoOptional(Tipo tipo_representacao);


}
