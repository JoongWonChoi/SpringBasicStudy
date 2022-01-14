package hello2.core.web;


import hello2.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    //private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> myLoggerProvider;
    //마찬가지로 request가 들어올때만 MyLogger가 빈으로 등록되고 의존관계 주입이 가능해지기 때문에
    //테스트를 위해 Provider를 사용하여 의존성을 검색하는 방식 사용.

    public void logic(String id) {
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("service id = " + id);

    }
}
