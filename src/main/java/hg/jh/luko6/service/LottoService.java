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

        String[] inputArr = new String[]{inputLotto.getNum1()+"",inputLotto.getNum2()+"",inputLotto.getNum3()+"",inputLotto.getNum4()+"",inputLotto.getNum5()+"",inputLotto.getNum6()+""};

        int score= 0;



        for(Lotto lotto : lottoList){

            String[] lottoArr = new String[]{lotto.getNo1(), lotto.getNo2(), lotto.getNo3(), lotto.getNo4(), lotto.getNo5(), lotto.getNo6()};

            score=0;



            for(String inputStr : inputArr ){
                for(String lottoStr : lottoArr){

                    if(inputStr.equals(lottoStr)){
                        score+=1;
                        continue;

                    }
                }

            }

            if(score>10){
                log.info("2등");
                log.info("라운드" + lotto.getRound());

                log.info(score);


            }else if(score==6){
                log.info("라운드" + lotto.getRound());

                log.info("1등");
                log.info(score);


            }else if(score==5){
                log.info("라운드" + lotto.getRound());

                log.info("3등");
                log.info(score);


            }else if(score==4){
                log.info("라운드" + lotto.getRound());
                log.info("4등");
                log.info(score);

            }else if(score==3){
                log.info("라운드" + lotto.getRound());
                log.info("5등");
                log.info(score);

            }else{

            }



        }

        return lottoList;

    }




}
