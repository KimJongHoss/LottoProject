package hg.jh.luko6.service;

import hg.jh.luko6.entity.InputLotto;
import hg.jh.luko6.entity.Lotto;
import hg.jh.luko6.entity.OutputLotto;
import hg.jh.luko6.entity.Percentage;
import hg.jh.luko6.repository.LottoRepository;
import hg.jh.luko6.repository.PercentageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class LottoService {

    private final LottoRepository lottoRepository;
    private final PercentageRepository percentageRepository;

    public void addPercentage(Long totalWinning){

        Percentage percentage = new Percentage();

        percentage.setTotalWinning(totalWinning);

        percentageRepository.save(percentage);

    }

    public Double calculatePercentage(Long totalWinning){

        Long Ranked= percentageRepository.findByTotalWinning(totalWinning);

        Long totalCount =percentageRepository.count();

        return Ranked.doubleValue()/totalCount.doubleValue();

    }



    public List<OutputLotto> LottoAll(InputLotto inputLotto){



        List<Lotto> lottoList = lottoRepository.findAll(Sort.by(Sort.Direction.DESC, "round"));



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

                for(String inputStr : inputArr ) {

                    if (lotto.getNo7().equals(inputStr)) {
                        score += 10;
                    }
                }

            }

            // 출력되야할 리스트에 넣는 부분.
            if(score>10){

                outputLotto.setRound(lotto.getRound());
                outputLotto.setWinning(lotto.getSecondWinning());
                outputLotto.setPlace("2등");

                outputLottoList.add(outputLotto);

            }else if(score==6){

                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getFirstWinning());
                    outputLotto.setPlace("1등");

                    outputLottoList.add(outputLotto);

            }else if(score==5){

                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getThirdWinning());
                    outputLotto.setPlace("3등");

                    outputLottoList.add(outputLotto);

            }else if(score==4){

                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getFourthWinning());
                    outputLotto.setPlace("4등");

                    outputLottoList.add(outputLotto);


            }else if(score==3){

                    outputLotto.setRound(lotto.getRound());
                    outputLotto.setWinning(lotto.getFifthWinning());
                    outputLotto.setPlace("5등");

                    outputLottoList.add(outputLotto);

            }


        }


        return outputLottoList;

    }

}
