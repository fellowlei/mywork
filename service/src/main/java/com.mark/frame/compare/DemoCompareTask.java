package com.mark.frame.compare;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by lulei on 2016/12/23.
 */
public class DemoCompareTask extends CompareTask<Request> {
    private static final Log log = LogFactory.getLog(DemoCompareTask.class);
    public DemoCompareTask(ErrorTask<Request> errorTask) {
        super(errorTask);
    }

    @Override
    public boolean compare(Request request) {
        return false;
    }
}
