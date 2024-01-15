import org.example.ScheduledTasks;
import org.example.pojo.ExchangeRateRequest;
import org.example.service.HomeworkService;
import org.example.utils.ExchangeRatesService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DailyExchangeRateBatchTest {

    @Mock
    private HomeworkService homeworkService;

    @InjectMocks
    private ScheduledTasks scheduledTasks;
    public DailyExchangeRateBatchTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExecuteBatch() {

        // Arrange
        List<ExchangeRateRequest> mockExchangeRates = Collections.singletonList(new ExchangeRateRequest(new Date(),30.15,null,null,null,null,null,null,null,null,null));
        Mockito.when(ExchangeRatesService.getExchangeRates(Mockito.anyString())).thenReturn(mockExchangeRates);

        // Act
        scheduledTasks.scheduledTask();

        // Assert
        Mockito.verify(homeworkService, Mockito.times(1)).insert(mockExchangeRates);
    }
}
