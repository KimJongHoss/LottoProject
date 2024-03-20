package hg.jh.luko6.service;

import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.entity.Lotto;
import hg.jh.luko6.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LottoService {

    private final LottoRepository lottoRepository;

    public List<Lotto> LottoAll(InputLotto inputLotto){



        List<Lotto> lottoList = lottoRepository.findAll();

        return lottoList;

    }




}
