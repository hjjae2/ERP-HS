package com.hserp.service;

import com.google.gson.Gson;
import com.hserp.entity.ApiLog;
import com.hserp.repository.ApiLogRepository;
import com.hserp.dto.ApiLogDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiLogService {

    private final ApiLogRepository apiLogRepository;
    private final Gson gson = new Gson();

    @Transactional
    public int create(ApiLogDto apiLogDto) {
        int apiLogId = -1;

        try {
            String apiLog = gson.toJson(apiLogDto);

            log.info(apiLog);

            apiLogId = apiLogRepository.save(new ApiLog(null, apiLog)).getId();
        } catch (Exception e) {
            log.warn(e.getMessage());
        }

        return apiLogId;
    }
}
