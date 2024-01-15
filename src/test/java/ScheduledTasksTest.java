import org.example.ScheduledTasks;
import org.example.pojo.ExchangeRateRequest;
import org.example.service.HomeworkService;
import org.example.utils.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScheduledTasksTest {

    @InjectMocks
    private ScheduledTasks scheduledTasks;

    @Mock
    private HomeworkService homeworkService;

    @Mock
    private ExchangeRatesService exchangeRatesService;

    @Test
    public void testScheduledTask() {
        // 模擬 ExchangeRatesService 返回一個樣本列表
        List<ExchangeRateRequest> mockExchangeRates = Collections.singletonList(new ExchangeRateRequest());

        // 當 exchangeRatesService.getExchangeRates 以任意字符串調用時，返回模擬的匯率列表
        when(exchangeRatesService.getExchangeRates(anyString())).thenReturn(mockExchangeRates);

        // 調用定時任務方法
        scheduledTasks.scheduledTask();

        // 驗證 homeworkService.insert 方法是否被使用模擬數據調用
        verify(homeworkService).insert(mockExchangeRates);
    }
}