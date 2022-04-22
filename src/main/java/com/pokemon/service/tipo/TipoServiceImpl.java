package com.pokemon.service.tipo;

import com.pokemon.convert.tipo.TipoConvert;
import com.pokemon.domain.tipo.Tipo;
import com.pokemon.domain.tipooposicao.TipoOposicao;
import com.pokemon.dto.tipo.TipoDTO;
import com.pokemon.dto.tipo.TipoFormDTO;
import com.pokemon.dto.tipo.TipoTipoOposicaoDTO;
import com.pokemon.repository.tipo.TipoRepository;
import com.pokemon.repository.tipooposicao.TipoOposicaoRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class TipoServiceImpl implements TipoService {

    private final TipoRepository tipoRepository;
    private final TipoOposicaoRepository tipoOposicaoRepository;

    @Override
    public List<TipoDTO> listarTipos() {
        List<Tipo> tipos = tipoRepository.findAll();
        return TipoConvert.tipoDomainListToDTOlist(tipos);
    }

    @Override
    public TipoDTO buscarTipoPorCodigo(Integer codigo) throws Exception{
        Optional<Tipo> tipoOpt = tipoRepository.findById(codigo);
        if(!tipoOpt.isPresent()){
        }
        return TipoConvert.tipoDomainToDTO(tipoOpt.get());
    }

    @Override
    public TipoTipoOposicaoDTO adicionaTipo(TipoFormDTO tipoFormDTO) throws Exception{
        // Verificação de tipos de oposicao
        Stream<Integer> tiposRepre = tipoFormDTO.getTipoOpostoStrongId().stream().filter(codigo -> {
            Optional<Tipo> tipoOpt = tipoRepository.findById(codigo);
            if(!tipoOpt.isPresent()){
                return false;
            }

            return true;
        });
        if(tiposRepre.count() != tipoFormDTO.getTipoOpostoStrongId().size()){
            throw new Exception("Existem tipos de representações que não estão salvos no sistema!");
        }
        
        // Verificação de tipos de representacoes
        Stream<Integer> tiposOpo = tipoFormDTO.getTipoOpostoWeakId().stream().filter(codigo -> {
            Optional<Tipo> tipoOpt = tipoRepository.findById(codigo);
            if(!tipoOpt.isPresent()){
                return false;
            }

            return true;
        });
        if(tiposOpo.count() != tipoFormDTO.getTipoOpostoWeakId().size()){
            throw new Exception("Existem tipos de representações que não estão salvos no sistema!");
        }

        Tipo entity = TipoConvert.tipoFormToTipoEntity(tipoFormDTO);
        Tipo tipoSave = tipoRepository.save(entity);

        tipoFormDTO.getTipoOpostoStrongId().stream().map(codigo -> {
            Optional<Tipo> tipoOp = tipoRepository.findById(codigo);
            TipoOposicao tipoOposicao = TipoOposicao.builder()
                                                    .nomeOposicao("s") // s -> strong
                                                    .tipo_representacao(tipoSave)
                                                    .tipo_oposto(tipoOp.get())
                                                    .build();

            return tipoOposicaoRepository.save(tipoOposicao);
        });
        tipoFormDTO.getTipoOpostoWeakId().stream().map(codigo -> {
            Optional<Tipo> tipoOp = tipoRepository.findById(codigo);
            TipoOposicao tipoOposicao = TipoOposicao.builder()
                                                    .nomeOposicao("w") // w -> weak
                                                    .tipo_representacao(tipoSave)
                                                    .tipo_oposto(tipoOp.get())
                                                    .build();
            return tipoOposicaoRepository.save(tipoOposicao);
        });

        Optional<List<TipoOposicao>> tipoOp = tipoOposicaoRepository.findByTipo_representacaoOptional(tipoSave);
        List<TipoOposicao> tiposOposicoes = null;
        if(tipoOp.isPresent()){
            tiposOposicoes = tipoOp.get();
        }
        return TipoConvert.tipoDomainWithTipoOposicaoToDTO(entity,tiposOposicoes);
    }

    @Override
    public TipoDTO editaTipo(Integer codigo, TipoFormDTO tipoForm) throws Exception {
        Optional<Tipo> tipoOpt = tipoRepository.findById(codigo);
        if(!tipoOpt.isPresent()){
            throw new Exception("Tipo não encotrada");
        }

        Tipo entity = tipoOpt.get();
        entity.setNome(tipoForm.getNome());
		tipoRepository.save(entity);
        return TipoConvert.tipoDomainToDTO(entity);
    }

     @Override
    public void deletaTipo(Integer codigo) throws Exception {

        Optional<Tipo> tipoOpt = tipoRepository.findById(codigo);
        if(!tipoOpt.isPresent()){
            throw new Exception("Tipo não encontrada");
        }

        tipoRepository.delete(tipoOpt.get());
    }
}
