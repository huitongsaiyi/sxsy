package com.sayee.sxsy.newModules.training.service;

import com.sayee.sxsy.newModules.training.dao.TrainingMapper;
import com.sayee.sxsy.newModules.training.entity.Training;
import com.sayee.sxsy.newModules.training.entity.TrainingExample;
import com.sayee.sxsy.newModules.utils.ResponsesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    @Autowired
    TrainingMapper mapper;

    public ResponsesUtils fandByVidioType(Training training){
        TrainingExample example = new TrainingExample();
        example.createCriteria().andVidioTypeEqualTo(training.getVidioType());

        List<Training> trainings = mapper.selectByExample(example);

        return ResponsesUtils.ok(trainings);

    }
}
