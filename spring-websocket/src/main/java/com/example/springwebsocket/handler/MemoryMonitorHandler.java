package com.example.springwebsocket.handler;

import com.example.springwebsocket.util.RuntimeUtil;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MemoryMonitorHandler extends TextWebSocketHandler {
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();



    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session){
        executorService.scheduleAtFixedRate(() -> {
            try {
                session.sendMessage(new TextMessage(getMemoryInfo()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private String getMemoryInfo() {
        return "Total Memory: " + RuntimeUtil.getTotalMemoryStringInMb() + ", "
            + "Used Memory: " + RuntimeUtil.getUsedMemoryStringInMb() + ", "
            + "Free Memory: " + RuntimeUtil.getFreeMemoryStringInMb();
    }

}
