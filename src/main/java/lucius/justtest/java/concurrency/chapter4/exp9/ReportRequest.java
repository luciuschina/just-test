package lucius.justtest.java.concurrency.chapter4.exp9;

import java.util.concurrent.CompletionService;

/**
 * Created by Lucius on 9/2/18.
 */
public class ReportRequest implements Runnable {
    private String name;
    private CompletionService<String> service;

    public ReportRequest(String name, CompletionService<String> service) {
        this.name = name;
        this.service = service;
    }

    @Override
    public void run() {
        ReportGenerator reportGenerator = new ReportGenerator(name, "Report");
        service.submit(reportGenerator);
    }
}
