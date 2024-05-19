package org.study.springaop.business;

import java.util.Arrays;
import org.springframework.stereotype.Service;
import org.study.springaop.data.DataService1;

@Service
public class BusinessService1 {

    private final DataService1 dataService1;

    public BusinessService1(DataService1 dataService1) {
        this.dataService1 = dataService1;
    }

    public int calMax() {
        int[] data = dataService1.retrieveData();
        return Arrays.stream(data)
            .max()
            .orElse(0);
    }

}
