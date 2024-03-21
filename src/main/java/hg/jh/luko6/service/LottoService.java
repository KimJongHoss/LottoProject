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



            for(String inputStr : inputArr ) { //각 회차별 score 추출
                for (String lottoStr : lottoArr) {// 각 번호별 맞으면 score에 +1

                    if (inputStr.equals(lottoStr)) {
                        score += 1;
                        continue;
                    }
                    }

            }

            if (score == 5) {
                log.info(score);
                log.info(lotto.getNo7());
                log.info(lotto);
                for(String inputStr : inputArr ) {
                    log.info(lotto.getNo7() + " for문 안에 들어옴: 2등보너스 번호");
                    log.info(inputStr + " for문 안에 들어옴: 입력한 번호");
                    if (inputStr.equals(lotto.getNo7())) {
                        log.info(lotto.getNo7() + "if문 안에 들어옴");
                        score += 10;
                    }
                }

            }

            if(score>10){
                log.info("2등");
                log.info("라운드" + lotto.getRound());
//                @Autowired
//                OutputLotto outputLotto
//                outputLotto.setWining( =lotto.getSecondWinning());
//                outputLotto.setWinner("2등");
//
//
//                List<OutputLotto> list =
//                lotto.getRound();
                log.info(score);


            }else
                if(score==6){
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
