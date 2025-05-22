package util.concurrent;

import java.io.BufferedReader;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.Collections;
        import java.util.HashSet;
        import java.util.Set;
        import java.util.concurrent.CountDownLatch;
        import java.util.concurrent.ExecutorService;
        import java.util.concurrent.Executors;

public class ConcurrentHttpTest {

    private static final String REQUEST_URL = "http://127.0.0.1:8090/ycslypt_web/api/getNum.do";
    private static final int THREAD_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT);

        // 用来检测是否返回重复编号
        Set<String> resultSet = Collections.synchronizedSet(new HashSet<>());

        for (int i = 0; i < THREAD_COUNT; i++) {
            executor.execute(() -> {
                try {
                    String result = sendGetRequest(REQUEST_URL);
                    System.out.println("Response: " + result);
                    resultSet.add(result.trim());
                } catch (Exception e) {
                    System.err.println("Request failed: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await(); // 等待所有线程完成
        executor.shutdown();

        System.out.println("------ Done ------");
        System.out.println("Total unique responses: " + resultSet.size());
        if (resultSet.size() < THREAD_COUNT) {
            System.err.println("⚠️ 存在重复编号！");
        } else {
            System.out.println("✅ 编号唯一，线程安全");
        }
    }

    private static String sendGetRequest(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(3000);
        con.setReadTimeout(3000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }

        in.close();
        return response.toString();
    }
}

