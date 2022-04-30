package com.wzy.scms.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinMaxAvgAchievementVo {
    Float maxAchievement;
    Float minAchievement;
    Double avgAchievement;
}
