package hg.jh.luko6.service;

import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.entity.Lotto;
import hg.jh.luko6.entity.OutputLotto;
import hg.jh.luko6.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LottoService {

    private final LottoRepository lottoRepository;





    public List<OutputLotto> LottoAll(InputLotto inputLotto){



        List<Lotto> lottoList = lottoRepository.findAll();

        List<OutputLotto> outputLottoList = new ArrayList<>();

        String[] inputArr = new String[]{inputLotto.getNum1()+"",inputLotto.getNum2()+"",inputLotto.getNum3()+"",inputLotto.getNum4()+"",inputLotto.getNum5()+"",inputLotto.getNum6()+""};

        int score= 0;


        for(Lotto lotto : lottoList){

            OutputLotto outputLotto = new OutputLotto();


            String[] lottoArr = new String[]{lotto.getNo1(), lotto.getNo2(), lotto.getNo3(), lotto.getNo4(), lotto.getNo5(), lotto.getNo6()};

            score=0;


            for (String lottoStr : lottoArr) {// 각 번호별 맞으면 score에 +1
                for(String inputStr : inputArr ) { //각 회차별 score 추출

                    if (lottoStr.equals(inputStr)) {
                        score += 1;
                        continue;
                    }
                    }

            }

            //2등로직
            if (score == 5) {
                log.info(score);
                log.info(lotto.getNo7());
                log.info(lotto);
                for(String inputStr : inputArr ) {
                    log.info(lotto.getNo7() + " for문 안에 들어옴: 2등보너스 번호");
                    log.info(inputStr + " for문 안에 들어옴: 입력한 번호");
                    if (lotto.getNo7().equals(inputStr)) {
                        log.info(lotto.getNo7() + "if문 안에 들어옴");
                        score += 10;
                    }
                }

            }

            // 출력되야할 리스트에 넣는 부분.
            if(score>10){
                log.info("2등");
                log.info("라운드" + lotto.getRound());
                log.info(score);


                outputLotto.setRound(lotto.getRound());
                outputLotto.setWinning(lotto.getSecondWinning());
                outputLotto.setPlace("2등");

                outputLottoList.add(outputLotto);

            }else if(score==6){
                log.info("라운드" + lotto.getRound());
                log.info("1등");
                log.info(score);


                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getFirstWinning());
                    outputLotto.setPlace("1등");

                    outputLottoList.add(outputLotto);

            }else if(score==5){
                    log.info("라운드" + lotto.getRound());
                    log.info("3등");
                    log.info(score);


                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getThirdWinning());
                    outputLotto.setPlace("3등");

                    outputLottoList.add(outputLotto);

            }else if(score==4){
                log.info("라운드" + lotto.getRound());
                log.info("4등");
                log.info(score);


                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getFourthWinning());
                    outputLotto.setPlace("4등");

                    outputLottoList.add(outputLotto);


            }else if(score==3){
                log.info("라운드" + lotto.getRound());
                log.info("5등");
                log.info(score);


                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getFifthWinning());
                    outputLotto.setPlace("5등");

                    outputLottoList.add(outputLotto);

            }else{

            }


        }

        log.info("어디서ㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓㅓ잘모된거야?????????");

        log.info(outputLottoList);

        return outputLottoList;

    }

}
