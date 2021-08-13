package com.hserp.dto.work;

import com.hserp.entity.work.WorkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WorkTypeResponseDto {

    String name;

    public static WorkTypeResponseDto of(WorkType workType) {
        return new WorkTypeResponseDto(workType.getName());
    }
}
