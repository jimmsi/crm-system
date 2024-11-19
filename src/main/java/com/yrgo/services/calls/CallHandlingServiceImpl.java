package com.yrgo.services.calls;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
@Transactional
@Service
public class CallHandlingServiceImpl implements CallHandlingService {

    private final CustomerManagementService customerService;
    private final DiaryManagementService diaryService;

    @Autowired
    public CallHandlingServiceImpl(CustomerManagementService customerService, DiaryManagementService diaryService) {
        this.customerService = customerService;
        this.diaryService = diaryService;
    }

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions) throws CustomerNotFoundException {
        customerService.recordCall(customerId, newCall);
        for (Action action : actions) {
            diaryService.recordAction(action);
        }
    }
}
